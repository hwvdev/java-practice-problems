package distributed.queue.model;

public interface Consumer {
    void message(String event);
}
