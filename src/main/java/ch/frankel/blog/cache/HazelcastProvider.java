package ch.frankel.blog.cache;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HazelcastProvider implements CacheProvider<Long, String> {

    private final HazelcastInstance hazelcast;
    private final IMap<Long, String> cache;

    public HazelcastProvider() {
        hazelcast = Hazelcast.newHazelcastInstance();
        cache = hazelcast.getMap("cache");
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
        hazelcast.shutdown();
    }
}
