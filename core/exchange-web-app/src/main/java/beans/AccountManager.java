package beans;

import model.Account;

import javax.ejb.Local;
import java.io.IOException;
import java.util.List;

@Local
public interface AccountManager {
    List<Account> getAll() throws IOException;
}
