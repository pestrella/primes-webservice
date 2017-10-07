package rest.primes.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.partition;
import static java.util.Collections.EMPTY_LIST;
import static java.util.stream.Collectors.toList;

public class PrimeGenerator {
    public static List<Integer> primes(int n) {
        if (n < 2) {
            return EMPTY_LIST;
        }

        if (n == 2) {
            return newArrayList(2);
        }

        List<Integer> primes = newArrayList(2);
        for (int i : seq(3, n, 2)) {
            if (isPrime.apply(i)) {
                primes.add(i);
            }
        }

        return primes;
    }

    public static List<Integer> primesParallel(int n) {
        if (n < 2) {
            return EMPTY_LIST;
        }

        if (n == 2) {
            return newArrayList(2);
        }

        ArrayList<Integer> primeCandidates = seq(3, n, 2);
        primeCandidates.add(0, 2);

        return partition(primeCandidates, 200000).parallelStream()
                .map(pc -> pc.stream()
                        .filter(isPrime::apply)
                        .collect(toList()))
                .flatMap(Collection::stream)
                .collect(toList());
    }

    private static Memoize<Integer, Boolean> isPrime = new Memoize<>(k -> isPrime(k));

    private static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        for (Integer divisor : seq(3, (int) Math.sqrt(n), 2)) {
            if (n % divisor == 0) {
                return false;
            }
        }

        return true;
    }

    private static ArrayList<Integer> seq(int start, int end, int inc) {
        ArrayList<Integer> seq = new ArrayList<>();
        for (int i = start; i <= end; ) {
            seq.add(i);
            i += inc;
        }
        return seq;
    }
}
