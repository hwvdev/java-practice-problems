package parkingLotV2.paymentStrategy;

import parkingLotV2.entities.Money;
import parkingLotV2.entities.Ticket;

public interface PaymentStrategy {
    Ticket pay(Ticket ticket);
}
