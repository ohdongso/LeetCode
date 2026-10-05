package _1_50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _18_4Sum_0 {

    static class Solution {

        /*
         * [LeetCode 18 - 4Sum]
         *
         * 숫자 4개의 합이 target이 되는 모든 조합을 찾는다.
         *
         * [풀이 흐름]
         *
         * 1. 배열을 오름차순으로 정렬
         * 2. i, j로 숫자 2개를 고정
         * 3. 나머지 2개는 left, right Two Pointer로 탐색
         *
         * sum == target → 정답 저장
         * sum < target  → left++
         * sum > target  → right--
         *
         * 같은 조합이 여러 번 나오지 않도록 중복값은 건너뛴다.
         *
         * 시간 복잡도 : O(N^3)
         */

        public List<List<Integer>> fourSum(int[] nums, int target) {

            List<List<Integer>> result = new ArrayList<>();

            // 숫자 4개를 만들 수 없는 경우
            if (nums == null || nums.length < 4) {
                return result;
            }

            // Two Pointer 사용을 위해 오름차순 정렬
            Arrays.sort(nums);

            // 첫 번째 숫자 선택
            for (int i = 0; i < nums.length - 3; i++) {

                // i 중복 제거
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                // 두 번째 숫자 선택
                for (int j = i + 1; j < nums.length - 2; j++) {

                    // j 중복 제거
                    if (j > i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }

                    // 나머지 두 숫자는 Two Pointer로 탐색
                    int left = j + 1;
                    int right = nums.length - 1;

                    while (left < right) {

                        // 숫자 4개의 합
                        long sum = (long) nums[i]
                                + nums[j]
                                + nums[left]
                                + nums[right];

                        if (sum == target) {

                            // target과 같으면 정답 저장
                            result.add(Arrays.asList(
                                    nums[i],
                                    nums[j],
                                    nums[left],
                                    nums[right]
                            ));

                            left++;
                            right--;

                            // left 중복 제거
                            while (left < right
                                    && nums[left] == nums[left - 1]) {
                                left++;
                            }

                            // right 중복 제거
                            while (left < right
                                    && nums[right] == nums[right + 1]) {
                                right--;
                            }

                        } else if (sum < target) {

                            // 합이 작으면 더 큰 값이 필요
                            left++;

                        } else {

                            // 합이 크면 더 작은 값이 필요
                            right--;
                        }
                    }
                }
            }

            return result;
        }
    }


    public static void main(String[] args) {

        Solution sol = new Solution();

        // 예제 1
        int[] nums1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;

        System.out.println(sol.fourSum(nums1, target1));

        // 예상 결과
        // [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]


        // 예제 2
        int[] nums2 = {2, 2, 2, 2, 2};
        int target2 = 8;

        System.out.println(sol.fourSum(nums2, target2));

        // 예상 결과
        // [[2, 2, 2, 2]]
    }
}