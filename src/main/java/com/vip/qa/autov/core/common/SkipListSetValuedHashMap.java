package com.vip.qa.autov.core.common;

import org.apache.commons.collections4.multimap.AbstractSetValuedMap;

import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/** 支持并发执行，但不保证一致性（并发put可能会丢数据）
 * Created by kevin02.zhou on 2017/11/10.
 */
public class SkipListSetValuedHashMap<K, V> extends AbstractSetValuedMap<K, V> {

    private Comparator<V> comparator;

    public SkipListSetValuedHashMap(final Comparator<V> comparator) {
        super(new ConcurrentHashMap<K, Set<V>>());
        this.comparator = comparator;
    }

    @Override
    protected Set<V> createCollection() {
        return new ConcurrentSkipListSet<V>(this.comparator);
    }

}
