package beans.impl;

import beans.CallManager;
import model.entities.Call;
import org.junit.*;
import test_utils.DBDependentTest;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vlastachu on 09/03/2017.
 */
public class CallManagerImplTest extends DBDependentTest {

    private CallManagerImpl callManagerImpl;

    @Before
    public void createBean() {
        callManagerImpl = new CallManagerImpl();
        callManagerImpl.em = em;
    }

    @Test
    public void getCalls() throws Exception {

    }

    @Test
    public void getCallsByPhoneNumber() throws Exception {
        Call call1 = new Call("111", 1);
        Call call2 = new Call("112", 1);
        Call call3 = new Call("222", 1);
        em.persist(call1);
        em.persist(call2);
        em.persist(call3);

        List<Call> calls = callManagerImpl.getCallsByPhoneNumber("111");
        assertNotNull(calls);
        assertEquals(1, calls.size());
        assertSame(call1, calls.get(0));

        calls = callManagerImpl.getCallsByPhoneNumber("2");
        assertNotNull(calls);
        assertEquals(2, calls.size());
        assertTrue(calls.contains(call2));
        assertTrue(calls.contains(call3));
    }

    @Test
    public void addCall() throws Exception {

    }

}