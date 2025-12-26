package topJavaQues;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class DataProcessor<N extends Number, S extends String> {

    private static Map<String, List<Event>> topicEventMap = new ConcurrentHashMap<>();
    private static Map<String, List<String>> subscribers = new ConcurrentHashMap<>();

    public static class Event {
        final String name;
        final String email;

        public Event(String name, String email) {
            this.email = email;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

        public static void main(String[] args) {
        DataProcessor dataProcessor = new DataProcessor();
        String topic1 = "meri Marzi";
        String topic2 = "teri Marzi";
//            Supplier
        List list = new ArrayList<>();
        list.add("vijay");
        list.add(123);

            System.out.println(list);

        subscribe(topic1, "vijay subs to topic1");
        subscribe(topic1, "random subs to topic2");

        publish(topic1, new Event("vijay", "vijay@gmail.com"));
        publish(topic1, new Event("test", "test@gmail.com"));
        publish(topic2, new Event("ajay", "ajay@gmail.com"));
        publish(topic2, new Event("aman", "aman@gmail.com"));

        subscribe(topic2, "random subs to topic2");

        }

    public static void subscribe(String topic, String email) {
            subscribers.compute(topic, (k,v) -> {
                if (v == null || v.isEmpty()) {
                    v = new ArrayList<>();
                    v.add(email);
                } else
                    v.add(email);
                return v;
            });
    }

    public static void publish(String topic, Event event) {
        broadcast(event, topic);
/*        topicEventMap.compute(topic, (k, v) -> {
            if (v == null || v.isEmpty()) {
                v = new ArrayList<>();
                v.add(event);
            } else
                v.add(event);
            return v;
        });*/
    }

    public static void broadcast(Event event, String topic) {
            subscribers.compute(topic, (k, v) -> {
                if (v == null) System.out.println("topic: " + k + " has no subscribers.");
                else if (v!=null || !v.isEmpty()) {
                    for (String email: v)
                        System.out.println("User: " + email + ", Received event mail: " + event);
                }
                return v;
            });
    }

}