package api.mapping;

import api.dataObject.LogLineDO;
import api.model.LogLine;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class LogLineDOMapper implements Mapper<LogLineDO> {
    public LogLineDO mapTo(LogLine logLine) {

        if (logLine == null) {
            return null;
        }

        LogLineDO logLineDO = new LogLineDO();
        BeanUtils.copyProperties(logLine, logLineDO);
        logLineDO.setId(null);

        return logLineDO;
    }

    public LogLine mapFrom(LogLineDO lineDO) {
        if (lineDO == null) {
            return null;
        }

        LogLine logLine = new LogLine();
        BeanUtils.copyProperties(lineDO, logLine);

        return logLine;
    }
}
