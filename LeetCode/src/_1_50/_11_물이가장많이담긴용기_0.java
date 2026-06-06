package _1_50;

public class _11_물이가장많이담긴용기_0 {

    static class Solution {

        /*
            [전체 흐름 정리]

            배열의 각 값은 세로 막대의 높이를 의미하고,
            배열의 인덱스는 막대의 가로 위치를 의미한다.

            두 막대를 선택했을 때 담을 수 있는 물의 양은 다음 공식으로 계산한다.

            넓이 = 두 막대 사이의 거리 * 두 막대 중 더 낮은 높이

            물은 더 높은 막대 기준으로 담기는 것이 아니라,
            더 낮은 막대 높이까지만 담길 수 있다.

            처음에는 가장 먼 거리를 확보하기 위해
            left는 첫 번째 막대, right는 마지막 막대에서 시작한다.

            매 반복마다 현재 left, right가 가리키는 두 막대의 넓이를 계산하고,
            지금까지의 최대 넓이를 갱신한다.

            이후 현재 물 높이를 제한하는 더 낮은 막대 쪽 포인터를 이동한다.

            포인터를 이동하면 두 막대 사이의 거리는 무조건 줄어든다.
            따라서 더 큰 넓이를 만들려면 물 높이를 키워야 한다.

            현재 물 높이를 제한하는 낮은 막대를 버리고,
            더 높은 막대를 만날 가능성이 있는 방향으로 이동하는 것이 핵심이다.

            left와 right가 만나면 두 막대를 선택할 수 없으므로 반복을 종료한다.

            시간 복잡도: O(n)
            공간 복잡도: O(1)
        */
        public int maxArea(int[] height) {

            int left = 0;
            int right = height.length - 1;
            int maxArea = 0;

            while (left < right) {

                // 두 막대 사이의 거리
                int width = right - left;

                // 실제로 물이 찰 수 있는 높이는 두 막대 중 더 낮은 높이
                int minHeight = Math.min(height[left], height[right]);

                // 현재 두 막대로 만들 수 있는 물의 양
                int currentArea = width * minHeight;

                // 지금까지 계산한 넓이 중 최대값 갱신
                maxArea = Math.max(maxArea, currentArea);

                // 현재 물 높이를 제한하는 낮은 막대 쪽을 이동
                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }
            }

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