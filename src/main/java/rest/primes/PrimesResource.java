package rest.primes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;

@Path("/primes")
public class PrimesResource {

    @GET
    @Path("/{end}")
    public Response getPrimes(@PathParam("end") Integer end) {
        return Response.status(OK).entity(PrimeGenerator.primes(end).toString()).build();
    }
}
