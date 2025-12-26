package topJavaQues;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerTemplate {
    private volatile Queue<Integer> buffer = new LinkedList<>();
    private final int capacity =10;
    private final Object lock = new Object();
    volatile int item = 1;

    public void produce() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                while (item == 199) {
                    lock.wait();
                    return;
                }
                while (buffer.size() == capacity) {  // ✅ Wait if FULL
                    lock.wait();
                }
                buffer.add(item);                    // ✅ Do work
                System.out.println("added: " + item);
                item++;
                lock.notifyAll();                    // ✅ Notify consumers
            }
        }
    }
    
    public void consume() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                while (buffer.isEmpty()) {           // ✅ Wait if EMPTY
                    if (item == 199) return;
                    lock.wait();
                }
                int item = buffer.remove();         // ✅ Do work
                System.out.println("removed: " + item);
                lock.notifyAll();                    // ✅ Notify producers
//                return item;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerTemplate p = new ProducerConsumerTemplate();
        new Thread(() -> {
            try {
                p.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                p.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        Thread.sleep(100);
    }
}