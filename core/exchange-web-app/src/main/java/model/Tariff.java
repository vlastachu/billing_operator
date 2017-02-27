package model;

import model.entities.Call;

public class Tariff {
    private final int moneyPerMinute;
    private final static Tariff defaultLocalTariff = new Tariff(10);

    public Tariff(int moneyPerMinute) {
        this.moneyPerMinute = moneyPerMinute;
    }

    public int getMoneyPerMinute() {
        return moneyPerMinute;
    }

    public static Tariff getDefaultLocalTariff() {
        return defaultLocalTariff;
    }

    public int getCallCost(Call call) {
        return call.getDuration() * moneyPerMinute;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "moneyPerMinute=" + moneyPerMinute +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tariff tariff = (Tariff) o;

        return moneyPerMinute == tariff.moneyPerMinute;
    }

    @Override
    public int hashCode() {
        return moneyPerMinute;
    }
}
