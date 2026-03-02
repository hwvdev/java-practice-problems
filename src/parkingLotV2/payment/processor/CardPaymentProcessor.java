package parkingLotV2.payment.processor;

import parkingLotV2.entities.Money;
import parkingLotV2.payment.PaymentMethod;
import parkingLotV2.payment.PaymentRequest;
import parkingLotV2.payment.PaymentResponse;
import parkingLotV2.payment.PaymentStatus;

import java.util.UUID;

public class CardPaymentProcessor implements PaymentProcessor {

    @Override
    public PaymentMethod paymentMethod() {
        return PaymentMethod.CARD;
    }

    @Override
    public PaymentResponse pay(PaymentRequest paymentRequest) {
        System.out.println("Paying through CARD");
        return new PaymentResponse(PaymentStatus.SUCCESS, UUID.randomUUID().toString(), "", paymentRequest.getAmount());
    }
}
