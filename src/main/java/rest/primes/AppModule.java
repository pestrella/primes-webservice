package rest.primes;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import rest.primes.core.PrimesService;
import rest.primes.resource.PrimesResource;

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
    PrimesResource providePrimesResource(@Named("primes") PrimesService primesService,
                                         @Named("parallelPrimes") PrimesService parallelPrimesService) {
        return new PrimesResource(primesService, parallelPrimesService);
    }

    @Provides
    @Singleton
    @Named("primes")
    PrimesService providePrimesService() {
        return new PrimesService();
    }

    @Provides
    @Singleton
    @Named("parallelPrimes")
    PrimesService provideParallelPrimesService() {
        return new PrimesService(true);
    }
}