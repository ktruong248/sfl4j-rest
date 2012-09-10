package api;

import api.dao.LogEntryRepository;
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

    private LogEntryRepository repository;

    private Mapper<LogEntry, Event> mapper;

    @Autowired
    public LoggingServiceImpl(LogEntryRepository repository, Mapper<LogEntry, Event> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public InsertResponse insert(Event event) {
        log.info("inserting {}", event);

        LogEntry logEntry = mapper.mapTo(event);

        LogEntry saved = repository.save(logEntry);

        return new InsertResponse(saved.getId());
    }

    public Event getById(String id) {
        LogEntry logEntry = repository.findOne(id);

        if (logEntry != null) {
            return mapper.mapFrom(logEntry);
        }

        throw new NotFoundException("not found event id " + id);
    }
}
