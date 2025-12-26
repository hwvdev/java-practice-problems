package distributed.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;

public class TopicManager {
    ConcurrentMap<String, BlockingQueue<String>> topicQueuMap = new ConcurrentHashMap<>();

    public BlockingQueue<String> getQueue(String topic) {
        return topicQueuMap.computeIfAbsent(topic, t -> new LinkedBlockingDeque<>(10));
    }

}
