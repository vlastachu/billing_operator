package beans;

import model.Tariff;

import javax.ejb.Local;

@Local
public interface TariffManager {
    Tariff getActualTariff(String phoneNumber);
}
