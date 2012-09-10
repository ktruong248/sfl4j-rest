package api;

import api.dao.LoggingDao;
import api.domain.LogEntry;
import api.mapping.Mapper;
import api.model.Event;
import api.model.InsertResponse;
import com.sun.jersey.api.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class LoggingServiceImplTest {

    private LoggingServiceImpl service;

    @Mock
    private LoggingDao dao;

    @Mock
    private Mapper<LogEntry, Event> mapper;

    @Before
    public void setup() {
        service = new LoggingServiceImpl();
        service.setLoggingDao(dao);
        service.setMapper(mapper);
    }

    @Test
    public void shouldInsert() {
        Event event = new Event();
        String insertId = "some id";

        LogEntry logEntry = new LogEntry();

        given(mapper.mapTo(event)).willReturn(logEntry);
        given(dao.insert(logEntry)).willReturn(insertId);

        InsertResponse insertResponse = service.insert(event);
        assertNotNull(insertResponse);
        assertEquals(insertId, insertResponse.getCreatedId());
    }

    @Test
    public void shouldAbleToGetById() {

        String logId = "some id";

        LogEntry logentry = new LogEntry();
        Event event = new Event();

        given(dao.find(logId)).willReturn(logentry);
        given(mapper.mapFrom(logentry)).willReturn(event);

        Event line = service.getById(logId);
        assertNotNull(line);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundExceptionForNullLogEntry() {
        String logId = "some unknown id";

        given(dao.find(logId)).willReturn(null);

        service.getById(logId);
    }
}
