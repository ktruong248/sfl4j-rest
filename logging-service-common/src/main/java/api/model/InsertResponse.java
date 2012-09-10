package api.model;

public final class InsertResponse {
    private String createdId;

    public InsertResponse() {
    }

    public InsertResponse(String insertId) {
        this.createdId = insertId;
    }

    public String getCreatedId() {
        return createdId;
    }

    public void setCreatedId(String createdId) {
        this.createdId = createdId;
    }
}
