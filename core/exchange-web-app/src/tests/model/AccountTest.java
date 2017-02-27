package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
    @Test
    public void getters() {
        Account account = new Account("9999999", 42);
        assertEquals("9999999", account.getPhoneNumber());
        assertEquals(42, account.getMoney());
    }
}