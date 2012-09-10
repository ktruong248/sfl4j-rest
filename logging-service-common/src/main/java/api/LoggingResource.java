package api;

import api.model.Event;

import javax.ws.rs.core.Response;

public interface LoggingResource {
    Response insert(Event event);

    Event getById(String id);
}
