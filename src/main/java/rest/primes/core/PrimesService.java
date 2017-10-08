package rest.primes.core;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class PrimesService {
    private final LoadingCache<Integer, List<Integer>> primes;

    public PrimesService() {
        this(false);
    }

    public PrimesService(boolean doParallel) {
        Function<Integer, List<Integer>> primesFunction = doParallel
                ? PrimeGenerator::primesParallel
                : PrimeGenerator::primes;

        primes = CacheBuilder.newBuilder()
                .maximumSize(100)
                .build(new CacheLoader<Integer, List<Integer>>() {
                    @Override
                    public List<Integer> load(Integer n) throws Exception {
                        return primesFunction.apply(n);
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
