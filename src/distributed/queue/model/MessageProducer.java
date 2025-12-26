package distributed.queue.model;

import distributed.queue.TopicManager;

import java.util.concurrent.BlockingQueue;

public class MessageProducer implements Producer {
    private final TopicManager topicManager;

    public MessageProducer(TopicManager topicManager) {
        this.topicManager = topicManager;
    }

    @Override
    public void publish(String message, String topic) {
        System.out.println("publish message on topic: " + topic +" Message = "+message);
        BlockingQueue<String> blockingQueue = topicManager.getQueue(topic);
        blockingQueue.add(message);
    }
}
