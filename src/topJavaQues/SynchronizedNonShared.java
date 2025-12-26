package topJavaQues;

public class SynchronizedNonShared {
    private int counter = 0;
    private final Object object = new Object();

    public void increment() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName() + " working independently");
            counter++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        SynchronizedNonShared nonShared = new SynchronizedNonShared();
        // ✅ Each thread has its own object - CONCURRENT access allowed
        Thread t1 = new Thread(() -> nonShared.increment(), "Thread-1");
        Thread t2 = new Thread(() -> nonShared.increment(), "Thread-2");
        Thread t3 = new Thread(() -> nonShared.increment(), "Thread-3");
        t1.start();t2.start();t3.start();
        t1.join();t2.join();t3.join();
        System.out.println(nonShared.counter);
    }
}