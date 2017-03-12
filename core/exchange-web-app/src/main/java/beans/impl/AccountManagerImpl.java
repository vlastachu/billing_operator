package beans.impl;

import Util.CSVHelper;
import beans.AccountManager;
import model.Account;

import javax.ejb.Stateless;
import java.io.IOException;
import java.util.List;

@Stateless
public class AccountManagerImpl implements AccountManager{

    @Override
    public List<Account> getAll() throws IOException {
        return CSVHelper.getAccountsFromFile(this.getClass().getResource("/accounts/accounts.csv").getPath());
    }
}
