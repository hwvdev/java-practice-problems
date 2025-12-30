import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Printer {

    public void print() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0 && Thread.currentThread().getName().equals("Even")) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    notify();
                }
            } else if (i % 2 == 1 && Thread.currentThread().getName().equals("Odd")) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    Thread.sleep(8);
                } catch (InterruptedException e) {
                    notify();
                }
            }
        }
    }

}


