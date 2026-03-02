package parkingLotV2.paymentStrategy.impl;

import parkingLotV2.entities.Money;
import parkingLotV2.entities.Ticket;
import parkingLotV2.paymentStrategy.PaymentStrategy;

import java.math.BigDecimal;

public class FlatRate implements PaymentStrategy {
    private final Money flatRate;

    public FlatRate(BigDecimal moneyValue, String ccy) {
        this.flatRate = new Money(moneyValue, ccy);
    }

    @Override
    public Ticket pay(Ticket ticket) {
        long lapse = ticket.getExitTime() - ticket.getEntryTime();
        Money charge = new Money(flatRate.getValue().multiply(new BigDecimal(Math.ceil(lapse*1.0/1000))), "Rs.");
        ticket.setParkingFee(charge);
        return ticket;
    }
}
