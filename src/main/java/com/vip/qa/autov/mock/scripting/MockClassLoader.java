package com.vip.qa.autov.mock.scripting;

import com.vip.qa.autov.core.common.MockSimpleWeakHashMap;
import com.vip.qa.autov.core.utils.ReflectionUtils;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.reflection.GroovyClassValue;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Introspector;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by kevin02.zhou on 2017/12/1.
 * This classLoader just keeps a weak reference to the loaded class
 * if the script text is discarded by the garbage collector, the relevant classes could be discarded as well(if not in use any more).
 */
public class MockClassLoader extends GroovyClassLoader {

    private final ReferenceQueue<Class> classReferenceQueue = new ReferenceQueue<>();

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private GroovyClassValue groovyGlobalClassCache;
    private boolean enableCache = true;

    private static class ClassWeakReference extends WeakReference<Class> {
        private String referentClassName;

        public ClassWeakReference(final Class referent, final ReferenceQueue<? super Class> q) {
            super(referent, q);
            this.referentClassName = referent.getName();
        }

        public String getReferentClassName() {
            return this.referentClassName;
        };
    }

    private final MockSimpleWeakHashMap<String, Class<Script>> sourceCache = new MockSimpleWeakHashMap<>();
    protected final MockSimpleWeakHashMap<String, WeakReference<Class>> scriptClassCache = new MockSimpleWeakHashMap<>();

    public MockClassLoader() {
        super();
        init();
    }

    public MockClassLoader(final ClassLoader classLoader) {
        super(classLoader);
        init();
    }

    public static MockClassLoader toMockClassLoader(final ClassLoader classLoader) {
        if (classLoader instanceof MockClassLoader) {
            return (MockClassLoader) classLoader;
        }
        else {
            return new MockClassLoader(classLoader);
        }
    }

    private MockSimpleWeakHashMap.EntryRemovedListener scriptRemovedListener = new MockSimpleWeakHashMap.EntryRemovedListener() {
        @Override
        public void doRemoved(final Map.Entry entry) {
            final Class clazz = (Class) entry.getValue();
            if (clazz != null) {
                removeScript(clazz);
            }
        }
    };

    private void removeScript(final Class scriptClass) {
        synchronized (this.scriptClassCache) {
            InvokerHelper.removeClass(scriptClass);
            this.scriptClassCache.remove(scriptClass.getName());
            if (this.groovyGlobalClassCache != null) {
                this.groovyGlobalClassCache.remove(scriptClass);
            }
        }
    }

    private void init() {
        try {
            this.groovyGlobalClassCache = (GroovyClassValue) ReflectionUtils.getStaticFieldValue(ClassInfo.class, "globalClassValue");
            this.sourceCache.addStaleEntryExpungedListener(this.scriptRemovedListener);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Class parseClass(final String scriptText) throws CompilationFailedException {
        Class<Script> compiledScriptClass = this.sourceCache.get(scriptText);
        if (compiledScriptClass == null) {
            synchronized (this.sourceCache) {
                compiledScriptClass = this.sourceCache.get(scriptText);
                if (compiledScriptClass == null) {
                    final Class newScript = super.parseClass(scriptText);
                    if (this.enableCache) {
                        this.sourceCache.put(scriptText, newScript);
                    }
                    compiledScriptClass = newScript;
                }
            }
        }
        return compiledScriptClass;
    }

    @Override
    public Class getClassCacheEntry(final String name) {
        if (name == null) {
            return null;
        }
        expungeStaleEntries();
        synchronized (this.scriptClassCache) {
            final WeakReference<Class> classReference = this.scriptClassCache.get(name);
            if (classReference != null) {
                return classReference.get();
            }
        }
        return null;
    }

    @Override
    protected void setClassCacheEntry(final Class cls) {
        expungeStaleEntries();
        synchronized (this.scriptClassCache) {
            this.scriptClassCache.put(cls.getName(), new ClassWeakReference(cls, this.classReferenceQueue));
        }
    }

    @Override
    public void removeClassCacheEntry(final String name) {
        final Class scriptClass = getClassCacheEntry(name);
        if (scriptClass != null) {
            synchronized (this.scriptClassCache) {
                this.scriptClassCache.remove(scriptClass.getName());
            }
        }
    }

    @Override
    public void clearCache() {
        super.clearCache();

        final Class[] loadedClasses = getLoadedClasses();
        for (final Class loadedClass : loadedClasses) {
            InvokerHelper.removeClass(loadedClass);
            if (this.groovyGlobalClassCache != null) {
                this.groovyGlobalClassCache.remove(loadedClass);
            }
        }

        synchronized (this.scriptClassCache) {
            this.scriptClassCache.clear();
        }
        synchronized (this.sourceCache) {
            this.sourceCache.clear();
        }
        ClassInfo.clearModifiedExpandos();
        Introspector.flushCaches();
    }

    public void removeCache(final String scriptText) {
        Class scriptClass = null;
        synchronized (this.sourceCache) {
            scriptClass = this.sourceCache.remove(scriptText);
        }
        if (scriptClass != null) {
            removeScript(scriptClass);
        }
    }

    public Class<Script> getLoadedClass(final String scriptText) {
        synchronized (this.sourceCache) {
            return this.sourceCache.get(scriptText);
        }
    }

    @Override
    public Class[] getLoadedClasses() {
        expungeStaleEntries();
        synchronized (this.scriptClassCache) {
            final Collection<WeakReference<Class>> values = this.scriptClassCache.values();
            final Collection<Class> classes = new ArrayList<>(values.size());
            for (final WeakReference<Class> reference : values) {
                final Class cls = reference.get();
                if (cls != null) {
                    classes.add(cls);
                }
            }
            return classes.toArray(new Class[classes.size()]);
        }
    }

    public void expungeStaleEntries() {
        this.sourceCache.expungeStaleEntries();
        //help gc
        for (Object x; (x = this.classReferenceQueue.poll()) != null; ) {
        }
    }

    public boolean isEnableCache() {
        return this.enableCache;
    }

    public void setEnableCache(final boolean enableCache) {
        this.enableCache = enableCache;
    }

}
