package rest.primes;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class PrimesServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        bind(GuiceContainer.class);
        bind(PrimesResource.class);
        serve("/*").with(GuiceContainer.class);
    }
}
