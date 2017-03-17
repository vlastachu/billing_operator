package model;

import lombok.Data;
import model.entities.Call;

@Data
public class Tariff {
    private final int moneyPerMinute;
    private final static Tariff defaultLocalTariff = new Tariff(10);

    public static Tariff getDefaultLocalTariff() {
        return defaultLocalTariff;
    }

    public int getCallCost(Call call) {
        return call.getDuration() * moneyPerMinute;
    }
}
