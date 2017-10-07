package rest.primes;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import rest.primes.core.PrimesService;
import rest.primes.core.SimpleCache;
import rest.primes.resource.PrimesResource;

import java.util.List;

import static com.google.inject.Scopes.SINGLETON;

public class AppModule extends ServletModule {
    @Override
    protected void configureServlets() {
        bind(JacksonJsonProvider.class).in(SINGLETON);
        bind(JacksonXMLProvider.class).in(SINGLETON);

        bind(GuiceContainer.class);
        serve("/*").with(GuiceContainer.class);
    }

    @Provides
    PrimesResource providePrimesResource(PrimesService primesService) {
        return new PrimesResource(primesService);
    }

    @Provides
    @Singleton
    PrimesService providePrimesService(SimpleCache<Integer, List<Integer>> cache) {
        PrimesService service = new PrimesService();
        service.setCache(cache);
        return service;
    }

    @Provides
    SimpleCache<Integer, List<Integer>> providePrimesCache() {
        return new SimpleCache<>(100);
    }
}