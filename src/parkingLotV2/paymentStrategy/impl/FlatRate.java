package parkingLotV2.paymentStrategy.impl;

import parkingLotV2.entities.Ticket;
import parkingLotV2.paymentStrategy.PaymentStrategy;

public class FlatRate implements PaymentStrategy {
    private final Double flatRate;

    public FlatRate(Double flatRate) {
        this.flatRate = flatRate;
    }

    @Override
    public Double pay(Ticket ticket) {
        switch (ticket.spotType()) {
            case BIKE -> {
                return 50.0;
            }
            case TRUCK -> {
                return 100.0;
            }
            case CAR -> {
                return 70.0;
            }
        }
        return flatRate;
    }
}
