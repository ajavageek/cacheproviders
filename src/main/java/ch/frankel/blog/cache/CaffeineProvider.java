package ch.frankel.blog.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public class CaffeineProvider implements CacheProvider<Long, String> {

    private final Cache<Long, String> cache;

    public CaffeineProvider() {
        cache = Caffeine.newBuilder().build();
    }

    @Override
    public void put(Long key, String value) {
        cache.put(key, value);
    }

    @Override
    public String get(Long key) {
        return cache.getIfPresent(key);
    }

    @Override
    public void close() {
        cache.cleanUp();
    }
}
