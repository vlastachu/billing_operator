package beans.impl;

import Util.CSVHelper;
import beans.AccountManager;
import model.Account;

import javax.ejb.Stateless;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AccountManagerImpl implements AccountManager{

    @Override
    public List<Account> getAll() {
        try {
            return CSVHelper.getAccountsFromFile("./src/main/resources/accounts/accounts.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
