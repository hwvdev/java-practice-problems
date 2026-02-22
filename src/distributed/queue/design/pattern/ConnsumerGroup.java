package distributed.queue.design.pattern;

import distributed.queue.model.Consumer;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class ConnsumerGroup implements Consume {
    private final List<Consumer> consumerList = new CopyOnWriteArrayList<>();
    private final BlockingQueue<String> messageQueue;

    public ConnsumerGroup(BlockingQueue<String> mq) {
        this.messageQueue = mq;
    }

    public void register(Consumer c) {
        consumerList.add(c);
    }

    public void consume() {
        Thread t = new Thread(() -> {
            while (true) {
                if (messageQueue.isEmpty())
                    continue;
                String message = null;
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

}
