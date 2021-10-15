package ch.frankel.blog.cache;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CoherenceProvider implements CacheProvider<Long, String> {

    private final NamedCache<Long, String> cache;

    public CoherenceProvider() {
        cache = CacheFactory.getCache("cache");
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
    public void close() {
        cache.close();
    }
}
