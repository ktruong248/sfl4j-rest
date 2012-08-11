package api.mapping;

import api.dataObject.LogLineDO;
import api.model.LogLevel;
import api.model.LogLine;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class LogLineDOMapperTest {

    private LogLineDOMapper mapper;

    @Before
    public void setup() {
        mapper = new LogLineDOMapper();
    }

    @Test
    public void shouldMapToDO() {

        LogLine logLine = new LogLine(LogLevel.DEBUG, "message", "details", "source", "12.34.56.78", System.currentTimeMillis());

        LogLineDO logLineDO = mapper.mapTo(logLine);
        assertNotNull(logLineDO);
        assertEquals(logLine.getSource(), logLineDO.getSource());
        assertEquals(logLine.getMessage(), logLineDO.getMessage());
        assertEquals(logLine.getDetails(), logLineDO.getDetails());
        assertEquals(logLine.getIpAddress(), logLineDO.getIpAddress());
        assertEquals(logLine.getLogLevel(), logLineDO.getLogLevel());
        assertEquals(logLine.getLogTimeSec(), logLineDO.getLogTimeSec());
    }

    @Test
    public void shouldSetIdToNull() {

        LogLine log = new LogLine();
        log.setId("some id");

        LogLineDO logLineDO = mapper.mapTo(log);
        assertNull(logLineDO.getId());
    }
}
