package rest.primes;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import static com.google.inject.Scopes.SINGLETON;

public class PrimesServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        bind(JacksonJsonProvider.class).in(SINGLETON);
        bind(JacksonXMLProvider.class).in(SINGLETON);

        bind(GuiceContainer.class);
        serve("/*").with(GuiceContainer.class);
    }
}