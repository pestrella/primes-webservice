package rest.primes;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Application {
    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                install(new PrimesModule());
                bind(GuiceFilter.class);
            }
        });

        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/");

        FilterHolder guice = new FilterHolder(injector.getInstance(GuiceFilter.class));
        handler.addFilter(guice, "/*", EnumSet.allOf(DispatcherType.class));

        Server server = new Server(8080);
        server.setHandler(handler);
        server.start();
    }
}
