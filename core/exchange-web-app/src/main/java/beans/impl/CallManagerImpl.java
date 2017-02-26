package beans.impl;

import beans.CallManager;
import model.entities.Call;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CallManagerImpl implements CallManager {

    @PersistenceContext(unitName = "em")
    private EntityManager em;

    @Override
    public List<Call> getCalls() {
        Query query = em.createNamedQuery("Call.getAll");
        return (List<Call>)query.getResultList();
    }

    @Override
    public List<Call> getCallsByPhoneNumber(String phoneNumber) {
        Query query = em.createNamedQuery("Call.getAllByPhoneNumber").setParameter("phoneNumber", phoneNumber);
        return (List<Call>)query.getResultList();
    }

    @Override
    public void addCall(Call call) {
        em.persist(call);
    }
}
