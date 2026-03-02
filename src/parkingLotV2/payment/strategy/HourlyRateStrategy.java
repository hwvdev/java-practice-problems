package parkingLotV2.payment.strategy;

import parkingLotV2.entities.Money;
import parkingLotV2.entities.Ticket;
import parkingLotV2.payment.StrategyMethod;

import java.math.BigDecimal;

public class HourlyRateStrategy implements PaymentStrategy {
    private final Money hourlyRate;

    public HourlyRateStrategy(Money hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public Money calculateFee(Ticket ticket) {
        long entryTime = ticket.getEntryTime();
        long exitTime = ticket.getExitTime();
        BigDecimal amount = hourlyRate.getValue().multiply(new BigDecimal(Math.ceil((exitTime - entryTime)/60_000.0)));
        return new Money(amount, hourlyRate.getCurrency());
    }
}
