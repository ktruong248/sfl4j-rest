package api;

import api.dao.LoggingDao;
import api.dataObject.LogLineDO;
import api.mapping.Mapper;
import api.model.InsertResponse;
import api.model.LogLine;
import com.sun.jersey.api.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoggingServiceImpl implements LoggingService {
    private static final Logger log = LoggerFactory.getLogger(LoggingServiceImpl.class);

    private LoggingDao loggingDao;

    private Mapper<LogLineDO> mapper;

    public InsertResponse insert(LogLine logLine) {
        log.info("inserting {}", logLine);

        LogLineDO logLineDO = mapper.mapTo(logLine);
        String id = loggingDao.insert(logLineDO);

        return new InsertResponse(id);
    }

    public LogLine getById(String id) {
        LogLineDO logLineDO = loggingDao.find(id);

        if (logLineDO != null) {
            return mapper.mapFrom(logLineDO);
        }

        throw new NotFoundException("not found log id " + id);
    }

    @Autowired
    public void setLoggingDao(LoggingDao loggingDao) {
        this.loggingDao = loggingDao;
    }

    @Autowired
    public void setMapper(Mapper<LogLineDO> mapper) {
        this.mapper = mapper;
    }
}
