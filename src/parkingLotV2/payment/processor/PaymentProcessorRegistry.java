package parkingLotV2.payment.processor;

import parkingLotV2.payment.PaymentMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class PaymentProcessorRegistry {
    private final Map<PaymentMethod, PaymentProcessor> paymentRegistry = new HashMap<>();

    public PaymentProcessorRegistry() {
        paymentRegistry.put(PaymentMethod.UPI, new UPIPaymentProcessor());
        paymentRegistry.put(PaymentMethod.CARD, new CardPaymentProcessor());
    }

    public PaymentProcessor getPaymentProcessor(PaymentMethod paymentMethod) {
        PaymentProcessor p = paymentRegistry.get(paymentMethod);
        if (p == null) throw new IllegalStateException("Undefined payment Method");
        return p;
    }
}
