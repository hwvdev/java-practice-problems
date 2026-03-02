package parkingLotV2.payment;

import parkingLotV2.entities.Money;
import parkingLotV2.entities.Ticket;
import parkingLotV2.payment.processor.PaymentProcessor;
import parkingLotV2.payment.processor.PaymentProcessorRegistry;
import parkingLotV2.payment.strategy.PaymentStrategy;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentService {
    private PaymentStrategy paymentStrategy;
    private final PaymentProcessorRegistry paymentProcessorRegistry;
    private final Map<String, PaymentResponse> paymentStatus = new ConcurrentHashMap<>();

    public PaymentService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
        this.paymentProcessorRegistry = new PaymentProcessorRegistry();
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public Optional<PaymentResponse> processPayment(Ticket ticket, PaymentMethod paymentMethod) throws InterruptedException {
        Money amount = paymentStrategy.calculateFee(ticket);
        int attempt = 0;
        PaymentProcessor paymentProcessor = paymentProcessorRegistry.getPaymentProcessor(paymentMethod);
        Optional<PaymentResponse> paymentResponse = Optional.empty();

        while (attempt <= 3) {
            String idempotentKey = "Key:" + ticket.getTicketId() + ":Retry:" + attempt;
            PaymentRequest paymentRequest = new PaymentRequest(ticket.getTicketId(), idempotentKey, paymentMethod, amount);

            paymentResponse = Optional.of(paymentProcessor.pay(paymentRequest));
            paymentStatus.put(paymentResponse.get().getTxnId(), paymentResponse.get());
            if (paymentResponse.isPresent()) {
                if (PaymentStatus.SUCCESS.equals(paymentResponse.get().getPaymentStatus())) {
                    break;
                } else if (PaymentStatus.FAILURE.equals(paymentResponse.get().getPaymentStatus())) {
                    attempt++;
                    Thread.sleep(4000);
                } else {
                    // rollback payment
                    Thread.interrupted();
                }
            } else {
                attempt++;
                Thread.sleep(3000);
            }
        }
        return paymentResponse;
    }

}
