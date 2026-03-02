package parkingLotV2.paymentStrategy;

import parkingLotV2.entities.Ticket;

public interface PaymentStrategy {
    Double pay(Ticket ticket);
}
