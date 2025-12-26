package topJavaQues;

import java.util.concurrent.locks.*;

public class ReadWriteLockExample {
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private String data = "Initial";
    
    // ✅ MULTIPLE READERS can access CONCURRENTLY
    public String readData() {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " READING: " + data);
            try { Thread.sleep(500); } catch (InterruptedException e) {}
            return data;
        } finally {
            rwLock.readLock().unlock();
        }
    }
    
    // ❌ ONLY ONE WRITER at a time - EXCLUSIVE access
    public void writeData(String newData) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " WRITING: " + newData);
            data = newData;
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        } finally {
            rwLock.writeLock().unlock();
        }
    }
    
    public static void main(String[] args) {
        ReadWriteLockExample sharedObj = new ReadWriteLockExample();
        
        // ✅ Multiple readers can run CONCURRENTLY
        new Thread(() -> sharedObj.readData(), "Reader-1").start();
        new Thread(() -> sharedObj.readData(), "Reader-2").start();
        new Thread(() -> sharedObj.readData(), "Reader-3").start();
        
        // ❌ Writers block everyone
        new Thread(() -> sharedObj.writeData("Update1"), "Writer-1").start();
        new Thread(() -> sharedObj.writeData("Update2"), "Writer-2").start();
    }
}