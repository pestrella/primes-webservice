package rest.primes;

import com.google.inject.AbstractModule;

public class PrimesModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new PrimesServletModule());
    }
}
