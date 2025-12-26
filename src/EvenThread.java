public class EvenThread extends Thread {

    EvenThread() {
        super("EvenThread");
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                try {
                    sleep(1);
                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
                    notify();
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
