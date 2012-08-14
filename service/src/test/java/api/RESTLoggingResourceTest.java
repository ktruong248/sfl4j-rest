package api;

import api.model.InsertResponse;
import api.model.LogLevel;
import api.model.LogLine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class RESTLoggingResourceTest {
    @Mock
    private Request request;

    @Mock
    private Response response;

    @Mock
    private LoggingService loggingService;

    private RESTLoggingResource resource;

    @Before
    public void setup() {
        resource = new RESTLoggingResource();
        resource.setLoggingService(loggingService);
    }

    @Test
    public void shouldInsert() {
        LogLine logLine = new LogLine(LogLevel.FATAL, "message", "details", "sample app", "127.0.0.1", System.currentTimeMillis());

        given(loggingService.insert(logLine)).willReturn(new InsertResponse("some id"));

        Response insertResponse = resource.insert(logLine);
        assertNotNull(insertResponse);
        assertEquals(Response.Status.CREATED.getStatusCode(), insertResponse.getStatus());
    }

    @Test
    public void shouldAbleToGetById() {

        given(loggingService.getById("some-id")).willReturn(new LogLine());

        LogLine logLine = resource.getById("some-id");
        assertNotNull(logLine);
    }
}
