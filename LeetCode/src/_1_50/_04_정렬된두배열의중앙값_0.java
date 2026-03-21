package _1_50;

public class _04_정렬된두배열의중앙값_0 {

    static class Solution {

        /*
         ● 내가 처음 생각한 방법
         두 배열을 하나로 합친 뒤 정렬해서 중앙값을 구한다.
         - 홀수 길이: 가운데 값 반환
         - 짝수 길이: 가운데 두 값의 평균 반환
         → 시간복잡도: O((m+n) log(m+n))

         ● 최적 풀이 (이진탐색)
         두 배열을 실제로 합치지 않고, 각 배열을 왼쪽 / 오른쪽 파티션으로 나눠 중앙값을 찾는다.

         핵심 아이디어
         - 두 배열을 나눴을 때 왼쪽 그룹의 모든 값 ≤ 오른쪽 그룹의 모든 값이 되도록 분할한다.
         - 올바른 분할이 되면 중앙값은 경계값만으로 바로 구할 수 있다.

         중앙값 구하는 방법
         - 전체 길이가 홀수이면 → 왼쪽 그룹의 최대값
         - 전체 길이가 짝수이면 → (왼쪽 그룹의 최대값 + 오른쪽 그룹의 최소값) / 2

         시간복잡도
         - 작은 배열 기준으로 이진탐색하므로 O(log(min(m, n)))
        */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {

            // 이진탐색 범위를 최소화하기 위해 항상 nums1을 더 짧은 배열로 맞춘다.
            if (nums1.length > nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }

            int m = nums1.length;
            int n = nums2.length;

            // nums1(짧은 배열)에서 왼쪽 파티션 개수를 이진탐색으로 찾는다.
            int left = 0;
            int right = m;

            while (left <= right) {

                // i: nums1(짧은 배열)의 왼쪽 파티션 개수
                int i = (left + right) / 2;

                // 전체 왼쪽 파티션 개수 = (m + n + 1) / 2
                // 전체 왼쪽 개수에서 nums1의 왼쪽 개수(i)를 제외하면
                // nums2의 왼쪽 파티션 개수(j)가 된다.
                int j = (m + n + 1) / 2 - i;

                // nums1 왼쪽 파티션의 마지막 값 (= 왼쪽 파티션의 최대값, 없으면 -∞ 처리)
                int maxLeft1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];

                // nums1 오른쪽 파티션의 첫 번째 값 (= 오른쪽 파티션의 최소값, 없으면 +∞ 처리)
                int minRight1 = (i == m) ? Integer.MAX_VALUE : nums1[i];

                // nums2 왼쪽 파티션의 마지막 값 (= 왼쪽 파티션의 최대값, 없으면 -∞ 처리)
                int maxLeft2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];

                // nums2 오른쪽 파티션의 첫 번째 값 (= 오른쪽 파티션의 최소값, 없으면 +∞ 처리)
                int minRight2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

                // 올바른 분할인지 확인
                // 1) nums1 왼쪽 최대값 ≤ nums2 오른쪽 최소값
                // 2) nums2 왼쪽 최대값 ≤ nums1 오른쪽 최소값
                // 위 두 조건이 모두 만족되면 전체 왼쪽 그룹 ≤ 전체 오른쪽 그룹이 성립한다.
                if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {

                    // 전체 길이가 홀수이면 왼쪽 그룹이 1개 더 많으므로
                    // 왼쪽 그룹의 최대값이 중앙값이 된다.
                    if ((m + n) % 2 == 1) {
                        return Math.max(maxLeft1, maxLeft2);
                    }

                    // 전체 길이가 짝수이면
                    // 중앙의 두 값은 왼쪽 그룹의 최대값과 오른쪽 그룹의 최소값이다.
                    return (Math.max(maxLeft1, maxLeft2)
                            + Math.min(minRight1, minRight2)) / 2.0;
                }

                // nums1 왼쪽 최대값이 nums2 오른쪽 최소값보다 크면
                // nums1의 왼쪽 파티션에 큰 값이 너무 많이 포함된 상태이므로
                // i를 줄여 파티션을 왼쪽으로 이동한다.
                else if (maxLeft1 > minRight2) {
                    right = i - 1;
                }

                // nums2 왼쪽 최대값이 nums1 오른쪽 최소값보다 크면
                // nums1의 왼쪽 파티션에 값이 부족한 상태이므로
                // i를 늘려 파티션을 오른쪽으로 이동한다.
                else {
                    left = i + 1;
                }
            }

            return 0.0;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        int[] nums1 = {1, 3};
        int[] nums2 = {2};

        double result = sol.findMedianSortedArrays(nums1, nums2);

        System.out.println(result); // 출력: 2.0
    }
}