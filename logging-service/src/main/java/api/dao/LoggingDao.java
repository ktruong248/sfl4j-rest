package api.dao;

import api.domain.LogEntry;

public interface LoggingDao {
    String insert(LogEntry logLine);

    LogEntry find(String logId);
}
