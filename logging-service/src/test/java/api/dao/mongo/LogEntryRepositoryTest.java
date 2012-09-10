package api.dao.mongo;

import api.common.Utils;
import api.dao.LogEntryRepository;
import api.domain.LogEntry;
import api.model.model.LogLevel;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import java.net.UnknownHostException;
import java.util.List;

import static junit.framework.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class LogEntryRepositoryTest {

    @Autowired
    private LogEntryRepository logEntryRepository;

    @Before
    public void setup() throws UnknownHostException {
        logEntryRepository.deleteAll();
    }

    @Test
    @Ignore("this require mongo to be running so have to ignore")
    public void shouldAbleToCreate() {
        LogEntry logLine = new LogEntry();
        logLine.setDetails("some details " + System.currentTimeMillis());
        logLine.setLogLevel(LogLevel.ERROR);
        logLine.setSource("test-source");
        logLine.setIpAddress(Utils.localAddress());
        logLine.setLogTimeSec(System.currentTimeMillis());

        LogEntry saved = logEntryRepository.save(logLine);
        assertTrue(StringUtils.hasLength(saved.getId()));

        LogEntry logLineFromSourceA = new LogEntry();
        logLineFromSourceA.setDetails("some details " + System.currentTimeMillis());
        logLineFromSourceA.setLogLevel(LogLevel.ERROR);
        logLineFromSourceA.setSource("source-a");
        logLineFromSourceA.setIpAddress(Utils.localAddress());
        logLineFromSourceA.setLogTimeSec(System.currentTimeMillis());
        logEntryRepository.save(logLineFromSourceA);

        List<LogEntry> found = logEntryRepository.findBySource(logLine.getSource());
        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals(logLine.getSource(), found.get(0).getSource());
    }
}
