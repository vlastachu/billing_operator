package beans.impl;

import Util.CSVHelper;
import beans.AccountManager;
import model.Account;

import javax.ejb.Stateless;
import java.io.IOException;
import java.util.List;

@Stateless
public class AccountManagerImpl implements AccountManager{
    private static final String csvPath = AccountManagerImpl.class.getResource("/accounts/accounts.csv").getPath();

    @Override
    public List<Account> getAll() throws IOException {
        return CSVHelper.getAccountsFromFile(csvPath);
    }

    public void archiveAccountIfExist(Account account) throws IOException, NoSuchAccountException {
        List<Account> accounts = CSVHelper.getAccountsFromFile(csvPath);
        if (accounts.remove(account)) {
            CSVHelper.saveAccountsToFile(csvPath, accounts);
        } else {
            throw new NoSuchAccountException();
        }
    }

    public void addMoney(Account account, int money) throws IOException, NoSuchAccountException, NotEnoughMoneyException {
        List<Account> accounts = CSVHelper.getAccountsFromFile(csvPath);
        int index = accounts.indexOf(account);
        if (index == -1) {
            throw new NoSuchAccountException();
        }
        if (account.getMoney() + money < 0) {
            throw new NotEnoughMoneyException();
        }
        Account removed = accounts.remove(index);
        accounts.add(index, new Account(removed.getPhoneNumber(), removed.getMoney() + money));
        CSVHelper.saveAccountsToFile(csvPath, accounts);
    }

    public void addAccount(Account account) throws IOException, NoSuchAccountException {
        List<Account> accounts = CSVHelper.getAccountsFromFile(csvPath);
        accounts.add(account);
        CSVHelper.saveAccountsToFile(csvPath, accounts);
    }
}
