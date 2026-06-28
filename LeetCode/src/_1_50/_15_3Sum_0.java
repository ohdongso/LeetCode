package _1_50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15_3Sum_0 {

    static class Solution {

        /*
            [전체 흐름]

            배열에서 합이 0이 되는
            서로 다른 세 수의 조합을 찾는다.

            1. 배열 정렬
            2. i를 첫 번째 숫자로 선택
            3. left, right 투 포인터로 나머지 두 수 탐색
            4. 합이 0이면 결과 저장
            5. 중복된 값은 건너뛰기

            시간 복잡도 : O(n²)
        */
        public List<List<Integer>> threeSum(int[] nums) {

            // 결과 저장
            List<List<Integer>> result = new ArrayList<>();

            // 예외 처리
            if (nums == null || nums.length < 3) {
                return result;
            }

            // 투 포인터 사용을 위해 정렬
            Arrays.sort(nums);

            // 첫 번째 숫자 선택
            for (int i = 0; i < nums.length - 2; i++) {

                // 같은 기준값은 건너뛰기
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                // 투 포인터 초기화
                int left = i + 1;
                int right = nums.length - 1;

                while (left < right) {

                    // 세 수의 합
                    int sum = nums[i] + nums[left] + nums[right];

                    // 합이 0인 경우
                    if (sum == 0) {

                        // 결과 저장
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                        // left 중복 제거
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }

                        // right 중복 제거
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        // 다음 조합 탐색
                        left++;
                        right--;

                    // 합이 작으면 left 이동
                    } else if (sum < 0) {

                        left++;

                    // 합이 크면 right 이동
                    } else {

                        right--;
                    }
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        System.out.println(sol.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        // [[-1, -1, 2], [-1, 0, 1]]

        System.out.println(sol.threeSum(new int[]{0, 1, 1}));
        // []

        System.out.println(sol.threeSum(new int[]{0, 0, 0}));
        // [[0, 0, 0]]
    }
}