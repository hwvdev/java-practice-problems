package parkingLotV2.payment;

import parkingLotV2.entities.Money;

public record PaymentRequest(String ticketId, String idempotentKey, PaymentMethod paymentMethod, Money amount) {
}
