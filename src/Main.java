public class Main {

    public static void main(String[] args) throws InterruptedException {

        AccountDetails accountDetails = new AccountDetails();
        accountDetails.amount = 0;

        Runnable1 runnable1 = new Runnable1(accountDetails);
        Runnable2 runnable2 = new Runnable2(accountDetails);
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Main thread finished: " + accountDetails.amount);

    }
}