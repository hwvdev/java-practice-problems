package distributed.queue.design.pattern;

import distributed.queue.model.Consumer;

import java.util.List;
import java.util.concurrent.*;

public class ConnsumerGroup implements Consume {
    private final List<Consumer> consumerList = new CopyOnWriteArrayList<>();
    private final BlockingQueue<String> messageQueue;
    private final ExecutorService executorService;
    private boolean running = true;

    public ConnsumerGroup(BlockingQueue<String> mq) {
        this.messageQueue = mq;
        this.executorService = Executors.newFixedThreadPool(5);
    }

    public void register(Consumer c) {
        consumerList.add(c);
    }

    public void consume() {

        Thread t = new Thread(() -> {
            while (true) {
                if (messageQueue.isEmpty())
                    continue;
                String message;
                try {
                    message = messageQueue.poll(2, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (Consumer consumer: consumerList) {
                    consumer.message(message);
                }
            }
        });
        t.start();
    }

    public void consume1msgPerConsumer() {
        for (Consumer consumer: consumerList) {
            Runnable r = () -> {
                while (running && !Thread.currentThread().isInterrupted()) {
                    String message;
                    try {
                        message = messageQueue.take();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                    consumer.message(message);
                }
                System.out.println("Terminating Thread");
            };
            executorService.submit(r);
        }
    }

    public void shutdown() {
        System.out.println("shutdown triggered");
        running = false;
        executorService.shutdown();
    }


}
