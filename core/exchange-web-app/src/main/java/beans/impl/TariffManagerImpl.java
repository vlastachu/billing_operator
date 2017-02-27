package beans.impl;

import beans.TariffManager;
import model.Tariff;

import javax.ejb.Stateless;

@Stateless
public class TariffManagerImpl implements TariffManager {

    @Override
    public Tariff getActualTariff(String phoneNumber) {
        return Tariff.getDefaultLocalTariff();
    }
}
