package rest.primes;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import rest.primes.resource.PrimesResource;

import static com.google.inject.Scopes.SINGLETON;

public class PrimesServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        bind(GuiceContainer.class);
        bind(PrimesResource.class);
        bind(JacksonJsonProvider.class).in(SINGLETON);
        bind(JacksonXMLProvider.class).in(SINGLETON);
        serve("/*").with(GuiceContainer.class);
    }
}
