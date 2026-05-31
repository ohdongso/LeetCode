package _1_50;

public class _11_물이가장많이담긴용기_0 {

    static class Solution {

        /*
            [전체 흐름 정리]

            이 문제는 여러 개의 세로선 중에서
            두 개의 선을 골라 가장 많은 물을 담을 수 있는 넓이를 구하는 문제다.

            물을 담을 수 있는 넓이는 다음 공식으로 계산한다.

            넓이 = 두 선 사이의 거리 * 두 선 중 더 낮은 높이

            여기서 중요한 점은
            물의 높이는 두 선 중 더 낮은 선을 기준으로 결정된다는 것이다.

            핵심 처리 방식은 다음과 같다.

            1. 왼쪽 포인터는 배열의 시작 위치에 둔다.
            2. 오른쪽 포인터는 배열의 마지막 위치에 둔다.
            3. 현재 두 선으로 만들 수 있는 넓이를 계산한다.
            4. 최대 넓이를 갱신한다.
            5. 더 낮은 높이를 가진 쪽의 포인터를 이동한다.

            낮은 쪽을 이동하는 이유는
            넓이를 키우려면 더 높은 선을 만날 가능성을 찾아야 하기 때문이다.

            높은 쪽을 이동하면
            거리는 줄어드는데 물 높이는 낮은 쪽 때문에 그대로 제한되므로
            더 큰 넓이를 만들 가능성이 낮다.

            마지막으로 모든 포인터 이동이 끝나면
            가장 큰 넓이를 반환한다.
        */
        public int maxArea(int[] height) {

            // 왼쪽 포인터는 배열의 시작 위치
            int left = 0;

            // 오른쪽 포인터는 배열의 마지막 위치
            int right = height.length - 1;

            // 지금까지 구한 최대 넓이
            int maxArea = 0;

            // left와 right가 만나기 전까지 반복한다.
            while (left < right) {

                // 두 선 사이의 거리
                int width = right - left;

                // 물의 높이는 두 선 중 더 낮은 높이를 기준으로 한다.
                int minHeight = Math.min(height[left], height[right]);

                // 현재 두 선으로 담을 수 있는 물의 양
                int currentArea = width * minHeight;

                // 최대 넓이 갱신
                maxArea = Math.max(maxArea, currentArea);

                // 더 낮은 높이를 가진 쪽을 이동한다.
                // 낮은 쪽이 물 높이를 제한하고 있기 때문에
                // 더 큰 높이를 찾기 위해 이동한다.
                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }
            }

            // 최종 최대 넓이 반환
            return maxArea;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        System.out.println(sol.maxArea(new int[]{1,8,6,2,5,4,8,3,7})); // 49
        System.out.println(sol.maxArea(new int[]{1,1}));               // 1
        System.out.println(sol.maxArea(new int[]{4,3,2,1,4}));         // 16
        System.out.println(sol.maxArea(new int[]{1,2,1}));             // 2
    }
}