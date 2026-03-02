package parkingLotV2.payment;

import parkingLotV2.entities.Money;

public record PaymentResponse(PaymentStatus paymentStatus, String txnId, String failureReason, Money amount) {
}
