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

    @Test
    public void shouldMapFromLogLineDO() {

        LogLineDO logLineDO = new LogLineDO();
        logLineDO.setId("some id");
        logLineDO.setDetails("details");
        logLineDO.setIpAddress("ipaddress");
        logLineDO.setLogLevel(LogLevel.DEBUG);
        logLineDO.setLogTimeSec(12345);
        logLineDO.setMessage("message");
        logLineDO.setSource("source");

        LogLine logLine = mapper.mapFrom(logLineDO);
        assertNotNull(logLine);
        assertEquals(logLineDO.getId(), logLine.getId());
        assertEquals(logLineDO.getSource(), logLine.getSource());
        assertEquals(logLineDO.getMessage(), logLine.getMessage());
        assertEquals(logLineDO.getDetails(), logLine.getDetails());
        assertEquals(logLineDO.getIpAddress(), logLine.getIpAddress());
        assertEquals(logLineDO.getLogLevel(), logLine.getLogLevel());
        assertEquals(logLineDO.getLogTimeSec(), logLine.getLogTimeSec());
    }
}
