package api.dao.mongo;

import api.domain.LogEntry;
import com.google.code.morphia.Datastore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class MongoLoggingDaoTest {

    private MongoLoggingDao dao;

    @Mock
    private Datastore dataStore;

    @Before
    public void setup() {
        dao = new MongoLoggingDao();
        dao.setDatastore(dataStore);
    }

    @Test
    public void shouldInsert() {
        LogEntry logEntry = new LogEntry();

        dao.insert(logEntry);

        verify(dataStore, times(1)).save(logEntry);
    }

    @Test
    public void shouldFindById() {

        String id = "some id";
        given(dataStore.get(LogEntry.class, id)).willReturn(new LogEntry());

        LogEntry logEntry = dao.find(id);
        assertNotNull(logEntry);
    }
}
