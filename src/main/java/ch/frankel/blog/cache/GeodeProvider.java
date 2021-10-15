package ch.frankel.blog.cache;

import java.io.IOException;
import org.apache.geode.cache.*;

public class GeodeProvider implements CacheProvider<Long, String> {

    private final Cache cache;
    private final Region<Long, String> region;

    public GeodeProvider() {
        cache = new CacheFactory().create();
        region = cache.<Long, String>createRegionFactory().create("cache");
    }

    @Override
    public void put(Long key, String value) {
        region.put(key, value);
    }

    @Override
    public String get(Long key) {
        return region.get(key);
    }

    @Override
    public void close() throws IOException {
        cache.close();
    }
}
