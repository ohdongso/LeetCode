package _1_50;

public class _01_두개의합계_무차별대입 {

    static class Solution {

        public int[] twoSum(int[] nums, int target) {

            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == target - nums[i]) { // 이게 좀 다르네 9-2=7
                        return new int[] { i, j };
                    }
                }
            }

            return new int[] {};
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = sol.twoSum(nums, target);

        System.out.println(result[0] + ", " + result[1]); // 출력: 0, 1
    }
}