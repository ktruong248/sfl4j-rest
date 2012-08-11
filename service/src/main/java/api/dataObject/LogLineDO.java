package api.dataObject;

import api.dao.mongo.BaseDOListener;
import api.model.LogLevel;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.EntityListeners;

@Entity(noClassnameStored = true, value = "logs")
@EntityListeners(BaseDOListener.class)
public final class LogLineDO extends BaseDO {

    private LogLevel logLevel;

    private String message;

    private String details;

    private String source;

    private String ipAddress;

    private long logTimeSec;

    public LogLevel getLogLevel() {
        return logLevel;
    }

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

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public long getLogTimeSec() {
        return logTimeSec;
    }

    public void setLogTimeSec(long logTimeSec) {
        this.logTimeSec = logTimeSec;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("LogLineDO");
        sb.append("{logLevel=").append(logLevel);
        sb.append(", id='").append(getId()).append('\'');
        sb.append(", created='").append(getCreated()).append('\'');
        sb.append(", modified='").append(getModified()).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", details='").append(details).append('\'');
        sb.append(", source='").append(source).append('\'');
        sb.append(", ipAddress='").append(ipAddress).append('\'');
        sb.append(", logTimeSec=").append(logTimeSec);
        sb.append('}');
        return sb.toString();
    }
}