package rest.primes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/primes")
@Produces(MediaType.APPLICATION_JSON)
public class PrimesResource {

    @GET
    @Path("/{end}")
    public List<Integer> getPrimes(@PathParam("end") Integer end) {
        return PrimeGenerator.primes(end);
    }
}
