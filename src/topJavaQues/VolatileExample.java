package topJavaQues;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VolatileExample {
    private volatile boolean flag = false;
    private int data = 0;
    
    public void writer() throws InterruptedException {
        data = 42;      // (1)
        flag = true;    // (2) - volatile write
  //      Thread.sleep(10);
    }

    public void Example() {
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(String.valueOf(i), "String: " + i);
        }

        if (map.containsKey("5")) {
            String str = map.get("5");
            if(str.equals("5"))
                System.out.println(str);
        }
    }
    
    public void reader() throws InterruptedException {
        if (flag) {     // (3) - volatile read
            // Guaranteed to see data = 42
            System.out.println(data); // (4)
        }
        System.out.println(data); // (4)

//        Thread.sleep(10);
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileExample v = new VolatileExample();
        Thread t1 = new Thread(()->{
            try {
                v.reader();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(()->{
            try {
                v.writer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start(); t2.start();
        t1.join();
        t2.join();
    }
}