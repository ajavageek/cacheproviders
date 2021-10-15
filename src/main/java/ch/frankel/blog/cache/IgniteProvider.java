package ch.frankel.blog.cache;

import java.io.IOException;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.lang.IgniteFuture;

public class IgniteProvider implements CacheProvider<Long, String> {

    private final Ignite ignite;
    private IgniteCache<Long, String> cache;

    public IgniteProvider() {
        ignite = Ignition.start();
        cache = ignite.getOrCreateCache("cache");
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
        ignite.close();
    }
}
