package beans.impl;

import test_utils.DBDependentTest;
import model.entities.Call;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class CallManagerImplTest extends DBDependentTest {

    private CallManagerImpl callManagerImpl;

    @Before
    public void createBean() {
        callManagerImpl = new CallManagerImpl();
        callManagerImpl.em = em;
    }

    @Test
    public void getCalls() throws Exception {
        Call call1 = new Call("111", 1);
        Call call2 = new Call("112", 1);
        callManagerImpl.addCall(call1);
        callManagerImpl.addCall(call2);

        List<Call> calls = callManagerImpl.getCalls();
        assertNotNull(calls);
        assertEquals(2, calls.size());
        assertTrue(calls.contains(call1));
        assertTrue(calls.contains(call2));
    }

    @Test
    public void getCallsByPhoneNumber() throws Exception {
        Call call1 = new Call("111", 1);
        Call call2 = new Call("112", 1);
        Call call3 = new Call("222", 1);
        callManagerImpl.addCall(call1);
        callManagerImpl.addCall(call2);
        callManagerImpl.addCall(call3);

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
}