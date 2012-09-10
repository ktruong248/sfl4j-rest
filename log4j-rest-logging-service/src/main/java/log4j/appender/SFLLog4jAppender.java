package log4j.appender;

import api.LoggingServiceClient;
import api.LoggingServiceClientImpl;
import api.common.Utils;
import api.model.Event;
import api.model.LogLevel;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;

public class SFLLog4jAppender extends AppenderSkeleton {

    private String url;

    private String appName;

    private LoggingServiceClient loggingServiceClient;
    private String ipAddress;

    public SFLLog4jAppender() {
        appName = "dev";
        url = "http://localhost:8080";
        ipAddress = Utils.localAddress();
    }

    @Override
    protected void append(LoggingEvent event) {
        try {
            ThrowableInformation throwableInformation = event.getThrowableInformation();
            Throwable throwable = (throwableInformation != null) ? throwableInformation.getThrowable() : null;
            LogLevel logLevel = mapLogLevel(event.getLevel());
            Object message = event.getMessage();
            doLog(logLevel, (message != null) ? message.toString() : "", appName, ipAddress, throwable);
        } catch (Exception e) {
            errorHandler.error("Failed to insert event to REST endpoint " + url, e, 100);
        }
    }

    private LogLevel mapLogLevel(Level level) {
        if (Level.DEBUG.equals(level)) {
            return LogLevel.DEBUG;
        } else if (Level.TRACE.equals(level)) {
            return LogLevel.TRACE;
        } else if (Level.INFO.equals(level)) {
            return LogLevel.INFO;
        } else if (Level.WARN.equals(level)) {
            return LogLevel.WARN;
        } else if (Level.ERROR.equals(level)) {
            return LogLevel.ERROR;
        } else {
            return LogLevel.DEBUG;
        }
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void close() {
    }

    public boolean requiresLayout() {
        return true;
    }

    public LoggingServiceClient getLoggingClient() {
        if (this.loggingServiceClient == null) {
            this.loggingServiceClient = new LoggingServiceClientImpl(url);
        }
        return this.loggingServiceClient;
    }

    protected void doLog(LogLevel logLevel, String msg, String source, String ipAddress, Throwable throwable) {
        long logTimeSec = System.currentTimeMillis();
        String details = (throwable != null) ? ExceptionUtils.getStackTrace(throwable) : "";
        getLoggingClient().insert(new Event("log", logLevel.name(), msg, details, source, ipAddress, logTimeSec));
    }
}
