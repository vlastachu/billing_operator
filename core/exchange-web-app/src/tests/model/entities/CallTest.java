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
}