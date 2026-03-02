package parkingLotV2.payment;

import parkingLotV2.entities.Money;
import parkingLotV2.entities.Ticket;
import parkingLotV2.payment.processor.PaymentProcessor;
import parkingLotV2.payment.processor.PaymentProcessorRegistry;
import parkingLotV2.payment.strategy.PaymentStrategy;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentService {
    private final PaymentProcessorRegistry paymentProcessorRegistry;
    private final Map<String, PaymentResponse> paymentStatus = new ConcurrentHashMap<>();
    private PaymentStrategy paymentStrategy;

    public PaymentService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
        this.paymentProcessorRegistry = new PaymentProcessorRegistry();
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public Money calcFee(Ticket ticket) {
        return paymentStrategy.calculateFee(ticket);
    }

    public PaymentResponse pay(Money amount, PaymentMethod paymentMethod, Ticket ticket) {
        PaymentProcessor paymentProcessor = paymentProcessorRegistry.getPaymentProcessor(paymentMethod);
        String idempotentKey = "Key:" + ticket.getTicketId();
        PaymentRequest paymentRequest = new PaymentRequest(ticket.getTicketId(), idempotentKey, paymentMethod, amount);
        return paymentProcessor.pay(paymentRequest);
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
            paymentStatus.put(paymentResponse.get().txnId(), paymentResponse.get());
            if (paymentResponse.isPresent()) {
                if (PaymentStatus.SUCCESS.equals(paymentResponse.get().paymentStatus())) {
                    break;
                } else if (PaymentStatus.FAILURE.equals(paymentResponse.get().paymentStatus())) {
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
