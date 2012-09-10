package api.dao.mongo;

import api.common.Utils;
import api.dao.LogEntryRepository;
import api.domain.LogEntry;
import api.model.LogLevel;
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
        LogEntry logEntry = new LogEntry();
        logEntry.setDetails("some details " + System.currentTimeMillis());
        logEntry.setType("log");
        logEntry.setCategory(LogLevel.ERROR.name());
        logEntry.setSource("test-source");
        logEntry.setIpAddress(Utils.localAddress());
        logEntry.setLogTimeSec(System.currentTimeMillis());

        LogEntry saved = logEntryRepository.save(logEntry);
        assertTrue(StringUtils.hasLength(saved.getId()));

        LogEntry logEntryFromSourceA = new LogEntry();
        logEntryFromSourceA.setDetails("some details " + System.currentTimeMillis());
        logEntryFromSourceA.setCategory(LogLevel.ERROR.name());
        logEntryFromSourceA.setType("log");
        logEntryFromSourceA.setSource("source-a");
        logEntryFromSourceA.setIpAddress(Utils.localAddress());
        logEntryFromSourceA.setLogTimeSec(System.currentTimeMillis());
        logEntryRepository.save(logEntryFromSourceA);

        List<LogEntry> found = logEntryRepository.findBySource(logEntry.getSource());
        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals(logEntry.getSource(), found.get(0).getSource());
    }
}
