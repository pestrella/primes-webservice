package rest.primes;

import java.util.ArrayList;
import java.util.List;

public class PrimeGenerator {
    public static List<Integer> primes(int n) {
        List<Integer> primes = new ArrayList<>();
        if (n < 2) {
            return primes;
        }

        primes.add(2);

        if (n == 2) {
            return primes;
        }

        for (int i : seq(3, n, 2)) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }

        return primes;
    }

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

    private static List<Integer> seq(int start, int end, int inc) {
        List<Integer> seq = new ArrayList<>();
        for (int i = start; i <= end; ) {
            seq.add(i);
            i += inc;
        }
        return seq;
    }
}
