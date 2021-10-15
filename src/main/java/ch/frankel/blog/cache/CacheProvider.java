package ch.frankel.blog.cache;

import java.io.IOException;

public interface CacheProvider<K, V> {
    void put(K key, V value);
    V get(K key);
    void close() throws IOException;
}
