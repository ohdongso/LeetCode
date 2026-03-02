package _1_50;

public class _01_두개의합계 {

    static class Solution {

        public int[] twoSum(int[] nums, int target) {

            int leng = nums.length;

            for (int i = 0; i < leng - 1; i++) {
                for (int j = i + 1; j < leng; j++) {
                    if (target == nums[i] + nums[j]) {
                        // 인덱스 반환
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