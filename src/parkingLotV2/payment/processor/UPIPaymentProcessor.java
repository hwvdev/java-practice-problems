package parkingLotV2.payment.processor;

import parkingLotV2.payment.PaymentMethod;
import parkingLotV2.payment.PaymentRequest;
import parkingLotV2.payment.PaymentResponse;
import parkingLotV2.payment.PaymentStatus;

import java.util.Random;
import java.util.UUID;

public class UPIPaymentProcessor implements PaymentProcessor {

    @Override
    public PaymentMethod paymentMethod() {
        return PaymentMethod.UPI;
    }

    @Override
    public PaymentResponse pay(PaymentRequest paymentRequest) {
        System.out.println(".... paying through UPI");
        return new PaymentResponse(PaymentStatus.SUCCESS, UUID.randomUUID().toString(), "", paymentRequest.getAmount());
    }

}
