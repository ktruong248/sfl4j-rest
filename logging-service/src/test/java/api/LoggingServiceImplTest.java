package api;

import api.dao.LogEntryRepository;
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
    private LogEntryRepository repository;

    @Mock
    private Mapper<LogEntry, Event> mapper;

    @Before
    public void setup() {
        service = new LoggingServiceImpl(repository, mapper);
    }

    @Test
    public void shouldInsert() {
        Event event = new Event();
        String insertId = "savedId";

        LogEntry logEntry = new LogEntry();

        LogEntry saved = new LogEntry();
        saved.setId(insertId);

        given(mapper.mapTo(event)).willReturn(logEntry);
        given(repository.save(logEntry)).willReturn(saved);

        InsertResponse insertResponse = service.insert(event);
        assertNotNull(insertResponse);
        assertEquals(insertId, insertResponse.getCreatedId());
    }

    @Test
    public void shouldAbleToGetById() {

        String logId = "some id";

        LogEntry logentry = new LogEntry();
        Event event = new Event();

        given(repository.findOne(logId)).willReturn(logentry);
        given(mapper.mapFrom(logentry)).willReturn(event);

        Event line = service.getById(logId);
        assertNotNull(line);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundExceptionForNullLogEntry() {
        String logId = "some unknown id";

        given(repository.findOne(logId)).willReturn(null);

        service.getById(logId);
    }
}
