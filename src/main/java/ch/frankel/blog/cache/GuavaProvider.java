package ch.frankel.blog.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class GuavaProvider implements CacheProvider<Long, String> {

    private final Cache<Long, String> cache;

    public GuavaProvider() {
        cache = CacheBuilder.newBuilder()
                .initialCapacity(1000)
                .build();
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
