
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

            /*
             * [학습 완료]
             * List<List<Integer>> : List 안에 List가 있는 구조
             * 각 내부 List에는 숫자 4개의 조합이 저장됨
             * 현재는 빈 리스트만 생성
             */
            List<List<Integer>> result = new ArrayList<>();

            /*
             * [학습 완료 - 방어 코드]
             * nums == null   : 배열 참조가 없는 경우
             * nums.length < 4 : 숫자가 4개 미만인 경우
             *
             * || : OR 연산자 (단락 평가)
             * 앞 조건이 true면 뒤 조건은 검사하지 않음
             * 조건에 해당하면 빈 result 반환 후 종료
             */
            if (nums == null || nums.length < 4) {
                return result;
            }

            /*
             * [학습 완료 - 오름차순 정렬]
             * 정렬을 통해 숫자의 크기 순서를 보장
             * 투 포인터 이동 방향을 판단할 수 있음
             *
             * sum < target → left++ (합 증가)
             * sum > target → right-- (합 감소)
             *
             * 불필요한 탐색을 줄이기 위한 정렬
             */
            Arrays.sort(nums);

            /*
             * [다음 학습 시작 위치]
             * 첫 번째 숫자 i 고정
             * 왜 nums.length - 3인지 분석 예정
             */
            for (int i = 0; i < nums.length - 3; i++) {

                // 첫 번째 i는 허용, 이후 같은 값이면 건너뛰기
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                // 두 번째 숫자 고정 (오른쪽에 최소 2개 필요)
                for (int j = i + 1; j < nums.length - 2; j++) {

                    // 첫 번째 j는 허용, 이후 같은 값이면 건너뛰기
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

                            // 정답 조합 저장
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

        /*
         * [학습 완료 - 객체 생성]
         * fourSum()은 static이 없는 인스턴스 메서드
         * 호출하기 위해 Solution 객체 생성
         *
         * 하나의 객체로 같은 메서드를 여러 번 호출 가능
         */
        Solution sol = new Solution();

        /*
         * [학습 완료 - 예제 1]
         * nums1   : 정수 배열 (지역 변수)
         * target1 : 목표 합계 (지역 변수)
         *
         * fourSum()에 배열과 목표값을 인자로 전달
         */
        int[] nums1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;

        System.out.println(sol.fourSum(nums1, target1));

        // 예상 결과
        // [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]

        /*
         * [학습 완료 - 예제 2]
         * 같은 객체와 메서드를 재사용
         * 서로 다른 배열과 목표값을 전달
         */
        int[] nums2 = {2, 2, 2, 2, 2};
        int target2 = 8;

        System.out.println(sol.fourSum(nums2, target2));

        // 예상 결과
        // [[2, 2, 2, 2]]
    }
}
