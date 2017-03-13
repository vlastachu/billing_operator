package beans;

import model.Account;

import javax.ejb.Local;
import java.io.IOException;
import java.util.List;

@Local
public interface AccountManager {
    public static class NoSuchAccountException extends RuntimeException {}

    List<Account> getAll() throws IOException;
    void archiveAccountIfExist(Account account) throws IOException, NoSuchAccountException;
    void addMoney(Account account, int money) throws IOException, NoSuchAccountException;
    void addAccount(Account account) throws IOException, NoSuchAccountException;
}
