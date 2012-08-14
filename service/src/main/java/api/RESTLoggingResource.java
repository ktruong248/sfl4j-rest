package api;

import api.model.InsertResponse;
import api.model.LogLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Service
@Path("/logs")
@Produces("application/json")
public class RESTLoggingResource implements LoggingResource {

    private LoggingService loggingService;

    @POST
    public Response insert(LogLine logLine) {
        InsertResponse insertResponse = loggingService.insert(logLine);
        return Response.status(Response.Status.CREATED).entity(insertResponse).build();
    }

    @GET
    @Path("/{logId}")
    public LogLine getById(@PathParam("logId") String id) {
        return loggingService.getById(id);
    }

    @Autowired
    public void setLoggingService(LoggingService loggingService) {
        this.loggingService = loggingService;
    }
}
