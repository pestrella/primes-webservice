package rest.primes.core;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PrimesService {
    private LoadingCache<Integer, List<Integer>> primes;

    public PrimesService() {
        primes = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(new CacheLoader<Integer, List<Integer>>() {
                    @Override
                    public List<Integer> load(Integer n) throws Exception {
                        return PrimeGenerator.primes(n);
                    }
                });
    }

    public List<Integer> computePrimes(Integer n) {
        try {
            return primes.get(n);
        } catch (ExecutionException e) {
            throw new RuntimeException("problem while computing primes", e);
        }
    }
}
