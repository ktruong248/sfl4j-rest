package api.domain;

import api.dao.mongo.BaseDOListener;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.EntityListeners;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity(noClassnameStored = true, value = "logs")
@EntityListeners(BaseDOListener.class)
@Document(collection = "logs")
public final class LogEntry extends BaseDomain {

    private String type;

    private String category;

    private String message;

    private String details;

    private String source;

    private String ipAddress;

    private long logTimeSec;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("LogEntry");
        sb.append("{type='").append(type).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", details='").append(details).append('\'');
        sb.append(", source='").append(source).append('\'');
        sb.append(", ipAddress='").append(ipAddress).append('\'');
        sb.append(", logTimeSec=").append(logTimeSec);
        sb.append('}');
        return sb.toString();
    }
}
