package ch.frankel.blog.cache;

import java.io.IOException;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CacheTests {

    static Stream<CacheProvider<Long, String>> cacheProviders() {
        return Stream.of(
                new JavaCachingSystemProvider<>(),
                new EhCacheProvider<>(Long.class, String.class),
                new GuavaProvider(),
                new CaffeineProvider(),
                new InfinispanProvider(),
                new CoherenceProvider(),
                new IgniteProvider(),
                new GeodeProvider(),
                new HazelcastProvider()
        );
    }

    @ParameterizedTest
    @MethodSource("cacheProviders")
    void whenPutShouldGet(CacheProvider<Long, String> provider) throws IOException {
        provider.put(1L, "One");
        var result = provider.get(1L);
        assertEquals("One", result);
        provider.close();
    }
}
