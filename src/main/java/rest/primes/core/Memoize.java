package rest.primes.core;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.function.Function;

public class Memoize<K, V> {
    private LoadingCache<K, V> cache;

    public Memoize(Function<K, V> f) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k) throws Exception {
                        return f.apply(k);
                    }
                });
    }

    public V apply(K k) {
        return cache.getUnchecked(k);
    }
}
