package api;

import api.model.model.LogLine;

public interface LoggingServiceClient {

    String insert(LogLine logLine);

    LogLine get(String id);
}
