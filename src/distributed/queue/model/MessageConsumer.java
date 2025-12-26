package distributed.queue.model;

public class MessageConsumer implements Consumer {

    @Override
    public void message(String event) {
        System.out.println("Consumer: " + event);
    }
}
