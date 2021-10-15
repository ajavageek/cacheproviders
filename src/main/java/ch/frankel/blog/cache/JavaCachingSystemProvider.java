package ch.frankel.blog.cache;


import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;

public class JavaCachingSystemProvider<K, V> implements CacheProvider<K, V> {

    private final CacheAccess<K, V> cache;

    public JavaCachingSystemProvider() {
        cache = JCS.getInstance("cache");
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void close() {
        JCS.shutdown();
    }
}
