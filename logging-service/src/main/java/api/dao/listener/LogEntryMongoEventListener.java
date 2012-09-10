package api.dao.listener;

import api.domain.LogEntry;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;

import java.util.Date;

public class LogEntryMongoEventListener extends AbstractMongoEventListener<LogEntry> {

    @Override
    public void onBeforeConvert(LogEntry logEntry) {
        Date now = new Date();
        if (logEntry.getCreated() == null) {
            logEntry.setCreated(now);
        }

        logEntry.setModified(now);
    }
}
