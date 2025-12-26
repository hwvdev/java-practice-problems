package distributed.queue;

import distributed.queue.design.pattern.ConnsumerGroup;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TopicManager {
    ConcurrentMap<String, BlockingQueue<String>> topicQueuMap = new ConcurrentHashMap<>();

    public BlockingQueue<String> getQueue(String topic) {
        return topicQueuMap.computeIfAbsent(topic, t -> new ArrayBlockingQueue<>(10));
    }

}
