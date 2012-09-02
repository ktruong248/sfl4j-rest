package api.domain;

import com.google.code.morphia.annotations.Id;

import java.util.Date;

public class BaseDomain {

    @Id
    private String id;

    private Date created;

    private Date modified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseDomain)) return false;

        BaseDomain baseDO = (BaseDomain) o;

        if (created != null ? !created.equals(baseDO.created) : baseDO.created != null) return false;
        return !(id != null ? !id.equals(baseDO.id) : baseDO.id != null) && !(modified != null ? !modified.equals(baseDO.modified) : baseDO.modified != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BaseDO");
        sb.append("{id='").append(id).append('\'');
        sb.append(", created=").append(created);
        sb.append(", modified=").append(modified);
        sb.append('}');
        return sb.toString();
    }
}
