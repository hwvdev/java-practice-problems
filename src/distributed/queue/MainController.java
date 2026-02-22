package distributed.queue;

import distributed.queue.design.pattern.ConnsumerGroup;
import distributed.queue.model.Consumer;
import distributed.queue.model.MessageConsumer;
import distributed.queue.model.Producer;
import distributed.queue.model.MessageProducer;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class MainController {
    public static void main(String[] args) throws InterruptedException {
        TopicManager topicManager = new TopicManager();

        Producer producer1 = new MessageProducer(topicManager);
        Producer producer2 = new MessageProducer(topicManager);

        String topic1 = "TOPIC1";
        String topic2 = "TOPIC2";

        BlockingQueue<String> queue1 = topicManager.getQueue(topic1);
        BlockingQueue<String> queue2 = topicManager.getQueue(topic2);

        ConnsumerGroup connsumerGroup1 = new ConnsumerGroup(queue1);
        ConnsumerGroup connsumerGroup2 = new ConnsumerGroup(queue2);

        Consumer consumer1 = new MessageConsumer();
        Consumer consumer2 = new MessageConsumer();
        Consumer consumer3 = new MessageConsumer();
        Consumer consumer4 = new MessageConsumer();
        Consumer consumer5 = new MessageConsumer();

        connsumerGroup1.register(consumer1);
        connsumerGroup1.register(consumer2);
        connsumerGroup1.register(consumer3);
        connsumerGroup1.register(consumer4);
        connsumerGroup1.register(consumer5);

        connsumerGroup2.register(consumer1);
        connsumerGroup2.register(consumer3);
        connsumerGroup2.register(consumer5);

        connsumerGroup1.consume1msgPerConsumer();
        connsumerGroup2.consume1msgPerConsumer();

        String message1 = "Message1";
        String message2 = "Message2";
        String message3 = "Message3";
        String message4 = "Message4";
        String message5 = "Message5";

        producer1.publish(message1, topic1);
        producer2.publish(message5, topic2);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String message = sc.nextLine();
            producer1.publish(message, topic1);
        }
    }
}
