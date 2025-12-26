package topJavaQues;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test2 {
    int count = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);

    public void test_1() {
        System.out.println("Inside test_1");
        lock.writeLock().lock();
        try {
            System.out.println("synch: " + Thread.currentThread().getName());
            count++;
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void test_2() {
        System.out.println("Inside test_2");
        lock.writeLock().lock();
        try {
            System.out.println("synch: " + Thread.currentThread().getName());
            count++;
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void test_3() {
        System.out.println("Inside test_3");
        lock.readLock().lock();
        try {
            System.out.println("synch: " + Thread.currentThread().getName());
//            count++;
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Test2 test2 = new Test2();
        Thread t1 = new Thread(test2::test_1, "THREAD-1");
        Thread t2 = new Thread(test2::test_2, "THREAD-2");
        Thread t3 = new Thread(test2::test_3, "THREAD-3");
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
        System.out.println(test2.count);
    }

}
