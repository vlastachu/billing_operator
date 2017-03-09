package model.entities;

import org.junit.*;

import javax.persistence.Query;
import java.util.List;
import test_utils.DBDependentTest;

import static org.junit.Assert.*;

public class CallTest extends DBDependentTest {

    @Test
    public void addRemoveCall() {
        Call call = new Call("111", 1);
        em.persist(call);
        assertTrue(em.contains(call));

        Query query = em.createNamedQuery("Call.getAll");
        List<Call> calls = (List<Call>)query.getResultList();
        assertEquals(1, calls.size());
        Call dbCall = calls.get(0);
        assertNotNull(dbCall);
        assertSame(call, dbCall);
        em.remove(call);
    }

    @Test
    public void findCallByNumber() {
        Call call1 = new Call("111", 1);
        Call call2 = new Call("112", 1);
        Call call3 = new Call("222", 1);
        em.persist(call1);
        em.persist(call2);
        em.persist(call3);

        List<Call> calls = (List<Call>)em.createNamedQuery("Call.getAllByPhoneNumber").setParameter("phoneNumber", "111").getResultList();
        assertNotNull(calls);
        assertEquals(1, calls.size());
        assertSame(call1, calls.get(0));

        calls = (List<Call>)em.createNamedQuery("Call.getAllByPhoneNumber").setParameter("phoneNumber", "2").getResultList();
        assertNotNull(calls);
        assertEquals(2, calls.size());
        assertTrue(calls.contains(call2));
        assertTrue(calls.contains(call3));
    }
}