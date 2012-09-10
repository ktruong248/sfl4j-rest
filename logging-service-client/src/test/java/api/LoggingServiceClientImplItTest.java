package api;

import api.common.Utils;
import api.model.Event;
import api.model.LogLevel;
import com.sun.jersey.api.client.config.ClientConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class LoggingServiceClientImplITTest {

    private LoggingServiceClientImpl serviceClient;

    private String serviceUrl;

    @Before
    public void setup() {
        serviceUrl = "http://localhost:8080";
        serviceClient = new LoggingServiceClientImpl(serviceUrl);
    }

    @Test
    public void shouldHaveDefaultReadAndConnectSetting() {
        Map<String, Object> properties = serviceClient.getClient().getProperties();
        assertEquals(LoggingServiceClientImpl.DEFAULT_CONNECT_TIMEOUT_MS, properties.get(ClientConfig.PROPERTY_CONNECT_TIMEOUT));
        assertEquals(LoggingServiceClientImpl.DEFAULT_READ_TIMEOUT_MS, properties.get(ClientConfig.PROPERTY_READ_TIMEOUT));
    }

    @Test
    @Ignore("uncommented to run as integration test")
    public void shouldBeAbleToCreateAndGet() {
        long time = System.currentTimeMillis();
        String ipAddress = Utils.localAddress();
        String message = "some test message for " + time;
        Event event = new Event("log", LogLevel.ERROR.name(), message, "details", "dev", ipAddress, time);

        String id = serviceClient.insert(event);
        assertNotNull(id);

        Event fetched = serviceClient.get(id);
        assertNotNull(fetched);
        assertEquals(id, fetched.getId());
    }

    @Test(expected = LoggingServiceClientException.class)
    @Ignore("uncommented to run as integration test")
    public void shouldThrowExceptionForNotFound() {
        serviceClient.get("some unknown");
    }
}
