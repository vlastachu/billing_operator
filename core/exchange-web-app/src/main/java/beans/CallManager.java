package beans;

import model.entities.Call;

import javax.ejb.Local;
import java.io.IOException;
import java.util.List;

@Local
public interface CallManager {
    List<Call> getCalls();
    List<Call> getCallsByPhoneNumber(String phoneNumaber);
    void addCall(Call call) throws IOException, AccountManager.NoSuchAccountException, AccountManager.NotEnoughMoneyException;
}
