package distributed.queue.design.pattern;

import distributed.queue.model.Consumer;

import java.util.List;
import java.util.concurrent.*;

public class ConnsumerGroup implements Consume {
    private final List<Consumer> consumerList = new CopyOnWriteArrayList<>();
    private final BlockingQueue<String> messageQueue;
    private final ExecutorService executorService;

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
                while (true) {
                    String message;
                    try {
                        message = messageQueue.take();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    consumer.message(message);
                }
            };
            executorService.submit(r);
        }
    }

}
