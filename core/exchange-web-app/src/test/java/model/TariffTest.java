package model;

import model.entities.Call;
import org.junit.Test;

import static org.junit.Assert.*;

public class TariffTest {
    @Test
    public void getDefaultLocalTariff() throws Exception {
        assertNotNull(Tariff.getDefaultLocalTariff());
    }

    @Test
    public void getCallCost() throws Exception {
        Tariff freeTariff = new Tariff(0);
        Tariff notFreeTariff = new Tariff(100);
        Call sampleCall = new Call("1", 20);
        assertEquals(0, freeTariff.getCallCost(sampleCall));
        assertEquals(2000, notFreeTariff.getCallCost(sampleCall));
    }
}