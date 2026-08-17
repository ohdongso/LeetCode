package _1_50;

import java.util.Arrays;

public class _16_3Sum_Closest_0 {

    static class Solution {

        public int threeSumClosest(int[] nums, int target) {

            /*
             * [LeetCode 16 - 3Sum Closest]
             *
             * 여러 개의 숫자가 들어있는 배열 nums에서
             * 서로 다른 위치의 숫자 3개를 선택한다.
             *
             * 선택한 세 숫자의 합 중에서
             * target 값과 가장 가까운 합을 반환하는 문제.
             *
             *
             * [알고리즘 순서]
             *
             * ① 배열을 오름차순으로 정렬한다.
             *
             *      Arrays.sort(nums);
             *
             *    정렬을 해야 left / right 포인터를 이동했을 때
             *    합을 증가시키거나 감소시킬 수 있다.
             *
             *              ↓
             *
             * ② 하나의 숫자(nums[i])를 기준값으로 고정한다.
             *
             *              ↓
             *
             * ③ 나머지 두 숫자를 left / right 투 포인터로 찾는다.
             *
             *      left  = i + 1
             *      right = nums.length - 1
             *
             *              ↓
             *
             * ④ 세 숫자의 합을 계산한다.
             *
             *      sum = nums[i] + nums[left] + nums[right]
             *
             *              ↓
             *
             * ⑤ 현재 sum이 기존 answer보다 target에 더 가까운지 확인한다.
             *
             *      |target - sum| < |target - answer|
             *
             *    현재 sum이 더 가까우면 answer를 sum으로 갱신한다.
             *
             *              ↓
             *
             * ⑥ sum과 target을 비교하여 포인터를 이동한다.
             *
             *      sum < target
             *          → 현재 합이 작으므로 left++
             *          → 더 큰 숫자를 선택해서 합을 증가시킨다.
             *
             *      sum > target
             *          → 현재 합이 크므로 right--
             *          → 더 작은 숫자를 선택해서 합을 감소시킨다.
             *
             *              ↓
             *
             * ⑦ sum == target이면 즉시 반환한다.
             *
             *      target과의 차이가 0이므로
             *      이보다 더 가까운 값은 존재할 수 없다.
             *
             *
             * [시간복잡도]
             *
             * 정렬       : O(N log N)
             * 투 포인터  : O(N²)
             *
             * 최종 시간복잡도 : O(N²)
             */

            // 1. 투 포인터 탐색을 위해 배열을 오름차순 정렬
            Arrays.sort(nums);

            /*
             * answer 초기값
             *
             * 우선 실제로 만들 수 있는 세 숫자의 합 하나를
             * 임시 정답으로 저장한다.
             *
             * nums[0], nums[1], nums[2]가 최종 정답이라는 의미는 아니고,
             * 이후 target에 더 가까운 합을 발견할 때마다 갱신한다.
             */
            int answer = nums[0] + nums[1] + nums[2];

            /*
             * i : 세 숫자 중 첫 번째 숫자를 고정하는 인덱스
             *
             * i 뒤에 left, right 두 숫자가 더 필요하기 때문에
             * i < nums.length - 2 까지만 반복한다.
             *
             * 예)
             *
             * index : 0  1  2  3  4  5
             *                   ↑  ↑  ↑
             *                   i  L  R
             *
             * 배열 길이가 6이라면
             * i의 마지막 값은 3(nums.length - 3)이 된다.
             */
            for (int i = 0; i < nums.length - 2; i++) {

                /*
                 * i를 기준으로
                 * left는 i 바로 다음 위치,
                 * right는 배열의 마지막 위치에서 시작한다.
                 */
                int left = i + 1;
                int right = nums.length - 1;

                /*
                 * left와 right는 서로 다른 인덱스를 가리켜야 한다.
                 *
                 * left == right가 되면 같은 숫자를 두 번 선택하게 되므로
                 * left < right인 동안만 탐색한다.
                 */
                while (left < right) {

                    // 현재 선택된 세 숫자의 합
                    int sum = nums[i] + nums[left] + nums[right];

                    /*
                     * 현재 sum이 기존 answer보다
                     * target에 더 가까운지 확인한다.
                     *
                     * Math.abs()를 사용하는 이유:
                     * target보다 큰 값 / 작은 값에 관계없이
                     * target과의 "거리"만 비교하기 위해서이다.
                     *
                     * 예)
                     *
                     * target = 10
                     * answer = 3  → |10 - 3| = 7
                     * sum    = 8  → |10 - 8| = 2
                     *
                     * 2 < 7 이므로 sum이 더 가까움
                     * → answer = 8
                     */
                    if (Math.abs(target - sum) < Math.abs(target - answer)) {
                        answer = sum;
                    }

                    /*
                     * 현재 합과 target 비교
                     */
                    if (sum == target) {

                        /*
                         * target과 정확히 같은 값
                         *
                         * |target - sum| = 0
                         *
                         * 더 가까운 값은 존재할 수 없으므로 즉시 반환
                         */
                        return sum;

                    } else if (sum < target) {

                        /*
                         * 현재 합이 target보다 작음
                         *
                         * 배열이 오름차순이므로
                         * left를 오른쪽으로 이동시키면 더 큰 숫자를 선택하게 된다.
                         *
                         * → 합을 증가시키기 위해 left++
                         */
                        left++;

                    } else {

                        /*
                         * 현재 합이 target보다 큼
                         *
                         * 배열이 오름차순이므로
                         * right를 왼쪽으로 이동시키면 더 작은 숫자를 선택하게 된다.
                         *
                         * → 합을 감소시키기 위해 right--
                         */
                        right--;
                    }
                }
            }

            // 모든 탐색이 끝난 후 target과 가장 가까운 세 숫자의 합 반환
            return answer;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        /*
         * [-1, 2, 1, -4]
         *
         * 정렬
         * → [-4, -1, 1, 2]
         *
         * target = 1
         *
         * target에 가장 가까운 세 숫자의 합
         * -1 + 1 + 2 = 2
         *
         * |1 - 2| = 1
         */
        System.out.println(sol.threeSumClosest(new int[]{-1, 2, 1, -4},1));
        // 결과 : 2


        /*
         * [0, 0, 0]
         *
         * 선택 가능한 세 숫자의 합은 0
         *
         * target = 1
         *
         * |1 - 0| = 1
         */
        System.out.println(sol.threeSumClosest(new int[]{0, 0, 0},1));
        // 결과 : 0


        /*
         * [1, 1, 1, 0]
         *
         * 정렬
         * → [0, 1, 1, 1]
         *
         * target = -100
         *
         * 가능한 합 중 target과 가장 가까운 값
         * 0 + 1 + 1 = 2
         */
        System.out.println(sol.threeSumClosest(new int[]{1, 1, 1, 0},-100
            )
        );
        // 결과 : 2
    }
}