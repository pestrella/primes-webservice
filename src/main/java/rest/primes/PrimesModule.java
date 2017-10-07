package rest.primes;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import rest.primes.core.PrimesService;
import rest.primes.resource.PrimesResource;

public class PrimesModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new PrimesServletModule());
    }

    @Provides
    PrimesResource providePrimesResource(PrimesService primesService) {
        return new PrimesResource(primesService);
    }

    @Provides
    @Singleton
    PrimesService providePrimesService() {
        return new PrimesService();
    }
}
