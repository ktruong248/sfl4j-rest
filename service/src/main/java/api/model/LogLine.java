package api.model;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

import java.io.Serializable;

/**
 * An LogLine contains all the information for a single log event.
 * If this become big, will create a separate DataObject and annotate @Entity
 */
@Entity(noClassnameStored = true, value = "logs")
public final class LogLine implements Serializable {

    private static final long serialVersionUID = 2988958435513107789L;

    @Id
    private String id;

    private LogLevel logLevel;

    private String message;

    private String details;

    private String source;

    private String ipAddress;

    private long logTimeSec;

    public LogLine() {
    }

    public LogLine(LogLevel logLevel, String message, String details, String source, String ipAddress, long logTimeSec) {
        this.logLevel = logLevel;
        this.message = message;
        this.details = details;
        this.source = source;
        this.ipAddress = ipAddress;
        this.logTimeSec = logTimeSec;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    /**
     * the log level for this event
     *
     * @param logLevel the log level
     */
    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * the ip address of the machine that event occurred
     *
     * @param ipAddress
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public long getLogTimeSec() {
        return logTimeSec;
    }

    /**
     * the time at which event occured
     *
     * @param logTimeSec the ms seconds since epoc
     */
    public void setLogTimeSec(long logTimeSec) {
        this.logTimeSec = logTimeSec;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("LogLine");
        sb.append("{id='").append(id).append('\'');
        sb.append(", logLevel=").append(logLevel);
        sb.append(", message='").append(message).append('\'');
        sb.append(", details='").append(details).append('\'');
        sb.append(", source='").append(source).append('\'');
        sb.append(", ipAddress='").append(ipAddress).append('\'');
        sb.append(", logTimeSec=").append(logTimeSec);
        sb.append('}');
        return sb.toString();
    }
}
