package beans;

import model.Account;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AccountManager {
    List<Account> getAll();
}
