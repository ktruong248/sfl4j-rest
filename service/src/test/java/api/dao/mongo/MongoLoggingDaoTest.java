package api.dao.mongo;

import api.dataObject.LogLineDO;
import com.google.code.morphia.Datastore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
        LogLineDO logLine = new LogLineDO();

        dao.insert(logLine);

        verify(dataStore, times(1)).save(logLine);
    }
}
