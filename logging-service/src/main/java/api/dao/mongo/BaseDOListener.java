package api.dao.mongo;

import api.common.IdGenerator;
import api.common.UniqueIdGenerator;
import api.dataObject.BaseDO;
import com.google.code.morphia.annotations.PrePersist;
import org.springframework.util.StringUtils;

import java.util.Date;

public final class BaseDOListener {
    private IdGenerator idGenerator;

    public BaseDOListener() {
        idGenerator = new UniqueIdGenerator();
    }

    @PrePersist
    public void processPrePersist(BaseDO entity) {

        if (!StringUtils.hasLength(entity.getId())) {
            entity.setId(idGenerator.randomId());
        }

        if (entity.getCreated() == null) {
            entity.setCreated(new Date());
        }

        entity.setModified(new Date());
    }
}
