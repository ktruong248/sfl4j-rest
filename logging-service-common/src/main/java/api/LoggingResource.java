package api;

import api.model.model.LogLine;

import javax.ws.rs.core.Response;

public interface LoggingResource {
    Response insert(LogLine logLine);

    LogLine getById(String id);
}
