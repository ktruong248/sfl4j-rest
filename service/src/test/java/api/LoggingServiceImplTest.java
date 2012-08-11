package api;

import api.dao.LoggingDao;
import api.dataObject.LogLineDO;
import api.mapping.Mapper;
import api.model.InsertResponse;
import api.model.LogLine;
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
}
