package api.dao;

import api.dataObject.LogLineDO;

public interface LoggingDao {
    String insert(LogLineDO logLine);
}
