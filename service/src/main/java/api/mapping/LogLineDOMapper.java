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
}
