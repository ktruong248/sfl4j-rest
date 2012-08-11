package api;

import api.model.InsertResponse;
import api.model.LogLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Service
@Path("/logs")
@Produces("application/json")
public class RESTLoggingResource implements LoggingResource {

    private LoggingService loggingService;

    public Response insert(LogLine logLine) {
        InsertResponse insertResponse = loggingService.insert(logLine);
        return Response.status(Response.Status.CREATED).entity(insertResponse).build();
    }

    @Autowired
    public void setLoggingService(LoggingService loggingService) {
        this.loggingService = loggingService;
    }
}
