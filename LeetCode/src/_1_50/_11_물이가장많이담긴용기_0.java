package _1_50;

public class _11_물이가장많이담긴용기_0 {

    static class Solution {

        /*
            [전체 흐름 정리]

            배열의 각 값은 세로 막대의 높이를 의미하고,
            배열의 인덱스는 막대의 가로 위치를 의미한다.

            두 막대를 선택했을 때 담을 수 있는 물의 양은

            넓이 = 두 막대 사이의 거리 * 두 막대 중 더 낮은 높이

            로 계산한다.

            처음에는 가장 넓은 거리를 만들기 위해
            left는 첫 번째 막대, right는 마지막 막대에서 시작한다.

            매 반복마다 현재 left, right가 가리키는 두 막대의 넓이를 계산하고,
            지금까지의 최대 넓이를 갱신한다.

            이후 물 높이를 제한하는 더 낮은 막대 쪽 포인터를 이동한다.
            낮은 쪽을 이동해야 더 높은 막대를 만나서
            더 큰 넓이를 만들 가능성이 생기기 때문이다.

            left와 right가 만나면 더 이상 두 막대를 선택할 수 없으므로 반복을 종료한다.
        */
        public int maxArea(int[] height) {

            int left = 0;
            int right = height.length - 1;
            int maxArea = 0;

            while (left < right) {

                int width = right - left;
                int minHeight = Math.min(height[left], height[right]);
                int currentArea = width * minHeight;

                maxArea = Math.max(maxArea, currentArea);

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