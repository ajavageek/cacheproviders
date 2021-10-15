package ch.frankel.blog.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

public class EhCacheProvider<K, V> implements CacheProvider<K, V> {

    private final CacheManager cacheManager;
    private final Cache<K, V> cache;

    public EhCacheProvider(Class<K> kClass, Class<V> vClass) {
        cacheManager = CacheManagerBuilder
                .newCacheManagerBuilder()
                .withCache("cache", CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        kClass, vClass, ResourcePoolsBuilder.heap(10))
                ).build();
        cacheManager.init();
        cache = cacheManager.getCache("cache", kClass, vClass);
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
        cacheManager.close();
    }
}
