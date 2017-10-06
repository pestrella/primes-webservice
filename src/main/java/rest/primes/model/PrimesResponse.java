package rest.primes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PrimesResponse {
    @JsonProperty("Initial")
    public final Integer initial;

    @JsonProperty("Primes")
    public final List<Integer> primes;

    public PrimesResponse(Integer initial, List<Integer> primes) {
        this.initial = initial;
        this.primes = primes;
    }

    public static class Builder {
        private Integer initial;
        private List<Integer> primes;

        public Builder withInitial(Integer initial) {
            this.initial = initial;
            return this;
        }

        public Builder withPrimes(List<Integer> primes) {
            this.primes = primes;
            return this;
        }

        public PrimesResponse build() {
            return new PrimesResponse(initial, primes);
        }
    }
}
