package topJavaQues;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class MutexVsSemaphore {
    private final Object mutex = new Object();
    private final Semaphore semaphore = new Semaphore(4); // Allow 3 concurrent
    
    public void mutexExample() {
        synchronized (mutex) {  // Only ONE thread here
            System.out.println(Thread.currentThread().getName() + " in critical section");
        }
    }
    
    public void semaphoreExample() throws InterruptedException {
        semaphore.acquire();  // Up to 3 threads here
        try {
            System.out.println(Thread.currentThread().getName() + " in semaphore section");
            Thread.sleep(2000);
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MutexVsSemaphore mutexVsSemaphore = new MutexVsSemaphore();

        int count =100;
        Thread[] t = new Thread[count];

        Map<String, Object> keyLock = new ConcurrentHashMap<>();
        String userName = "vijay.dev@db.com";
        System.out.println(keyLock.compute(userName, (k, v) -> {
            if (v == null) v = new Object();
            return v;
        }));
        System.out.println(keyLock.get(userName));

        System.out.println(keyLock.computeIfAbsent(userName, v-> new Object()));
        System.out.println(keyLock.computeIfPresent(userName, (k,v) -> new Object()));
        System.out.println(keyLock.computeIfAbsent(userName, v-> new Object()));

    }
}