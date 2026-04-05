package _1_50;

public class _07_정수역순_0 {

    static class Solution {

        /*
            [문제 설명]
            32비트 정수 x가 주어지면, 숫자를 뒤집어서 반환한다.

            단,
            뒤집은 결과가 32비트 정수 범위를 벗어나면 0을 반환한다.

            예)
            123  → 321
            -123 → -321
            120  → 21

            [핵심 아이디어]
            1. x의 마지막 자리수를 하나씩 꺼낸다. (x % 10)
            2. 기존 결과값에 *10을 하고 새 자리수를 더한다.
            3. overflow 발생 여부를 미리 체크한다.

            시간복잡도: O(log n)
            공간복잡도: O(1)
        */
        public int reverse(int x) {

            int result = 0;

            while (x != 0) {

                int digit = x % 10;   // 마지막 자리 추출
                x /= 10;              // 자리수 제거

                // overflow 체크 (핵심)
                if (result > Integer.MAX_VALUE / 10 || 
                    (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                    return 0;
                }

                if (result < Integer.MIN_VALUE / 10 || 
                    (result == Integer.MIN_VALUE / 10 && digit < -8)) {
                    return 0;
                }

                result = result * 10 + digit;
            }

            return result;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        System.out.println(sol.reverse(123));    // 321
        System.out.println(sol.reverse(-123));   // -321
        System.out.println(sol.reverse(120));    // 21
        System.out.println(sol.reverse(1534236469)); // 0 (overflow)
    }
}