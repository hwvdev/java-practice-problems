package parkingLotV2.payment;

import parkingLotV2.entities.Money;

public class PaymentResponse {
    private final PaymentStatus paymentStatus;
    private final String txnId;
    private final String failureReason;
    private final Money amount;

    public PaymentResponse(PaymentStatus paymentStatus, String txnId, String failureReason, Money amount) {
        this.paymentStatus = paymentStatus;
        this.txnId = txnId;
        this.failureReason = failureReason;
        this.amount = amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public String getTxnId() {
        return txnId;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public Money getAmount() {
        return amount;
    }
}
