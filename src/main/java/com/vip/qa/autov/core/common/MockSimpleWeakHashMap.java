package com.vip.qa.autov.core.common;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by kevin02.zhou on 2017/12/1.
 * 掌握核心科技之简单实现的WeakHashMap，未实现Map接口。提供listener，当value被回收时，会得到通知(非实时)。
 * @param <K>
 * @param <V>
 */
public class MockSimpleWeakHashMap<K,V> {

    //令同一实例只有一个清除任务在执行，不需要控制的太精确，故不使用volatile或同步
    private boolean expungeInProgress = false;

    public V get(K key) {
        expungeStaleEntries();
        Object keyRef = getRefKey(key);
        return map.get(keyRef);
    }

    public V put(K key, V value) {
        expungeStaleEntries();
        if (key == null) {
            throw new IllegalArgumentException("Null key");
        }
        WeakReference<K> keyRef = makeReference(key, refQueue);
        return map.put(keyRef, value);
    }

    public V remove(K key) {
        expungeStaleEntries();
        WeakReference<K> keyRef = makeReference(key);
        V oldValue = map.remove(keyRef);
        fireEntryRemovedListeners(keyRef, oldValue);
        return oldValue;
    }

    public Collection<V> values() {
        return map.values();
    }

    public void clear() {
        map.clear();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public int size() {
        return map.size();
    }

    public void expungeStaleEntries() {
        if (expungeInProgress) {
            return;
        }
        expungeInProgress = true;
        try {
            Reference<? extends K> ref;
            while ((ref = refQueue.poll()) != null) {
                V value = map.remove(ref);
                if (value != null) {
                    fireEntryRemovedListeners(ref, value);
                }
            }
        }
        finally {
            expungeInProgress = false;
        }
    }

    private void fireEntryRemovedListeners(Reference<? extends K> key, V value) {
        try {
            for (EntryRemovedListener listener : entryExpungedCallbackQueue) {
                Map.Entry<Reference<? extends K>, V> entry = new ImmutablePair<Reference<? extends K>, V>(key, value);
                listener.doRemoved(entry);
            }
        }
        catch (Exception cause) {
            //Ignore
        }
    }

    private Object getRefKey(K referent) {
        return new RefKey(referent);
    }

    private static class RefKey {

        private Object o;

        RefKey(Object o) {
            this.o = o;
        }

        @Override
        public int hashCode() {
            return o.hashCode();
        }

        @Override
        public boolean equals(Object another) {
            if (another == null) {
                return false;
            }
            return o.hashCode() == another.hashCode();
        }
    }

    private WeakReference<K> makeReference(K referent) {
        return new IdentityWeakReference<K>(referent);
    }

    private WeakReference<K> makeReference(K referent, ReferenceQueue<K> q) {
        return new IdentityWeakReference<K>(referent, q);
    }

    private static class IdentityWeakReference<T> extends WeakReference<T> {
        IdentityWeakReference(T o) {
            this(o, null);
        }

        IdentityWeakReference(T o, ReferenceQueue<T> q) {
            super(o, q);
            this.hashCode = (o == null) ? 0 : o.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o instanceof IdentityWeakReference<?>) {
                IdentityWeakReference<?> wr = (IdentityWeakReference<?>) o;
                Object got = get();
                return (got != null && got.equals(wr.get()));
            }
            return o.hashCode() == this.hashCode;
        }
        @Override
        public int hashCode() {
            return hashCode;
        }

        private final int hashCode;
    }

    private HashMap<Object, V> map = new HashMap<Object, V>();
    private ReferenceQueue<K> refQueue = new ReferenceQueue<K>();

    public static interface EntryRemovedListener {
        void doRemoved(Map.Entry entry);
    }

    private ConcurrentLinkedQueue<EntryRemovedListener> entryExpungedCallbackQueue = new ConcurrentLinkedQueue<EntryRemovedListener>();

    public void addStaleEntryExpungedListener(EntryRemovedListener entryRemovedListener) {
        entryExpungedCallbackQueue.add(entryRemovedListener);
    }

    public boolean removeStaleEntryExpungedListener(EntryRemovedListener entryRemovedListener) {
        return entryExpungedCallbackQueue.remove(entryRemovedListener);
    }
}
