package _1_50;

import java.util.Arrays;

public class _16_3Sum_Closest_0 {

    static class Solution {

        public int threeSumClosest(int[] nums, int target) {

            /*
                [알고리즘 순서]

                ① 배열을 오름차순으로 정렬한다.
                      ↓
                ② 하나의 원소(i)를 기준값으로 고정한다.
                      ↓
                ③ 나머지 두 수는 left / right 투 포인터로 탐색한다.
                      ↓
                ④ 현재 합(sum)이 target보다 작으면
                   left를 증가시켜 더 큰 합을 만든다.
                      ↓
                ⑤ 현재 합(sum)이 target보다 크면
                   right를 감소시켜 더 작은 합을 만든다.
                      ↓
                ⑥ target과의 차이가 가장 작은 합을
                   answer에 계속 갱신한다.
                      ↓
                ⑦ sum == target 이면 가장 가까운 값이므로
                   바로 반환(return)한다.
            */

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

        System.out.println(sol.threeSumClosest(new int[]{1, 1, 1, 0}, -100));
        // 2
    }
}