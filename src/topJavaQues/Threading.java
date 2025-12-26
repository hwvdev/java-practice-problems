package topJavaQues;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Threading extends Thread {

    @Override
    public void run() {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance.hashCode());
    }

    public static void main(String[] args) throws InterruptedException {
        final int THREAD_COUNT = 100;

        Threading t1 = new Threading();
        Threading t2 = new Threading();
        Threading t3 = new Threading();

        t1.start();
        t2.start();
        t3.start();

        ImmutablePerson ip = new ImmutablePerson("vij",56);
        ImmutablePerson ip2 = new ImmutablePerson("pooja", 45);

        List<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(324);
        Iterator<Integer> it = list.iterator();
        System.out.println(it.next());
        System.out.println(it.next());

        Pair p1 = new Pair(12, 10);
        Pair p2 = new Pair(6, 8);
        Pair p3 = new Pair(2, 3);
        Pair p4 = new Pair(5, 5);
        Pair p5 = new Pair(50, 23);
        Pair p6 = new Pair(45, 55);

        HashSet<Pair> set = new HashSet<>();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);
        set.add(p5);
        set.add(p6);
        System.out.println("hash "+set.hashCode());
        System.out.println("hash " + new HashSet<>(set));

        SortedSet<Integer> integerSet = new TreeSet<>();
        integerSet.add(23); integerSet.add(12); integerSet.add(65);
        System.out.println(integerSet);


        NavigableSet<Pair> navigableSet = new TreeSet<>((a,b) -> a.val - b.val);
        navigableSet.add(new Pair(2,5));
        navigableSet.add(new Pair(41,3));
        navigableSet.add(new Pair(3,4));
        navigableSet.add(new Pair(6,1));
        navigableSet.add(new Pair(5,2));
        System.out.println(navigableSet);

        Map<Integer, Integer> map = new TreeMap<>();
        map.put(2,4);
        map.put(8,1);
        map.put(34,2);
        map.put(6,10);
        map.put(5,3);

        System.out.println(map);
    }

    public record Pair(int val, int freq){}
}
