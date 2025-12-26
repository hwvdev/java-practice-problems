package creational.patterns;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AbstractFactory {
    enum PAYMENT_METHOD{CREDIT_CARD, BANKING, UPI, WALLET, DEBIT_CARD};

    public static void main(String[] args) throws Exception {
        PaymentFactory paymentFactory = createPaymentFactory(true);
        Processor processor = paymentFactory.getProcessor(PAYMENT_METHOD.UPI);
        processor.process();
    }

    public static PaymentFactory createPaymentFactory(boolean isReg) {
        if (isReg) return new RegisteredPaymentProcessor();
        else return new UnregisteredPaymentProcessor();
    }

    public static class UnregisteredPaymentProcessor implements PaymentFactory {
        private static Map<PAYMENT_METHOD, Processor> processorMap = new HashMap<>();
        static {
            processorMap.putIfAbsent(PAYMENT_METHOD.CREDIT_CARD, new UnRegCCProcessor());
            processorMap.putIfAbsent(PAYMENT_METHOD.BANKING, new UnRegBankingProcessor());
            processorMap.putIfAbsent(PAYMENT_METHOD.UPI, new UnRegUPIProcessor());
            processorMap.putIfAbsent(PAYMENT_METHOD.WALLET, new UnRegWalletProcessor());
            processorMap.putIfAbsent(PAYMENT_METHOD.DEBIT_CARD, new UnRegDebitCardProcessor());
        }
        public Processor getProcessor(PAYMENT_METHOD method) {
            return processorMap.getOrDefault(method, ()-> {throw new Exception("No such unregistered payment method");});
        }
    }
    public static class RegisteredPaymentProcessor implements PaymentFactory {
        private static Map<PAYMENT_METHOD, Supplier<Processor>> processorMap = new HashMap<>();
        static {
            processorMap.putIfAbsent(PAYMENT_METHOD.CREDIT_CARD, RegBankingProcessor::new);
            processorMap.putIfAbsent(PAYMENT_METHOD.BANKING, RegBankingProcessor::new);
            processorMap.putIfAbsent(PAYMENT_METHOD.UPI, RegUPIProcessor::new);
            processorMap.putIfAbsent(PAYMENT_METHOD.WALLET, RegWalletProcessor::new);
            processorMap.putIfAbsent(PAYMENT_METHOD.DEBIT_CARD, RegDebitCardProcessor::new);
        }
        public Processor getProcessor(PAYMENT_METHOD method) {
            return processorMap.getOrDefault(method, () -> {
                try {
                    throw new Exception("No such registered payment method exists");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).get();
        }
    }
    public interface PaymentFactory {
        Processor getProcessor(PAYMENT_METHOD method);
    }

    public static class UnRegDebitCardProcessor implements Processor {
        public void process() {
            System.out.println("Inside Un Debit Card Payment Processor");
        }
    }
    public static class UnRegWalletProcessor implements Processor {
        public void process() {
            System.out.println("Inside Un Wallet Payment Processor");
        }
    }
    public static class UnRegBankingProcessor implements Processor {
        public void process() {
            System.out.println("Inside Un Banking Payment Processor");
        }
    }
    public static class UnRegCCProcessor implements Processor {
        public void process() {
            System.out.println("Inside Un Credit card Payment Processor");
        }
    }
    public static class UnRegUPIProcessor implements Processor {
        public void process() {
            System.out.println("Inside Un UPI payment Processor");
        }
    }
    public static class RegDebitCardProcessor implements Processor {
        public void process() {
            System.out.println("Inside Debit Card Payment Processor");
        }
    }
    public static class RegWalletProcessor implements Processor {
        public void process() {
            System.out.println("Inside Wallet Payment Processor");
        }
    }
    public static class RegBankingProcessor implements Processor {
        public void process() {
            System.out.println("Inside Banking Payment Processor");
        }
    }
    public static class RegCCProcessor implements Processor {
        public void process() {
            System.out.println("Inside Credit card Payment Processor");
        }
    }
    public static class RegUPIProcessor implements Processor {
        public void process() {
            System.out.println("Inside UPI payment Processor");
        }
    }
    public interface Processor {
        public void process() throws Exception;
    }
}
