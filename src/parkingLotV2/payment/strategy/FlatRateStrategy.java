package parkingLotV2.payment.strategy;

import parkingLotV2.entities.Money;
import parkingLotV2.entities.Ticket;

import java.math.BigDecimal;

public class FlatRateStrategy implements PaymentStrategy {
    public final Money flatRate;

    public FlatRateStrategy(BigDecimal amount, String ccy) {
        flatRate = new Money(amount, ccy);
    }

    @Override
    public Money calculateFee(Ticket ticket) {
        return flatRate;
    }

}
