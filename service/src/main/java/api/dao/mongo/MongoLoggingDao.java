package api.dao.mongo;

import api.dao.LoggingDao;
import api.dataObject.LogLineDO;
import com.google.code.morphia.Datastore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.mongodb.util.MyAsserts.assertNull;

@Repository
public class MongoLoggingDao implements LoggingDao {
    private static final Logger log = LoggerFactory.getLogger(MongoLoggingDao.class);
    private Datastore datastore;

    public String insert(LogLineDO logLine) {
        assertNull(logLine.getId());

        datastore.save(logLine);
        log.info("insert {}", logLine);

        return logLine.getId();
    }

    @Autowired
    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }
}
