package parkingLotV2.paymentStrategy;

import parkingLotV2.entities.Ticket;

public interface PaymentStrategy {
    public Double pay(Ticket ticket);
}
