package api;


import api.model.Event;

public interface LoggingServiceClient {

    String insert(Event event);

    Event get(String id);
}
