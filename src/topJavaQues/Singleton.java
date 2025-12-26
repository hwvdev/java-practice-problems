package topJavaQues;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Singleton {
    private static volatile Singleton instance;
    private List<Integer> array;

    public static Singleton getInstance() {
        if (instance == null)
            synchronized (Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        return instance;
    }

    public List<Integer> getX() {
        return new ArrayList<>(array);
    }

    public void updateX(List<Integer> list) {
        this.array = new CopyOnWriteArrayList<>(list);
    }

    public static void main(String[] args) {
        Singleton singleton = getInstance();
        singleton.updateX(List.of(2,3,4,5,6));
        System.out.println(singleton.getX());
        singleton.updateX(List.of(6,5,3,7,9));
        System.out.println(singleton.getX());

        Supplier<Integer> supplier = () -> 5;
        Consumer<Integer> consumer = (a) -> {
            System.out.println("print a: " + a);
        };
        //andThen((a));
    }
}
