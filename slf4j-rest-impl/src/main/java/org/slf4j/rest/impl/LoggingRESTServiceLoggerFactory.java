package org.slf4j.rest.impl;

import org.apache.log4j.LogManager;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoggingRESTServiceLoggerFactory implements ILoggerFactory {

    private Map<String, Logger> loggerMap;

    public LoggingRESTServiceLoggerFactory() {
        loggerMap = new ConcurrentHashMap<String, Logger>();
    }

    /*
    * (non-Javadoc)
    *
    * @see org.slf4j.ILoggerFactory#getLogger(java.lang.String)
    */
    public Logger getLogger(String name) {
        Logger slf4jLogger;
        // protect against concurrent access of loggerMap
        synchronized (this) {
            slf4jLogger = loggerMap.get(name);
            if (slf4jLogger == null) {
                org.apache.log4j.Logger log4jLogger;
                if (name.equalsIgnoreCase(Logger.ROOT_LOGGER_NAME)) {
                    log4jLogger = LogManager.getRootLogger();
                } else {
                    log4jLogger = LogManager.getLogger(name);
                }
                slf4jLogger = new LoggingRESTServiceAdapter(log4jLogger);
                loggerMap.put(name, slf4jLogger);
            }
        }
        return slf4jLogger;
    }
}
