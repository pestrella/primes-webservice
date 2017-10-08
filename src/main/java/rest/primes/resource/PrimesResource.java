package rest.primes.resource;

import rest.primes.core.PrimesService;
import rest.primes.model.PrimesResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/primes")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class PrimesResource {
    private PrimesService primesService;
    private PrimesService parallelPrimesService;

    public PrimesResource(PrimesService primesService, PrimesService parallelPrimesService) {
        this.primesService = primesService;
        this.parallelPrimesService = parallelPrimesService;
    }

    @GET
    @Path("/{end}")
    public PrimesResponse getPrimes(@PathParam("end") Integer end,
                                    @QueryParam("parallel") String parallel) {

        List<Integer> primes = asBoolean(parallel)
                ? parallelPrimesService.computePrimes(end)
                : primesService.computePrimes(end);

        return new PrimesResponse.Builder()
                .withInitial(end)
                .withPrimes(primes)
                .build();
    }

    private static boolean asBoolean(String parallel) {
        return (parallel == null) ? false : Boolean.valueOf(parallel);
    }
}
