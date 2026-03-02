package parkingLotV2.payment.processor;

import parkingLotV2.payment.PaymentMethod;
import parkingLotV2.payment.PaymentRequest;
import parkingLotV2.payment.PaymentResponse;

public interface PaymentProcessor {
    PaymentMethod paymentMethod();
    PaymentResponse pay(PaymentRequest paymentRequest);
}
