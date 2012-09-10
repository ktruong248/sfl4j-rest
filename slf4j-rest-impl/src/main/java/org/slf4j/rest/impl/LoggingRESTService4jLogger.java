package org.slf4j.rest.impl;

import api.LoggingServiceClient;
import api.model.Event;
import api.model.LogLevel;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


/**
 * LoggingRESTService and log4j wrapper
 * This will try to log to LoggingRestService first and fall back to log4j if failed
 */
public class LoggingRESTService4jLogger implements RESTServiceLogger {

    private Logger logger;

    private LoggingServiceClient loggingServiceClient;

    public LoggingRESTService4jLogger(Logger logger, LoggingServiceClient serviceClient) {
        this.logger = logger;
        this.loggingServiceClient = serviceClient;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public void debug(String callerFQCN, String msg, String source, String ipAddress, Throwable throwable) {
        boolean success = doLog(LogLevel.DEBUG, msg, source, ipAddress, throwable);
        if (!success) {
            logger.log(callerFQCN, Level.DEBUG, msg, throwable);
        }
    }

    public void trace(String callerFQCN, String msg, String appName, String ipAddress, Throwable throwable) {
        debug(callerFQCN, msg, appName, ipAddress, throwable);
    }


    public void info(String callerFQCN, String msg, String source, String ipAddress, Throwable throwable) {
        boolean success = doLog(LogLevel.INFO, msg, source, ipAddress, throwable);
        if (!success) {
            logger.log(callerFQCN, Level.INFO, msg, throwable);
        }
    }

    public void warn(String callerFQCN, String msg, String source, String ipAddress, Throwable throwable) {
        boolean success = doLog(LogLevel.WARN, msg, source, ipAddress, throwable);
        if (!success) {
            logger.log(callerFQCN, Level.WARN, msg, throwable);
        }
    }

    public void error(String callerFQCN, String msg, String source, String ipAddress, Throwable throwable) {
        boolean success = doLog(LogLevel.ERROR, msg, source, ipAddress, throwable);
        if (!success) {
            logger.log(callerFQCN, Level.ERROR, msg, throwable);
        }
    }

    protected boolean doLog(LogLevel logLevel, String msg, String source, String ipAddress, Throwable throwable) {

        try {
            long logTimeSec = System.currentTimeMillis();
            String details = (throwable != null) ? ExceptionUtils.getStackTrace(throwable) : "";
            loggingServiceClient.insert(new Event("log", logLevel.name(), msg, details, source, ipAddress, logTimeSec));
        } catch (com.sun.jersey.api.client.ClientHandlerException e) {
            // ignore any error and just fall back to standard logger
            if (logger.isTraceEnabled()) {
                logger.trace("having problem logging to service so switch back to default logger. Exception " + e.getMessage());
            }

            return false;
        }

        return true;
    }

}
