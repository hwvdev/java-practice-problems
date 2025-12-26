package topJavaQues;

import java.util.concurrent.*;
import java.util.function.Function;

class ProducerConsumer {
    private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
    
    class Producer implements Runnable {
        public void run() {
            int value = 0;
            while (true) {
                try {
                    queue.put(value); // Blocks if queue is full
                    System.out.println("Produced: " + value);
                    value++;
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
    
    class Consumer implements Runnable {
        public void run() {
            while (true) {
                try {
                    Integer value = queue.take(); // Blocks if queue is empty
                    System.out.println("Consumed: " + value);
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
    
    public void start() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(new Producer());
        executor.submit(new Consumer());
    }

    public static void main(String[] args) {
//        ProducerConsumer pc = new ProducerConsumer();
  //      pc.start();
        ProducerConsumer pc = new ProducerConsumerBuilder().name("vijay").age(23).build();
        Function<String, Integer> parser = (obj) -> Integer.valueOf(obj);
//        Function<String, Integer> after = (obj) -> obj;

//        System.out.println(parser.andThen());

    }

    private final String name;
    private final int age;


    public ProducerConsumer(ProducerConsumerBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    public ProducerConsumer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static class ProducerConsumerBuilder {
        private String name;
        private int age;

        public ProducerConsumerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProducerConsumerBuilder age(int age) {
            this.age = age;
            return this;
        }

        public ProducerConsumer build() {
            return new ProducerConsumer(this);
        }

    }

}