
package _1_50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _18_4Sum_0 {

    static class Solution {

        /*
         * [LeetCode 18 - 4Sum]
         *
         * 서로 다른 인덱스의 숫자 4개를 선택하여
         * 합이 target인 중복 없는 조합을 찾는다.
         *
         * [전체 흐름]
         * 1. 배열을 오름차순 정렬
         * 2. 이중 for문으로 i, j 고정
         * 3. left, right 투 포인터로 나머지 2개 탐색
         * 4. sum과 target을 비교하여 포인터 이동
         * 5. 정답 저장 및 중복 제거
         *
         * sum == target → 정답 저장 후 양쪽 이동
         * sum < target  → left++
         * sum > target  → right--
         *
         * 시간 복잡도 : O(N^3)
         */

        public List<List<Integer>> fourSum(int[] nums, int target) {

            List<List<Integer>> result = new ArrayList<>();

            // 숫자가 4개 미만이면 종료
            if (nums == null || nums.length < 4) {
                return result;
            }

            // 투 포인터 탐색을 위해 오름차순 정렬
            Arrays.sort(nums);

            // 첫 번째 숫자 고정
            for (int i = 0; i < nums.length - 3; i++) {

                // i 중복 제거
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                // 두 번째 숫자 고정
                for (int j = i + 1; j < nums.length - 2; j++) {

                    // j 중복 제거
                    if (j > i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }

                    // j 오른쪽 구간에서 투 포인터 탐색
                    int left = j + 1;
                    int right = nums.length - 1;

                    while (left < right) {

                        // 네 숫자의 합 (오버플로 방지)
                        long sum = (long) nums[i]
                                + nums[j]
                                + nums[left]
                                + nums[right];

                        if (sum == target) {

                            // 정답 저장
                            result.add(Arrays.asList(
                                    nums[i],
                                    nums[j],
                                    nums[left],
                                    nums[right]
                            ));

                            // 다른 조합 탐색
                            left++;
                            right--;

                            // left 중복값 건너뛰기
                            while (left < right
                                    && nums[left] == nums[left - 1]) {
                                left++;
                            }

                            // right 중복값 건너뛰기
                            while (left < right
                                    && nums[right] == nums[right + 1]) {
                                right--;
                            }

                        } else if (sum < target) {

                            // 합이 작으면 큰 숫자 방향으로 이동
                            left++;

                        } else {

                            // 합이 크면 작은 숫자 방향으로 이동
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
