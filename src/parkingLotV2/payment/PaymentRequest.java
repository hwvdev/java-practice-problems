package parkingLotV2.payment;

import parkingLotV2.entities.Money;

public class PaymentRequest {
    private final String ticketId;
    private final String idempotentKey;
    private final PaymentMethod paymentMethod;
    private final Money amount;

    public PaymentRequest(String ticketId, String idempotentKey, PaymentMethod paymentMethod, Money amount) {
        this.ticketId = ticketId;
        this.idempotentKey = idempotentKey;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getIdempotentKey() {
        return idempotentKey;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Money getAmount() {
        return amount;
    }
}
