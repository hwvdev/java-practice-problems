import topJavaQues.ThreadingExam;

import java.util.*;

public class test {
//"aabbccc"
//cccaabb

// aabbccca
// cccaabba

    public static void main(String[] args) throws InterruptedException {
        // get("bbaacccb");
        // get("bbaacccc");


        Thread thread1 = new Thread(() -> System.out.println(get("bbaaccca")), "Thread-1");
        Thread thread2 = new Thread(() -> System.out.println(get("bbaacca")), "Thread-2");
        Thread thread3 = new Thread(() -> System.out.println(get("bbaacccmp")), "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();

        Thread.sleep(1000);
    }

    public static String get(String input) {
        String ans = "";
        CharFre charFre = null;
        List<CharFre> charFreList = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (i == 0) {
                charFre = new CharFre(input.charAt(i), 1);
                continue;
            }
            if (input.charAt(i) == input.charAt(i - 1)) {
                charFre.count++;
            } else {
                charFreList.add(charFre);
                charFre = new CharFre(input.charAt(i), 1);
            }
        }
        charFreList.add(charFre);

//        Comparator
        Comparator<CharFre> c = (o1, o2) -> {
            if (o1.count == o2.count && o1.ch != o2.ch)
                return o1.ch - o2.ch;
            return o2.count - o1.count;

        };

        Collections.sort(charFreList);

        for (CharFre charFre1 : charFreList) {
            int count = charFre1.count;
            while (count-- > 0)
                ans += charFre1.ch;
        }

        System.out.println(charFreList);
        return ans;
    }

    public static class CharFre implements Comparable<CharFre> {
        char ch;
        int count;

        public CharFre(char c, int count) {
            this.ch = c;
            this.count = count;
        }

        @Override
        public String toString() {
            return "[" + this.ch + "," + this.count + "]";
        }

        @Override
        public int compareTo(CharFre o) {
            if (this.count == o.count) return this.ch - o.ch;
            return o.count - this.count;
        }
    }


    public static class FixedCache {
        private static final int MAX_SIZE = 1000;
        private static LinkedHashMap<String, Object> cache =
                new LinkedHashMap<String, Object>(MAX_SIZE, 0.75f, true) {
                    @Override
                    public boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
                        return size() > MAX_SIZE;
                    }
                };
    }

}
