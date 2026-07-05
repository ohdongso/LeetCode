package _1_50;

import java.util.Arrays;

public class _16_3Sum_Closest_0 {

    static class Solution {

        /*
            [문제 분석]

            정수 배열 nums에서 서로 다른 인덱스의 세 수를 골라
            그 합이 target에 가장 가까운 값을 찾는 문제이다.

            예)
            nums = [-1, 2, 1, -4], target = 1
            가능한 세 수의 합 중 target 1에 가장 가까운 값은 2이다.
            (-1 + 2 + 1 = 2)

            [풀이 흐름]

            1. 배열을 오름차순 정렬한다.
            2. i를 기준값으로 고정한다.
            3. 기준값 오른쪽에서 left, right 투 포인터를 사용한다.
            4. 현재 합 sum과 target의 차이를 비교한다.
            5. 더 가까운 합이면 answer를 갱신한다.
            6. sum < target 이면 left++
            7. sum > target 이면 right--
            8. sum == target 이면 가장 가까운 값이므로 바로 return 한다.

            시간 복잡도 : O(n²)
        */
        public int threeSumClosest(int[] nums, int target) {

            Arrays.sort(nums);

            int answer = nums[0] + nums[1] + nums[2];

            for (int i = 0; i < nums.length - 2; i++) {

                int left = i + 1;
                int right = nums.length - 1;

                while (left < right) {

                    int sum = nums[i] + nums[left] + nums[right];

                    if (Math.abs(target - sum) < Math.abs(target - answer)) {
                        answer = sum;
                    }

                    if (sum == target) {
                        return sum;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }

            return answer;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        System.out.println(sol.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        // 2

        System.out.println(sol.threeSumClosest(new int[]{0, 0, 0}, 1));
        // 0
    }
}