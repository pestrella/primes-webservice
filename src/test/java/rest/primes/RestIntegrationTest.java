package rest.primes;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import rest.primes.model.PrimesResponse;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RestIntegrationTest {
    private static final String HOST = "http://localhost:8080";

    @BeforeClass
    public static void init() throws Exception {
        Application.main(new String[] {});
    }

    @Test
    public void testPrimesEndpoint() {
        Client client = Client.create();
        WebResource resource = client.resource(HOST + "/primes/10");
        PrimesResponse response = resource.get(PrimesResponse.class);
        assertThat(response.primes, is(asList(2, 3, 5, 7)));
    }
}
