package rest.primes.resource;

import rest.primes.core.PrimesService;
import rest.primes.model.PrimesResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/primes")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class PrimesResource {
    private PrimesService primesService;

    public PrimesResource(PrimesService primesService) {
        this.primesService = primesService;
    }

    @GET
    @Path("/{end}")
    public PrimesResponse getPrimes(@PathParam("end") Integer end) {
        return new PrimesResponse.Builder()
                .withInitial(end)
                .withPrimes(primesService.computePrimes(end))
                .build();
    }
}
