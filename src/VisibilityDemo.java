import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class VisibilityDemo {

    static class Store {
        private int value;
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        int read() {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " Inside Read");
            try {
                return value;
            } finally {
                readLock.unlock();
                System.out.println(Thread.currentThread().getName() + " Exited Read");
            }
        }

        void write(int newValue) {
            writeLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " Inside Write");
                value = newValue;
            } finally {
                System.out.println(Thread.currentThread().getName() + " Exited Write");
                writeLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws Exception {

        List<String> words = List.of("apple", "banana", "apricot", "cherry", "avocado");

        System.out.println(
        words.stream().collect(Collectors.groupingBy(
                s-> s.charAt(0),
                Collectors.maxBy(Comparator.comparing(String::length))
        )));

        List<String> paths = List.of("/a/b/c", "/a/b/d", "/a/e", "/x/y");
        System.out.println(
        paths.stream().collect(Collectors.groupingBy(
                s -> s.split("/")[1],
                Collectors.mapping(str -> str.substring(2), Collectors.toList())
                ))
        );
        User user = new User("34", "vijay");
        User user1 = new User("34", "vijay");
        System.out.println(user.equals(user));

//        System.out.println(user1.hashCode());

    }
    static class User {
        String id;
        String email;

        public User(String id, String email) {
            this.id = id;
            this.email = email;
        }
    }

}
