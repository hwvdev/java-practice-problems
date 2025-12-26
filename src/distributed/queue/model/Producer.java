package distributed.queue.model;

public interface Producer {
    public void publish(String message, String topic);
}
