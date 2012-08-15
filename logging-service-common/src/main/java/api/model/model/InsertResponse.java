package api.model.model;

public final class InsertResponse {
    private String logId;

    public InsertResponse() {
    }

    public InsertResponse(String insertId) {
        this.logId = insertId;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }
}
