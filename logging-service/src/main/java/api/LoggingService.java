package api;

import api.model.Event;
import api.model.InsertResponse;

public interface LoggingService {
    InsertResponse insert(Event event);

    Event getById(String id);
}
