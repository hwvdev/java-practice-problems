package parkingLotV2.payment.strategy;

import parkingLotV2.entities.Money;
import parkingLotV2.entities.Ticket;
import parkingLotV2.payment.StrategyMethod;

public interface PaymentStrategy {
    Money calculateFee(Ticket ticket);
}
