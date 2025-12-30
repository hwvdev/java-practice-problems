import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sol {

    public static void main(String[] args) throws InterruptedException {

        String s = "hug, jkl, Klj, ,,gfg,jkl,  gfg, fd ,,,gfg, gfg";
        Map<String, Long> map = Arrays.stream(s.split(","))
                .map(String::trim)
                .filter(str -> str!="")
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(k -> k, Collectors.counting()));
        Comparator<Map.Entry<String, Long>> comp = Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder());
//        List<Map.Entry<String, Long>> res = map.entrySet().stream().sorted(comp).toList();
        System.out.println(map);

        int MAX = 0;
        final int[] counter = {1};
        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (counter[0] <= MAX) {
                    if (counter[0] % 2 == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println(counter[0]);
                        counter[0]++;
                        lock.notify();
                    }
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                while (counter[0] <= MAX) {
                    if (counter[0] % 2 != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println(counter[0]);
                        counter[0]++;
                        lock.notify();
                    }
                }
            }
        });
        t2.start();

    }
}
