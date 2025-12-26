package topJavaQues;

public class UniqueIdGenerator {

    private static long id = 1;

    public static long generateId() {
        synchronized (UniqueIdGenerator.class) {
            id++;
        }
        return id;
    }

}
