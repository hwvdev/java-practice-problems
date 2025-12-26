import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class G1ConcurrentMarkDemo {

    public static void main(String[] args) {
        List<String> words = List.of("eat", "tea", "tan", "ate", "nat", "bat");

        Map<String, List<String>> map = new HashMap<>();
        for (String str : words) {
            char[] keychar = str.toCharArray();
            Arrays.sort(keychar);
            String s = new String(keychar);
            map.putIfAbsent(s, new ArrayList<>());
            map.get(s).add(str);
        }
        System.out.println(map);

        List<String> sentences = List.of(
                "I love coding",
                "I love Java",
                "Java is fun"
        );

        Map<String, Long> map1 = sentences.stream().flatMap(str -> Arrays.stream(str.split(" ")))
                .map(String::strip).map(String::toLowerCase).collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        System.out.println(map1);


        List<String> emails = List.of(
                "alice@gmail.com",
                "bob@yahoo.com",
                "charlie@gmail.com",
                "dave@hotmail.com"
        );

        Map<String, List<String>> peopleByCit = emails.stream().map(String::toLowerCase).collect(Collectors
                .groupingBy(email -> email.split("@")[1]));
        System.out.println(peopleByCit);

        Map<String, Integer> salaries = Map.of(
                "Alice", 5000,
                "Bob", 7000,
                "Charlie", 6000,
                "Dave", 7000
        );
        int k = 2;

        Comparator<Map.Entry<String, Integer>> comp = Comparator.comparing(Map.Entry<String, Integer>::getValue, Comparator.reverseOrder())
                .thenComparing(Map.Entry::getKey);
        List<String> names = salaries.entrySet().stream().sorted(comp).limit(2).map(Map.Entry::getKey).toList();
        System.out.println(names);

        int[] nums = {4, 4, 2, 3, 2, 1};

        Map<Integer, Integer> integerMap = new LinkedHashMap<>();
        for (int key : nums) {
            integerMap.put(key, integerMap.getOrDefault(key, 0) + 1);
        }

        PriorityQueue<LFU> priorityQueue = new PriorityQueue<>((a, b) -> {
            if (Integer.compare(a.val, b.val) == 0) {
                return Integer.compare(a.idx, b.idx);
            }
            return Integer.compare(a.val, b.val);
        });

        int idx=0;
        for (Map.Entry<Integer, Integer> entry: integerMap.entrySet()) {
            priorityQueue.add(new LFU(entry.getKey(), entry.getValue(), idx));
            idx++;
        }
        System.out.println(priorityQueue.peek().key);

        List<Integer> nums2 = List.of(10, 40,20, 30, 40);

        int first = Math.max(nums2.get(0), nums2.get(1));
        int sec = Math.min(nums2.get(0), nums2.get(1));

        for (int i = 2; i < nums2.size(); i++) {
            if (nums2.get(i)>=first) {
                sec = first;
                first = nums2.get(i);
            } else {
                if (nums2.get(i)>sec) {
                    sec = nums2.get(i);
                }
            }
        }
        System.out.println(first + " : "+ sec);

        List<Integer> nums3 = List.of(1, 2, 3, 4, 5, 6);
        Map<Boolean, List<Integer>> listMap = nums3.stream()
                .collect(Collectors.partitioningBy(n -> n%2==0));
        System.out.println(listMap);

        List<String> words1 = List.of("java", "stream", "collections", "api");
        Optional<String> optionalS = words1.stream().max((a,b) -> a.length() - b.length());
        System.out.println(optionalS.get().length());
    }

    public static class LFU {
        int key;
        int val;
        int idx;

        public LFU(int key, int val, int idx) {
            this.key = key;
            this.val = val;
            this.idx = idx;
        }
    }
}
