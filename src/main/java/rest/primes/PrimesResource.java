package rest.primes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/primes")
public class PrimesResource {

    @GET
    @Path("/{end}")
    public List<Integer> getPrimes(@PathParam("end") Integer end) {
        return PrimeGenerator.primes(end);
    }
}
