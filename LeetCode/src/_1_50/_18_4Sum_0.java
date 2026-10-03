package _1_50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _18_4Sum_0 {

    static class Solution {

        /*
         * [LeetCode 18 - 4Sum]
         *
         * 정수 배열 nums와 정수 target이 주어졌을 때,
         * 서로 다른 4개의 위치에 있는 숫자를 선택하여
         * 합이 target이 되는 모든 고유한 조합을 반환하는 문제이다.
         *
         *
         * 예)
         *
         * nums = [1, 0, -1, 0, -2, 2]
         * target = 0
         *
         * 결과
         *
         * [
         *   [-2, -1, 1, 2],
         *   [-2, 0, 0, 2],
         *   [-1, 0, 0, 1]
         * ]
         *
         *
         * --------------------------------------------------
         * [전체 풀이 방식]
         * --------------------------------------------------
         *
         * 1. nums 배열을 오름차순으로 정렬한다.
         *
         * 2. 첫 번째 숫자를 i로 선택한다.
         *
         * 3. 두 번째 숫자를 j로 선택한다.
         *
         * 4. 나머지 두 숫자는
         *    left, right 두 개의 포인터를 이용하여 찾는다.
         *
         * 5. 네 숫자의 합(sum)을 target과 비교한다.
         *
         *
         * sum == target
         * → 정답 조합 발견
         *
         * sum < target
         * → 합을 증가시켜야 하므로 left++
         *
         * sum > target
         * → 합을 감소시켜야 하므로 right--
         *
         *
         * --------------------------------------------------
         * [Two Pointer]
         * --------------------------------------------------
         *
         * 정렬된 배열에서 양쪽에 포인터를 두고
         * 조건에 따라 포인터를 이동시키는 방식이다.
         *
         * 예)
         *
         * [-2, -1, 0, 0, 1, 2]
         *
         * i   j   L        R
         * ↓   ↓   ↓        ↓
         * -2 -1   0   0  1  2
         *
         * i, j는 고정하고
         * left와 right를 움직이면서
         * target을 만족하는 나머지 두 숫자를 찾는다.
         *
         *
         * --------------------------------------------------
         * [중복 제거]
         * --------------------------------------------------
         *
         * 문제에서는 같은 숫자 조합을
         * 여러 번 반환하면 안 된다.
         *
         * 따라서
         *
         * i
         * j
         * left
         * right
         *
         * 각각에서 이전과 동일한 값이 반복되는 경우
         * 건너뛴다.
         *
         *
         * --------------------------------------------------
         * [long 사용 이유]
         * --------------------------------------------------
         *
         * nums[i]의 범위가 크기 때문에
         * 네 숫자를 int로 더하면
         * int 범위를 초과할 가능성이 있다.
         *
         * 따라서 합을 계산할 때 long을 사용한다.
         *
         *
         * --------------------------------------------------
         * [시간 복잡도]
         * --------------------------------------------------
         *
         * 정렬
         * → O(N log N)
         *
         * i 반복
         * → O(N)
         *
         * j 반복
         * → O(N)
         *
         * Two Pointer
         * → O(N)
         *
         * 전체 시간 복잡도
         * → O(N^3)
         */

        public List<List<Integer>> fourSum(int[] nums, int target) {

            // 최종 결과를 저장할 List
            List<List<Integer>> result = new ArrayList<>();

            // 숫자 4개가 필요하므로
            // 배열 길이가 4보다 작으면 정답을 만들 수 없다.
            if (nums == null || nums.length < 4) {
                return result;
            }

            /*
             * Two Pointer를 사용하기 위해
             * 배열을 오름차순으로 정렬한다.
             */
            Arrays.sort(nums);

            /*
             * 첫 번째 숫자 선택
             *
             * 뒤에 최소 3개의 숫자가 더 필요하므로
             * nums.length - 3까지만 반복한다.
             */
            for (int i = 0; i < nums.length - 3; i++) {

                /*
                 * 첫 번째 숫자의 중복 제거
                 *
                 * 이전에 사용한 숫자와 같다면
                 * 동일한 조합이 만들어질 수 있으므로 건너뛴다.
                 */
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                /*
                 * 두 번째 숫자 선택
                 *
                 * i 다음 위치부터 시작한다.
                 */
                for (int j = i + 1; j < nums.length - 2; j++) {

                    /*
                     * 두 번째 숫자의 중복 제거
                     *
                     * 현재 i에 대한 j의 첫 번째 위치가 아니라면
                     * 이전 j 값과 비교하여 중복을 제거한다.
                     */
                    if (j > i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }

                    /*
                     * 나머지 두 숫자를 찾기 위한
                     * Two Pointer 설정
                     */
                    int left = j + 1;
                    int right = nums.length - 1;

                    while (left < right) {

                        /*
                         * int Overflow를 방지하기 위해
                         * long으로 합을 계산한다.
                         */
                        long sum =
                                (long) nums[i]
                                + nums[j]
                                + nums[left]
                                + nums[right];

                        /*
                         * 네 숫자의 합이 target과 같은 경우
                         *
                         * 정답 조합을 result에 저장한다.
                         */
                        if (sum == target) {

                            result.add(
                                    Arrays.asList(
                                            nums[i],
                                            nums[j],
                                            nums[left],
                                            nums[right]
                                    )
                            );

                            /*
                             * 현재 조합을 사용했으므로
                             * 양쪽 포인터를 이동한다.
                             */
                            left++;
                            right--;

                            /*
                             * left 중복 제거
                             *
                             * 바로 이전에 사용했던 값과 같다면
                             * 다음 값으로 이동한다.
                             */
                            while (
                                    left < right
                                    && nums[left] == nums[left - 1]
                            ) {
                                left++;
                            }

                            /*
                             * right 중복 제거
                             *
                             * 바로 이전에 사용했던 값과 같다면
                             * 다음 값으로 이동한다.
                             */
                            while (
                                    left < right
                                    && nums[right] == nums[right + 1]
                            ) {
                                right--;
                            }

                        } else if (sum < target) {

                            /*
                             * 현재 합이 target보다 작다.
                             *
                             * 배열이 오름차순으로 정렬되어 있으므로
                             * 합을 증가시키기 위해 left를 오른쪽으로 이동한다.
                             */
                            left++;

                        } else {

                            /*
                             * 현재 합이 target보다 크다.
                             *
                             * 배열이 오름차순으로 정렬되어 있으므로
                             * 합을 감소시키기 위해 right를 왼쪽으로 이동한다.
                             */
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


        /*
         * 예제 1
         *
         * nums = [1, 0, -1, 0, -2, 2]
         * target = 0
         */
        int[] nums1 = {
                1, 0, -1, 0, -2, 2
        };

        int target1 = 0;

        System.out.println(
                sol.fourSum(nums1, target1)
        );

        // 예상 결과
        //
        // [
        //   [-2, -1, 1, 2],
        //   [-2, 0, 0, 2],
        //   [-1, 0, 0, 1]
        // ]


        /*
         * 예제 2
         *
         * nums = [2, 2, 2, 2, 2]
         * target = 8
         */
        int[] nums2 = {
                2, 2, 2, 2, 2
        };

        int target2 = 8;

        System.out.println(
                sol.fourSum(nums2, target2)
        );

        // 예상 결과
        //
        // [[2, 2, 2, 2]]
    }
}