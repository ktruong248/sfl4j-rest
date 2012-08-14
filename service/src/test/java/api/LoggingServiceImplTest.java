package api;

import api.dao.LoggingDao;
import api.dataObject.LogLineDO;
import api.mapping.Mapper;
import api.model.InsertResponse;
import api.model.LogLine;
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
    private Mapper<LogLineDO> mapper;

    @Before
    public void setup() {
        service = new LoggingServiceImpl();
        service.setLoggingDao(dao);
        service.setMapper(mapper);
    }

    @Test
    public void shouldInsert() {
        LogLine logLine = new LogLine();
        String insertId = "some id";

        LogLineDO logLineDO = new LogLineDO();

        given(mapper.mapTo(logLine)).willReturn(logLineDO);
        given(dao.insert(logLineDO)).willReturn(insertId);

        InsertResponse insertResponse = service.insert(logLine);
        assertNotNull(insertResponse);
        assertEquals(insertId, insertResponse.getLogId());
    }

    @Test
    public void shouldAbleToGetById() {

        String logId = "some id";

        LogLineDO logLineDO = new LogLineDO();
        LogLine logLine = new LogLine();

        given(dao.find(logId)).willReturn(logLineDO);
        given(mapper.mapFrom(logLineDO)).willReturn(logLine);

        LogLine line = service.getById(logId);
        assertNotNull(line);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundExceptionForNullLogLineDO() {
        String logId = "some unknown id";

        given(dao.find(logId)).willReturn(null);

        service.getById(logId);
    }
}
