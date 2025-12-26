import java.util.Arrays;

class FirstPositiveInteger {
    public int firstMissingPositive(int[] nums) {

        int i = 0;
        while (i < nums.length) {
            int idx = nums[i]-1;
            if (idx>=0 && idx < nums.length && nums[i] != nums[idx]) {
                swap(i, idx, nums);
            } else {
                i++;
            }
        }
        System.out.println(Arrays.toString(nums));

        int ans = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j]-1 == j) {
                ans = nums[j];
            } else break;
        }
        return ans+1;
    }

    public void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int arr[] = new int[]{3,4,-1,1};
        FirstPositiveInteger firstPositiveInteger = new FirstPositiveInteger();
        System.out.println(firstPositiveInteger.firstMissingPositive(arr));
    }
}