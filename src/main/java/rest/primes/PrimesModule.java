package rest.primes;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.GuiceFilter;

public class PrimesModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new PrimesServletModule());
    }
}
