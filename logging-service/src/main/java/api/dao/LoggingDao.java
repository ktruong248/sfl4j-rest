package api.dao;

import api.domain.LogEntry;

public interface LoggingDao {
    String insert(LogEntry logEntry);

    LogEntry find(String logId);
}
