import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

class Solution {

    // no of stairs to climb n=2
    //constraint 1 jump or 2 jump
    // return no of combinations to climb all stairs

    // n = 3

    public static void main(String[] args) {
        int n  = 34000;
        int []ways = new int[n+1];
        ways[1] = 1;
        ways[2] = 2;
        List<List<Integer>> ans = new ArrayList<>();

        for (int i=3;i<=n; i++) {
            ways[i] = ways[i-1]+ways[i-2];
        }
        List<Integer> path = new ArrayList<>();
        rec(0, n, path, ans);

        System.out.println(ans);
        System.out.println(ways[n]);


    }

    public static void rec(int i, int n, List<Integer> path, List<List<Integer>> ans) {
        if(i==n) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (i>n) return;
        path.add(1);
        rec(i+1, n, path, ans);
        path.remove(path.size()-1);

        path.add(2);
        rec(i+2, n, path, ans);
        path.remove(path.size()-1);

        Employee e = new Employee(12, new ArrayList<>());
        e.get();


    }

    public final static class Employee implements Cloneable{
        private int id;
        private List<Integer> marks;

        public Employee(int id, List<Integer> marks) {
            this.id = id;
            this.marks = marks;
        }

        public int getId() {
            return id;
        }

        public List<Integer> getMarks() {
            return new ArrayList<>(marks);
        }

        public static String get() {
            return "";
        }
    }

}