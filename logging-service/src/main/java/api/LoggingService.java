package api;

import api.model.model.InsertResponse;
import api.model.model.LogLine;

public interface LoggingService {
    InsertResponse insert(LogLine logLine);

    LogLine getById(String id);
}
