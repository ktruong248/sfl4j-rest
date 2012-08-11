package api;

import api.model.InsertResponse;
import api.model.LogLine;

public interface LoggingService {
    InsertResponse insert(LogLine logLine);
}
