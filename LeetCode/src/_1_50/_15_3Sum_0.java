package _1_50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15_3Sum_0 {

    static class Solution {

        /*
            [문제 분석]

            정수 배열 nums에서 서로 다른 인덱스의 세 수를 골라
            합이 0이 되는 모든 조합을 찾는 문제이다.

            단, 같은 값 조합은 한 번만 결과에 담아야 한다.

            예)
            [-1, 0, 1, 2, -1, -4]
            → [[-1, -1, 2], [-1, 0, 1]]

            [풀이 흐름]

            1. 배열을 오름차순 정렬한다.
               - 투 포인터를 사용하기 위해 필요하다.
               - 같은 값들이 연속으로 모이기 때문에 중복 제거도 쉬워진다.

            2. i를 기준값으로 고정한다.

            3. 기준값 오른쪽 구간에서 left, right 투 포인터를 사용한다.
               - left  : 기준값 바로 다음 인덱스
               - right : 배열의 마지막 인덱스

            4. nums[i] + nums[left] + nums[right] 값을 비교한다.
               - sum == 0 : 정답 저장
               - sum < 0  : 합이 작으므로 left를 오른쪽으로 이동
               - sum > 0  : 합이 크므로 right를 왼쪽으로 이동

            5. 중복 제거
               - 기준값 i 중복 제거
               - left 중복 제거
               - right 중복 제거

            시간 복잡도 : O(n²)
        */
        public List<List<Integer>> threeSum(int[] nums) {

            // 최종 결과를 저장할 리스트
            List<List<Integer>> result = new ArrayList<>();

            // 숫자 3개를 뽑아야 하므로 null이거나 길이가 3보다 작으면 바로 빈 결과 반환
            if (nums == null || nums.length < 3) {
                return result;
            }

            /*
                배열 정렬

                정렬을 하면 두 가지 장점이 있다.

                1. 투 포인터 이동 기준이 생긴다.
                   - left++  하면 값이 같거나 커진다.
                   - right-- 하면 값이 같거나 작아진다.

                2. 같은 값들이 연속으로 붙기 때문에 중복 제거가 가능하다.
            */
            Arrays.sort(nums);

            /*
                i는 기준값의 인덱스이다.

                nums.length - 2 까지만 반복하는 이유:
                기준값 i 뒤에 left, right로 사용할 숫자 2개가 최소한 남아 있어야 하기 때문이다.

                예)
                길이 6인 배열의 인덱스가 0 1 2 3 4 5 라면
                i는 3까지만 가능하다.
                i = 3일 때 left = 4, right = 5 가능
                i = 4일 때는 뒤에 숫자가 1개뿐이라 불가능
            */
            for (int i = 0; i < nums.length - 2; i++) {

                /*
                    기준값 중복 제거

                    i > 0 조건이 필요한 이유:
                    i가 0일 때 nums[i - 1]은 nums[-1]이 되어 존재하지 않는 인덱스를 참조하게 된다.

                    nums[i] == nums[i - 1] 인 경우:
                    현재 기준값이 이전 기준값과 같다는 의미이다.
                    이미 같은 기준값으로 탐색했기 때문에 다시 탐색할 필요가 없다.

                    정렬되어 있기 때문에 같은 값은 연속으로 붙어 있다.
                    그래서 바로 이전 값 하나만 비교해도 중복 기준값을 제거할 수 있다.
                */
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                /*
                    투 포인터 초기화

                    left는 기준값 바로 다음 인덱스부터 시작한다.
                    right는 배열의 마지막 인덱스부터 시작한다.

                    left = i가 아닌 i + 1인 이유:
                    기준값 nums[i]를 다시 사용하면 안 되기 때문이다.
                    문제는 서로 다른 세 개의 인덱스를 사용해야 한다.
                */
                int left = i + 1;
                int right = nums.length - 1;

                /*
                    left와 right가 만나기 전까지 반복한다.

                    left < right 인 이유:
                    left와 right가 같아지면 같은 인덱스를 두 번 사용하는 것이 된다.
                    서로 다른 세 수를 선택해야 하므로 left와 right는 겹치면 안 된다.
                */
                while (left < right) {

                    // 현재 기준값, left 값, right 값의 합
                    int sum = nums[i] + nums[left] + nums[right];

                    /*
                        세 수의 합이 0이면 정답 조합이다.
                    */
                    if (sum == 0) {

                        // 현재 세 수를 하나의 List로 만들어 결과 리스트에 추가
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                        /*
                            left 중복 제거

                            현재 left 값과 다음 left 값이 같으면
                            같은 조합이 다시 나올 수 있으므로 건너뛴다.
                        */
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }

                        /*
                            right 중복 제거

                            현재 right 값과 이전 right 값이 같으면
                            같은 조합이 다시 나올 수 있으므로 건너뛴다.
                        */
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        /*
                            중복값을 모두 건너뛴 후,
                            현재 저장한 조합은 이미 사용했으므로 다음 새로운 조합을 찾기 위해 이동한다.
                        */
                        left++;
                        right--;

                    /*
                        합이 0보다 작으면 더 큰 값이 필요하다.

                        배열이 정렬되어 있으므로
                        left를 오른쪽으로 이동하면 값이 같거나 커진다.
                    */
                    } else if (sum < 0) {

                        left++;

                    /*
                        합이 0보다 크면 더 작은 값이 필요하다.

                        배열이 정렬되어 있으므로
                        right를 왼쪽으로 이동하면 값이 같거나 작아진다.
                    */
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

        System.out.println(sol.threeSum(new int[]{-1, -1, -1, 2, 2, 2}));
        // [[-1, -1, 2]]
    }
}