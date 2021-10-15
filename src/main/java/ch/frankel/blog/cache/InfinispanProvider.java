package ch.frankel.blog.cache;

import java.io.IOException;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

public class InfinispanProvider implements CacheProvider<Long, String> {

    private final EmbeddedCacheManager cacheManager;
    private final Cache<Long, String> cache;

    public InfinispanProvider() {
        cacheManager = new DefaultCacheManager();
        cacheManager.defineConfiguration("cache", new ConfigurationBuilder().build());
        cache = cacheManager.getCache("cache");
    }

    @Override
    public void put(Long key, String value) {
        cache.put(key, value);
    }

    @Override
    public String get(Long key) {
        return cache.get(key);
    }

    @Override
    public void close() throws IOException {
        cacheManager.close();
    }
}
