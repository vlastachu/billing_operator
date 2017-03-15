package beans.impl;

import beans.AccountManager;
import model.Account;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AccountManagerImplTest {
    private AccountManagerImpl accountManager = new AccountManagerImpl();
    @Test
    public void getAll() throws Exception {
        List<Account> accounts = accountManager.getAll();
        assertEquals(2, accounts.size());
        assertEquals(new Account("999999999", 1000), accounts.get(0));
        assertEquals(new Account("888888888", 2000), accounts.get(1));
    }

    @Test
    public void removeAdd() throws Exception {
        List<Account> accounts = accountManager.getAll();
        assertEquals(2, accounts.size());
        Account account0 = accounts.get(0);
        Account account1 = accounts.get(1);
        accountManager.archiveAccountIfExist(account1);
        assertEquals(1, accountManager.getAll().size());
        assertEquals(account0, accountManager.getAll().get(0));
        accountManager.addAccount(account1);
        assertEquals(accounts, accountManager.getAll());
    }

    @Test
    public void addMoney() throws Exception {
        Account account0 = accountManager.getAll().get(0);
        assertEquals(1000, account0.getMoney());
        accountManager.addMoney(account0, 100);
        Account account0_ = accountManager.getAll().get(0);
        assertEquals(1100, account0_.getMoney());
        accountManager.addMoney(account0_, -100);
        assertEquals(1000, accountManager.getAll().get(0).getMoney());
    }

    @Test
    public void remove_NoSuchAccountException() throws IOException {
        try {
            accountManager.archiveAccountIfExist(new Account("", 0));
            fail("Expected an NoSuchAccountException to be thrown");
        } catch (AccountManager.NoSuchAccountException e) {
            // catch success
        }
    }

    @Test
    public void addMoney_NoSuchAccountException() throws IOException {
        try {
            accountManager.addMoney(new Account("", 0), 100);
            fail("Expected an NoSuchAccountException to be thrown");
        } catch (AccountManager.NoSuchAccountException e) {
            // catch success
        }
    }
}