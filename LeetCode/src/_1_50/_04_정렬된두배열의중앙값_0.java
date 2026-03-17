package _1_50;

public class _04_정렬된두배열의중앙값_0 {

    static class Solution {
    	
    	/*
    	 	● 내가 생각한 문제풀이 방법
    	 	2개의 배열을 받게 되는데, 2개의 배열을 함친 뒤 오름차순 정렬하고
    	 	짝수면 중앙 2개의 값을 더한 뒤 2를 나눈값을, 홀수면 중앙값을 그대로 출력
    	 	
    	 	● AI풀이 방법
    	 	두 배열은 이미 오름차순 정렬되어 있기 때문에 전체를 합쳐서 정렬할 필요 없이
	        이진탐색을 이용하여 두 배열을 적절한 위치에서 나눈다 (partition).
	
	        이때 왼쪽 부분과 오른쪽 부분으로 나누었을 때
	        왼쪽의 모든 값이 오른쪽의 값보다 작거나 같도록 만드는 것이 핵심이다.
	
	        조건:왼쪽 최대값 ≤ 오른쪽 최소값
	        위 조건을 만족하는 분할 위치를 찾으면 중앙값을 바로 구할 수 있다.
	
	        - 전체 길이가 홀수일 경우:
	          → 왼쪽 부분의 최대값이 중앙값
	
	        - 전체 길이가 짝수일 경우:
	          → (왼쪽 최대값 + 오른쪽 최소값) / 2
	
	        이 과정을 작은 배열 기준으로 이진탐색하여 O(log(m+n)) 시간복잡도로 해결한다.
    	 */
    	
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {

            // 항상 nums1이 더 작은 배열이 되도록 설정
            if (nums1.length > nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }
             
            // 각 배열의 길이 저장
            int m = nums1.length;
            int n = nums2.length;

            int left = 0;
            int right = m;

            while (left <= right) {

                int i = (left + right) / 2;
                int j = (m + n + 1) / 2 - i;

                int maxLeft1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
                int minRight1 = (i == m) ? Integer.MAX_VALUE : nums1[i];

                int maxLeft2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
                int minRight2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

                // 올바른 분할 찾은 경우
                if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {

                    // 전체 길이가 홀수
                    if ((m + n) % 2 == 1) {
                        return Math.max(maxLeft1, maxLeft2);
                    }

                    // 짝수
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                }

                // 왼쪽으로 이동
                else if (maxLeft1 > minRight2) {
                    right = i - 1;
                }

                // 오른쪽으로 이동
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