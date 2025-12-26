public class OddThread extends Thread {
    OddThread() {
        super("OddThread");
    }
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            if (i%2!=0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    sleep(1);
                } catch (InterruptedException e) {
       //             Thread.currentThread().interrupt();
                    notify();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
