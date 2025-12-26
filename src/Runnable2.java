public class Runnable2 implements Runnable {

    private final AccountDetails accountDetails;

    public Runnable2(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }

    @Override
    public void run() {

        for (int i=0;i<1000;i++)
            accountDetails.updateAmount();
    }

}
