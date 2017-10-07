package rest.primes.resource;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import rest.primes.Application;
import rest.primes.model.PrimesResponse;

import javax.ws.rs.core.MediaType;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RestIntegrationTest {
    private static final String HOST = "http://localhost:8080";
    private Client client;

    @BeforeClass
    public static void init() throws Exception {
        Application.main(new String[] {});
    }

    @Before
    public void setup() {
        client = Client.create();
    }

    @Test
    public void testJson() {
        WebResource resource = client.resource(HOST + "/primes/10");
        PrimesResponse response = resource.accept(MediaType.APPLICATION_JSON).get(PrimesResponse.class);
        assertThat(response.getPrimes(), is(asList(2, 3, 5, 7)));
    }

    @Test
    public void testXml() {
        WebResource resource = client.resource(HOST + "/primes/10");
        PrimesResponse response = resource.accept(MediaType.APPLICATION_XML).get(PrimesResponse.class);
        assertThat(response.getPrimes(), is(asList(2, 3, 5, 7)));
    }
}
