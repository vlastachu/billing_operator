package beans.impl;

import beans.AccountManager;
import beans.CallManager;
import model.Account;
import model.Tariff;
import model.entities.Call;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;

@Stateless
public class CallManagerImpl implements CallManager {

    @PersistenceContext(unitName = "em")
    EntityManager em;

    @EJB
    AccountManager accountManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Call> getCalls() {
        Query query = em.createNamedQuery("Call.getAll");
        return (List<Call>)query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Call> getCallsByPhoneNumber(String phoneNumber) {
        Query query = em.createNamedQuery("Call.getAllByPhoneNumber").setParameter("phoneNumber", phoneNumber);
        return (List<Call>)query.getResultList();
    }

    @Override
    public void addCall(Call call) throws IOException, AccountManager.NoSuchAccountException, AccountManager.NotEnoughMoneyException {
        Account account = null;
        System.out.println("call = [" + call + "]");
        for (Account a: accountManager.getAll()) {
            if (a.getPhoneNumber().equals(call.getPhoneNumber())){
                account = a;
            }
        }
        if (account != null) {
            accountManager.addMoney(account, -Tariff.getDefaultLocalTariff().getCallCost(call));
            em.persist(call);
        } else {
            throw new AccountManager.NoSuchAccountException();
        }
    }
}
