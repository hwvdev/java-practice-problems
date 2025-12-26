package topJavaQues;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ThreadingExam {
    private static int val = 0;
    private static int MAX = 10;
    static StringBuilder sb = new StringBuilder(",");

    public static void main(String[] args) throws InterruptedException {
        Object ob = new Object();
        Thread evenThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": even sync");
            synchronized (ob) {
                System.out.println(Thread.currentThread().getName() + ": even enter");
                while (val < MAX) {
                    if (val % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + val);
                        val++;
                        System.out.println(Thread.currentThread().getName() + " before notify");
                        ob.notify();
                        System.out.println(Thread.currentThread().getName() + " after notify");
                    } else
                        try {
                            System.out.println(Thread.currentThread().getName() + " before wait");
                            ob.wait();
                            System.out.println(Thread.currentThread().getName() + " after wait");
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                }
            }
        }, "Even");

        Thread oddThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": odd sync");
            synchronized (ob) {
                System.out.println(Thread.currentThread().getName() + ": odd enter ");
                while (val < MAX) {
                    if (val % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + val);
                        val++;
                        System.out.println(Thread.currentThread().getName() + " before notify");
                        ob.notify();
                        System.out.println(Thread.currentThread().getName() + " after notify");
                    }else
                    try {
                        System.out.println(Thread.currentThread().getName() + " before wait");
                        ob.wait();
                        System.out.println(Thread.currentThread().getName() + " after wait");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, "Odd");
        oddThread.start();
        evenThread.start();
        oddThread.join();
        evenThread.join();

        merge();
    }

    // Decodes your encoded data to tree.
    public Solution.TreeNode deserialize(String data) {
        String[] input = data.split(",");
        Solution.TreeNode root = new Solution.TreeNode(Integer.valueOf(input[0]));
//        Arrays.copyOfRange(input, 1, input.length).length;
  //      convert(Arrays.copyOfRange(input, 1, input.length), root);
        return root;
    }

    public static void merge() throws InterruptedException {
        // Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        List<Integer> list=new ArrayList<>();
        list.addAll(Arrays.asList(1,4,2,5,7,43,29,3, 43, 3, 5,2));
        list.forEach(o-> {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(o, map.get(o) == null ? 0 : map.get(o)+1);
        });
        Map<Integer, Long> map = list.stream().collect(Collectors.groupingBy(a->a, Collectors.counting()));
        List<Integer> res = map.keySet().stream().filter(o->map.get(o)>1).collect(Collectors.toUnmodifiableList());
        System.out.println(res);
//        return Arrays.copyOfRange(intervals, 0, 3);
        String s = API_SERVERS.get("abc");
        System.out.println(s);
        try {
            API_SERVERS.put("abc", "AS");
        } catch (Exception e) {
            System.out.println("cant");
        }

        Runnable r = () -> {
            try {
                new Test();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread[] t = new Thread[100000];
        for (int i=0;i<100000;i++) {
            t[i] = new Thread(r);
        }
        for (int i = 0; i < 100000; i++) {
            t[i].start();
        }
        for (int i = 0; i < 100000; i++) {
            t[i].join();
        }

        Test ts = new Test();
        System.out.print(ts.getCount());

    }

    public static class Test{
        static protected volatile int count = 0;
        static final Object object = new Object();
        public Test() throws InterruptedException {
            synchronized (Test.class){
                count = count + 1;
            }
        }
        public int getCount() {
            List<Integer> subSum = new ArrayList<>();
            Set<List<Integer>> set = new HashSet<>();
//            Integer.I
            return this.count;
        }
    }

    public final static Map<String, String> API_SERVERS = Map.of("abc", "trc", "gh", "gh");
}
