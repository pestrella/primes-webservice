package rest.primes.core;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;

import static java.lang.String.format;

public class SimpleCache<K, V> {
    private LoadingCache<K, V> cache;
    private CacheBuilder cacheBuilder;

    public SimpleCache(int maxSize) {
        cacheBuilder = CacheBuilder.newBuilder().maximumSize(maxSize);
    }

    public void init(Function<K, V> loader) {
        cache = cacheBuilder.build(new CacheLoader<K, V>() {
            @Override
            public V load(K k) throws Exception {
                return loader.apply(k);
            }
        });
    }

    public V get(K k) {
        if (cache == null) {
            throw new IllegalStateException("cache has not been initialized");
        }

        try {
            return cache.get(k);
        } catch (ExecutionException e) {
            throw new RuntimeException(format("problem loading value for key '%s'", k), e);
        }
    }
}
