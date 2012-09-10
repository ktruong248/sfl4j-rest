package api.mapping;

import api.domain.LogEntry;
import api.model.Event;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class LogEntryEventMapperTest {

    private LogEntryEventMapper mapper;

    @Before
    public void setup() {
        mapper = new LogEntryEventMapper();
    }

    @Test
    public void shouldMapToDO() {

        Event event = new Event("log", "debug", "message", "details", "source", "12.34.56.78", System.currentTimeMillis());


        LogEntry logEntry = mapper.mapTo(event);
        assertNotNull(logEntry);
        assertEquals(event.getSource(), logEntry.getSource());
        assertEquals(event.getMessage(), logEntry.getMessage());
        assertEquals(event.getDetails(), logEntry.getDetails());
        assertEquals(event.getIpAddress(), logEntry.getIpAddress());
        assertEquals(event.getType(), logEntry.getType());
        assertEquals(event.getCategory(), event.getCategory());
        assertEquals(event.getLogTimeSec(), logEntry.getLogTimeSec());
    }

    @Test
    public void shouldSetIdToNull() {

        Event event = new Event();
        event.setId("some id");

        LogEntry logEntry = mapper.mapTo(event);
        assertNull(logEntry.getId());
    }

    @Test
    public void shouldMapFromLogLineDO() {

        LogEntry logEntry = new LogEntry();
        logEntry.setId("some id");
        logEntry.setDetails("details");
        logEntry.setIpAddress("ipaddress");
        logEntry.setType("debug");
        logEntry.setCategory("log");
        logEntry.setLogTimeSec(12345);
        logEntry.setMessage("message");
        logEntry.setSource("source");

        Event event = mapper.mapFrom(logEntry);
        assertNotNull(event);
        assertEquals(logEntry.getId(), event.getId());
        assertEquals(logEntry.getSource(), event.getSource());
        assertEquals(logEntry.getMessage(), event.getMessage());
        assertEquals(logEntry.getDetails(), event.getDetails());
        assertEquals(logEntry.getIpAddress(), event.getIpAddress());
        assertEquals(logEntry.getType(), event.getType());
        assertEquals(logEntry.getCategory(), event.getCategory());
        assertEquals(logEntry.getLogTimeSec(), event.getLogTimeSec());
    }
}
