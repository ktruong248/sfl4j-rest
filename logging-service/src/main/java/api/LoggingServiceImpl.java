package api;

import api.dao.LoggingDao;
import api.domain.LogEntry;
import api.mapping.Mapper;
import api.model.Event;
import api.model.InsertResponse;
import com.sun.jersey.api.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoggingServiceImpl implements LoggingService {
    private static final Logger log = LoggerFactory.getLogger(LoggingServiceImpl.class);

    private LoggingDao loggingDao;

    private Mapper<LogEntry, Event> mapper;

    public InsertResponse insert(Event event) {
        log.info("inserting {}", event);

        LogEntry logEntry = mapper.mapTo(event);
        String id = loggingDao.insert(logEntry);

        return new InsertResponse(id);
    }

    public Event getById(String id) {
        LogEntry logEntry = loggingDao.find(id);

        if (logEntry != null) {
            return mapper.mapFrom(logEntry);
        }

        throw new NotFoundException("not found event id " + id);
    }

    @Autowired
    public void setLoggingDao(LoggingDao loggingDao) {
        this.loggingDao = loggingDao;
    }

    @Autowired
    public void setMapper(Mapper<LogEntry, Event> mapper) {
        this.mapper = mapper;
    }
}
