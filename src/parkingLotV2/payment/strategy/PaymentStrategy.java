package parkingLotV2.payment.strategy;

import parkingLotV2.entities.Money;
import parkingLotV2.entities.Ticket;

public interface PaymentStrategy {
    Money calculateFee(Ticket ticket);
}
