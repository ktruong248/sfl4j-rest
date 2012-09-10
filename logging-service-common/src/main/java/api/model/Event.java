package api.model;

import java.io.Serializable;
import java.util.Date;

/**
 * An LogLine contains all the information for a single log event.
 */
public final class Event implements Serializable {

    private static final long serialVersionUID = 2988958435513107789L;

    private String id;

    private String type;

    private String category;

    private String message;

    private String details;

    private String source;

    private String ipAddress;

    private long logTimeSec;

    private Date created;

    public Event() {
    }

    public Event(String type, String category, String message, String details, String source, String ipAddress, long logTimeSec) {
        this.message = message;
        this.details = details;
        this.source = source;
        this.ipAddress = ipAddress;
        this.logTimeSec = logTimeSec;
        this.type = type;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("LogLine");
        sb.append("{id='").append(id).append('\'');
        sb.append(", type='").append(type).append('\'');
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
