package server;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class RestServerTest {

    private RestServer server;

    @Before
    public void setup() {
        server = new RestServer("classpath:server/spring-resource.xml");
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setPort(8098);
        server.setServerConfig(serverConfig);
    }

    @Test
    public void testNotHaveValidAppConfigPath() {

        try {
            server.setAppConfigPath("");
            server.startAndWait();
        } catch (Exception e) {
            assertFalse(server.isRunning());
            return;
        }

        fail("should have throw exception");
    }


    @Test
    public void testBadConfiguration() {

        try {
            server.setAppConfigPath("classpath:_missing_.xml");
            server.startAndWait();
        } catch (Exception e) {
            assertFalse(server.isRunning());
            return;
        }

        fail("should have thrown exception");
    }

    @Test
    public void testNoResources() {
        try {
            server.setAppConfigPath("classpath:server/spring-empty.xml");
            server.startAndWait();
        } catch (Exception e) {
            assertFalse(server.isRunning());
            return;
        }

        fail("should have throw exception");
    }

    @Test
    public void testResource() throws IOException {

        server.startAndWait();
        assertTrue(server.isRunning());

        URL url = new URL("http://localhost:8098/simple");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String response;
        while ((response = reader.readLine()) != null) {
            assertEquals("OK", response);
        }

        server.stopAndWait();
        assertFalse(server.isRunning());
    }
}
