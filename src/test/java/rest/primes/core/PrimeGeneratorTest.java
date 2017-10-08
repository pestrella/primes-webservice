package rest.primes.core;

import org.junit.Test;

import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static rest.primes.core.PrimeGenerator.primes;
import static rest.primes.core.PrimeGenerator.primesParallel;

public class PrimeGeneratorTest {
    @Test
    public void testPrimes() {
        Integer[] primesTo100 = new Integer[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 };
        Integer[] primesTo1000 = new Integer[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997 };

        assertThat(primes(100), is(asList(primesTo100)));
        assertThat(primes(1000), is(asList(primesTo1000)));
    }

    @Test
    public void testInclusive() {
        assertThat(primes(7), is(asList(2, 3, 5, 7)));
    }

    @Test
    public void testEdgeCases() {
        assertThat(primes(-1), is(EMPTY_LIST));
        assertThat(primes(0), is(EMPTY_LIST));
        assertThat(primes(1), is(EMPTY_LIST));
        assertThat(primes(2), is(asList(2)));
    }

    @Test
    public void testPrimesParallel() {
        int n = 500000;

        long start = System.currentTimeMillis();
        List<Integer> primes = primesParallel(n);
        System.out.println(format("found %,d primes < %,d; elapsed time: %,d ms", primes.size(), n, System.currentTimeMillis() - start));

        assertThat(primes(n), is(primesParallel(n)));
    }
}
