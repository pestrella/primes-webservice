package rest.primes.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class PrimesResponse {
    @JacksonXmlProperty(localName = "Initial")
    @JsonProperty("Initial")
    private final Integer initial;

    @JsonProperty("Primes")
    @JacksonXmlElementWrapper(localName = "Primes")
    private final List<Integer> primes;

    @JsonCreator
    public PrimesResponse(
            @JsonProperty("Initial") Integer initial,
            @JsonProperty("Primes") List<Integer> primes) {
        this.initial = initial;
        this.primes = primes;
    }

    public Integer getInitial() {
        return initial;
    }

    public List<Integer> getPrimes() {
        return primes;
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
