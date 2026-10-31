
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
         * 공간 복잡도 : O(log N) ~ O(N)
         * (정렬 구현에 따른 보조 공간, 결과 리스트 제외)
         */

        public List<List<Integer>> fourSum(int[] nums, int target) {

            /*
             * [학습 완료 - 결과 리스트]
             *
             * List<List<Integer>> : List 안에 List가 있는 구조
             *
             * 바깥쪽 List : 여러 정답 조합을 저장
             * 안쪽 List   : 숫자 4개로 구성된 하나의 조합
             *
             * 숫자 4개의 합이 target과 일치하는
             * 중복 없는 조합만 저장
             *
             * 현재는 빈 리스트만 생성
             */
            List<List<Integer>> result = new ArrayList<>();

            /*
             * [학습 완료 - 방어 코드]
             *
             * nums == null    : 배열 참조가 없는 경우
             * nums.length < 4 : 숫자가 4개 미만인 경우
             *
             * || : OR 연산자 (단락 평가)
             * 앞 조건이 true면 뒤 조건은 검사하지 않음
             *
             * 조건에 해당하면 빈 result 반환 후 종료
             */
            if (nums == null || nums.length < 4) {
                return result;
            }

            /*
             * [학습 완료 - 오름차순 정렬]
             *
             * 정렬을 통해 숫자의 크기 순서를 보장
             * 투 포인터 이동 방향을 판단할 수 있음
             *
             * sum < target → left++ (합 증가 또는 유지)
             * sum > target → right-- (합 감소 또는 유지)
             *
             * 불필요한 탐색을 줄이기 위한 정렬
             */
            Arrays.sort(nums);

            /*
             * [학습 완료 - 첫 번째 숫자 i 고정]
             *
             * i : 숫자 4개 중 첫 번째 숫자의 인덱스
             *
             * nums.length - 3 :
             * i를 선택한 뒤 오른쪽에 최소 3개의 숫자가
             * 남아 있어야 하기 때문에 반복 범위를 제한
             *
             * 예) 배열 길이 6
             * i < 6 - 3 → i < 3
             * i는 0, 1, 2까지만 선택 가능
             */
            for (int i = 0; i < nums.length - 3; i++) {

                /*
                 * [학습 완료 - 첫 번째 숫자 i 중복 제거]
                 *
                 * i > 0 : 첫 번째 반복이 아닌 경우
                 *
                 * nums[i] == nums[i - 1] :
                 * 현재 숫자와 이전 숫자가 같은지 비교
                 *
                 * 같은 값이면 continue 실행
                 * 현재 반복을 건너뛰고 다음 i++ 진행
                 *
                 * 이전 i에서 가능한 숫자 조합을 이미
                 * 탐색했으므로 중복 탐색을 방지
                 *
                 * 정렬되어 있어 같은 숫자는 연속해서 위치함
                 */
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                /*
                 * [학습 완료 - 두 번째 숫자 j 고정]
                 *
                 * j : 숫자 4개 중 두 번째 숫자의 인덱스
                 *
                 * j = i + 1 :
                 * 첫 번째 숫자 i가 이미 선택되었으므로
                 * i 바로 다음 인덱스부터 탐색 시작
                 *
                 * j < nums.length - 2 :
                 * j를 선택한 뒤 오른쪽에 최소 2개의 숫자가
                 * 남아 있어야 하기 때문에 반복 범위를 제한
                 *
                 * 예) 배열 길이 6
                 * j < 6 - 2 → j < 4
                 * j는 최대 인덱스 3까지 선택 가능
                 *
                 * i와 j는 서로 다른 인덱스를 선택해야 함
                 */
                for (int j = i + 1; j < nums.length - 2; j++) {

                    /*
                     * [학습 완료 - 두 번째 숫자 j 중복 제거]
                     *
                     * j > i + 1 :
                     * 현재 i를 기준으로 첫 번째 j는
                     * 반드시 탐색하도록 보장하는 조건
                     *
                     * nums[j] == nums[j - 1] :
                     * 현재 숫자와 이전 숫자가 같은지 비교
                     *
                     * 첫 번째 j는 같은 값이어도 탐색 허용
                     * 이후 같은 값이 반복되면 continue 실행
                     *
                     * 이전 j에서 가능한 조합을 이미
                     * 탐색했으므로 중복 탐색을 방지
                     */
                    if (j > i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }

                    /*
                     * [학습 완료 - 투 포인터 시작 위치]
                     *
                     * i : 첫 번째 고정 인덱스
                     * j : 두 번째 고정 인덱스
                     *
                     * left = j + 1 :
                     * j 바로 다음 인덱스에서 탐색 시작
                     *
                     * right = nums.length - 1 :
                     * 배열의 마지막 인덱스에서 탐색 시작
                     *
                     * left는 오른쪽으로 이동
                     * right는 왼쪽으로 이동
                     *
                     * 서로 다른 인덱스 4개를 선택하기 위한 구조
                     */
                    int left = j + 1;
                    int right = nums.length - 1;

                    /*
                     * [학습 완료 - 투 포인터 반복 조건]
                     *
                     * left < right :
                     * 두 포인터가 서로 다른 인덱스를
                     * 가리키는 동안 탐색 진행
                     *
                     * left == right :
                     * 같은 인덱스를 가리키므로 탐색 종료
                     *
                     * left > right :
                     * 포인터가 교차했으므로 탐색 종료
                     *
                     * 서로 다른 인덱스의 숫자 4개를
                     * 선택해야 하기 때문에 < 조건 사용
                     */
                    while (left < right) {

                        /*
                         * [학습 완료 - 네 숫자의 합 계산]
                         *
                         * nums[i]     : 첫 번째 숫자
                         * nums[j]     : 두 번째 숫자
                         * nums[left]  : 세 번째 숫자
                         * nums[right] : 네 번째 숫자
                         *
                         * int : 32비트 정수형
                         * long : 64비트 정수형
                         *
                         * int로 네 숫자를 더하면
                         * 오버플로가 발생할 수 있음
                         *
                         * (long) nums[i] :
                         * 첫 번째 숫자를 long으로 명시적 형변환
                         *
                         * 이후 덧셈에서도 나머지 int 값들이
                         * long으로 자동 형변환되어 계산됨
                         *
                         * long sum으로 선언하는 것만으로는
                         * int 연산 중 오버플로를 방지할 수 없음
                         */
                        long sum = (long) nums[i]
                                + nums[j]
                                + nums[left]
                                + nums[right];

                        /*
                         * [학습 완료 - sum과 target 비교]
                         *
                         * sum : long
                         * target : int
                         *
                         * == 연산자로 서로 다른 정수형 비교 가능
                         *
                         * 비교 과정에서 target이 long으로
                         * 자동 형변환되어 값 비교 진행
                         *
                         * sum == target :
                         * 네 숫자의 합이 목표값과 일치
                         */
                        if (sum == target) {

                            /*
                             * [학습 완료 - 정답 조합 저장]
                             *
                             * Arrays.asList() :
                             * 숫자 4개를 하나의 List로 생성
                             *
                             * result.add() :
                             * 생성된 List를 결과 리스트에 추가
                             *
                             * List<List<Integer>> 구조
                             *
                             * 예)
                             * [-2, -1, 1, 2]
                             * [-2,  0, 0, 2]
                             *
                             * 인덱스 자체가 아니라
                             * 해당 인덱스의 숫자 값을 저장
                             */
                            result.add(Arrays.asList(
                                    nums[i],
                                    nums[j],
                                    nums[left],
                                    nums[right]
                            ));

                            /*
                             * [학습 완료 - 정답 발견 후 포인터 이동]
                             *
                             * 현재 조합은 이미 정답으로 저장
                             *
                             * left++ :
                             * 더 큰 숫자 방향으로 이동
                             *
                             * right-- :
                             * 더 작은 숫자 방향으로 이동
                             *
                             * 양쪽 포인터를 이동하여
                             * 새로운 숫자 조합을 탐색
                             */
                            left++;
                            right--;

                            /*
                             * [학습 완료 - left 중복 제거]
                             *
                             * left < right :
                             * 두 포인터가 만나기 전인지 확인
                             *
                             * nums[left] == nums[left - 1] :
                             * 현재 값과 이전 값을 비교
                             *
                             * 같은 값이면 이미 탐색한 숫자이므로
                             * left++로 다음 인덱스 이동
                             *
                             * 중복된 정답 조합 저장 방지
                             */
                            while (left < right
                                    && nums[left] == nums[left - 1]) {
                                left++;
                            }

                            /*
                             * [학습 완료 - right 중복 제거]
                             *
                             * left < right :
                             * 두 포인터가 만나기 전인지 확인
                             *
                             * nums[right] == nums[right + 1] :
                             * 현재 값과 이전에 선택했던 값 비교
                             *
                             * 같은 값이면 이미 탐색한 숫자이므로
                             * right--로 이전 인덱스 이동
                             *
                             * left와 right는 이동 방향이 반대이므로
                             * 중복 비교 인덱스도 서로 반대
                             */
                            while (left < right
                                    && nums[right] == nums[right + 1]) {
                                right--;
                            }

                        /*
                         * [학습 완료 - 합이 목표보다 작은 경우]
                         *
                         * sum < target :
                         * 네 숫자의 합이 목표값보다 작음
                         *
                         * 배열은 이미 오름차순 정렬되어 있음
                         *
                         * left++ 실행 시 더 크거나 같은
                         * 숫자를 선택하게 됨
                         *
                         * 따라서 합을 증가시키거나 유지하면서
                         * 목표값을 다시 탐색
                         */
                        } else if (sum < target) {

                            left++;

                        /*
                         * [학습 완료 - 합이 목표보다 큰 경우]
                         *
                         * sum > target :
                         * 네 숫자의 합이 목표값보다 큼
                         *
                         * right-- 실행 시 더 작거나 같은
                         * 숫자를 선택하게 됨
                         *
                         * 따라서 합을 감소시키거나 유지하면서
                         * 목표값을 다시 탐색
                         */
                        } else {

                            right--;
                        }
                    }
                }
            }

            /*
             * [학습 완료 - 최종 결과 반환]
             *
             * 모든 i, j 및 투 포인터 탐색 완료
             *
             * 조건에 맞는 중복 없는 숫자 조합들을 반환
             *
             * 정답이 없다면 빈 리스트 반환
             */
            return result;
        }
    }

    public static void main(String[] args) {

        /*
         * [학습 완료 - 객체 생성]
         *
         * fourSum()은 static이 없는 인스턴스 메서드
         * 호출하기 위해 Solution 객체 생성
         *
         * 하나의 객체로 같은 메서드를 여러 번 호출 가능
         */
        Solution sol = new Solution();

        /*
         * [학습 완료 - 예제 1]
         *
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
         *
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
