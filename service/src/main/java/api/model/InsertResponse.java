package api.model;

public final class InsertResponse {
    private String logId;

    public InsertResponse(String insertId) {
        this.logId = insertId;
    }

    public String getLogId() {
        return logId;
    }
}
