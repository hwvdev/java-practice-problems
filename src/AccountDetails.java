
public class AccountDetails {
    long amount;
    String currency;
    String user;

    public synchronized void updateAmount() {
        amount = amount + 100000;
    }

    public static void main(String[] args) {
    }

}
