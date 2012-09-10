package api;

import api.model.Event;
import api.model.InsertResponse;
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
    public Response insert(Event event) {
        InsertResponse insertResponse = loggingService.insert(event);
        return Response.status(Response.Status.CREATED).entity(insertResponse).build();
    }

    @GET
    @Path("/{eventId}")
    public Event getById(@PathParam("eventId") String id) {
        return loggingService.getById(id);
    }

    @Autowired
    public void setLoggingService(LoggingService loggingService) {
        this.loggingService = loggingService;
    }
}
