package api.mapping;

import api.domain.LogEntry;
import api.model.Event;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class LogEntryEventMapper implements Mapper<LogEntry, Event> {
    public LogEntry mapTo(Event event) {

        if (event == null) {
            return null;
        }

        LogEntry logEntry = new LogEntry();
        BeanUtils.copyProperties(event, logEntry);
        logEntry.setId(null);

        return logEntry;
    }

    public Event mapFrom(LogEntry logEntry) {
        if (logEntry == null) {
            return null;
        }

        Event event = new Event();
        BeanUtils.copyProperties(logEntry, event);

        return event;
    }
}
