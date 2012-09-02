package api.dao.mongo;

import api.domain.BaseDomain;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class BaseDOListenerTest {
    BaseDOListener listener;
    private BaseDomain account;

    @Before
    public void setup() {
        listener = new BaseDOListener();
        account = new BaseDomain();
    }


    @Test
    public void shouldUpdateTheCreateAndLastModified() {

        assertThat(account.getCreated(), is(nullValue()));
        assertThat(account.getModified(), is(nullValue()));

        Date now = new Date();

        listener.processPrePersist(account);

        assertDateEquals(account.getCreated(), now, 200);
        assertDateEquals(account.getModified(), now, 200);
    }


    @Test
    public void shouldNotUpdateCreated() {
        Date now = new Date();
        Date createdDt = new Date(now.getTime() - 10000);

        account.setCreated(createdDt);

        listener.processPrePersist(account);

        assertThat(account.getCreated(), equalTo(createdDt));
        assertDateEquals(account.getModified(), now, 200);
    }


    @Test
    public void shouldSetIdWithGeneratedUUID() {
        assertThat(account.getId(), is(nullValue()));

        listener.processPrePersist(account);

        assertThat(account.getId(), notNullValue());
    }


    @Test
    public void shouldNotPopulateId() {
        account.setId("some id");

        listener.processPrePersist(account);

        assertThat(account.getId(), equalTo("some id"));
    }

    public static void assertDateEquals(Date dt, Date expect, int allowDelta) {

        if (dt == null && expect == null) {
            return;
        }

        if (dt != null && expect == null) {
            fail("expected " + expect + " got null");
        } else if (dt == null) {
            fail("expected null but got " + dt);
        } else {
            long delta = Math.abs(dt.getTime() - expect.getTime());
            assertTrue("expect " + expect.getTime() + " got " + dt.getTime(), delta <= allowDelta);
        }
    }
}
