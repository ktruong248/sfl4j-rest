package api.mapping;

import api.domain.LogEntry;
import api.model.model.LogLine;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class LogLineDOMapper implements Mapper<LogEntry> {
    public LogEntry mapTo(LogLine logLine) {

        if (logLine == null) {
            return null;
        }

        LogEntry logLineDO = new LogEntry();
        BeanUtils.copyProperties(logLine, logLineDO);
        logLineDO.setId(null);

        return logLineDO;
    }

    public LogLine mapFrom(LogEntry lineDO) {
        if (lineDO == null) {
            return null;
        }

        LogLine logLine = new LogLine();
        BeanUtils.copyProperties(lineDO, logLine);

        return logLine;
    }
}
