import java.util.*;
import java.util.stream.Collectors;

class Combination2 {
    List<List<Integer>> set = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        rec(candidates, target, 0, new ArrayList<>());
        return set.stream().toList();
    }

    private void rec(int[] candidates, int target, int idx, List<Integer> ans) {
        if (set.contains(new ArrayList<>(ans))) return;
        if (target == 0) {
            set.add(new ArrayList<>(ans));
            return;
        }

        if (idx == candidates.length || target < 0) return;

        ans.add(candidates[idx]);
        rec(candidates, target - candidates[idx], idx + 1, ans);
        ans.remove(ans.size() - 1);

        rec(candidates, target, idx + 1, ans);
    }

    public static void main(String[] args) {
        Combination2 solution = new Combination2();
        List<List<Integer>> ans = solution.combinationSum(new int[]{10,1,2,7,6,1,5}, 8);
//        System.out.println(ans);
        topKElements();
    }

    public static void topKElements() {
        int[] arr = {3,2,4,5,3,7,3,7};
        int freq[] = new int[8];

        for (int val: arr) freq[val]++;
        int k=3;

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> a.freq - b.freq);
        for (int i = 0; i < 8; i++) {
            if (freq[i]!=0) {
                pq.add(new Pair(i, freq[i]));
                while (pq.size() > k) {
                    pq.poll();
                }
            }
        }

        while(!pq.isEmpty()) {
             {
            Pair p = pq.poll();
            System.out.print(p.val + " ") ;
            }
        }
    }

    public static class Pair {
        int freq;
        int val;

        public Pair(int v, int f) {
            this.val =v;
            this.freq = f;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        TreeNode current = root;
        // inorder morris traversal
        while(current!=null) {
            if (current.left==null) {
                k--;
                if (k==0) return current.val;
                current = current.right;
            } else {
                TreeNode pred = current.left;

                while (pred.right!=null && pred.right!=current) {
                    pred = pred.right;
                }

                if (pred.right == null) {
                    pred.right = current;
                    current = current.left;
                } else {
                    pred.right = null;
                    k--;
                    if (k==0) return current.val;
                    current = current.right;
                }
            }
        }
        return -1;
    }

}