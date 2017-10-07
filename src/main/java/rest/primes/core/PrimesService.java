package rest.primes.core;

import java.util.List;

public class PrimesService {
    private SimpleCache<Integer, List<Integer>> cache;

    public List<Integer> computePrimes(Integer n) {
        return (cache != null) ? cache.get(n) : PrimeGenerator.primes(n);
    }

    public void setCache(SimpleCache<Integer, List<Integer>> cache) {
        //cache.init(PrimeGenerator::primes);
        this.cache = cache;
    }
}
