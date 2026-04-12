package _1_50;

public class _07_정수역순_0 {

    static class Solution {

        /*
            [문제 설명]
            32비트 정수 x를 뒤집어서 반환한다.

            단, 뒤집은 결과가 int 범위(-2147483648 ~ 2147483647)를
            벗어나면 0을 반환한다.

            예)
            123  → 321
            -123 → -321
            120  → 21

            [핵심 아이디어]
            1. x의 마지막 자리수를 하나씩 꺼낸다. (x % 10)
            2. result에 10을 곱해 기존 자리를 왼쪽으로 이동시킨다.
            3. 새로 꺼낸 digit을 뒤에 붙인다.
            4. 위 과정을 반복하여 숫자를 뒤집는다.
            5. 단, 연산 전 overflow 여부를 반드시 사전 체크한다.

            시간복잡도: O(log n)
            공간복잡도: O(1)
        */
        public int reverse(int x) {

            int result = 0;

            // x의 모든 자릿수를 처리할 때까지 반복
            while (x != 0) {

                // 현재 숫자의 마지막 자리 추출
                int digit = x % 10;

                // 마지막 자리 제거
                x /= 10;

                // ========================================
                // 🔹 overflow 사전 체크 (핵심)
                // ========================================

                /*
                    다음 연산:
                    result = result * 10 + digit

                    → 이 연산을 수행하기 전에
                      결과가 int 범위를 벗어나는지 미리 검사한다.
                */

                // result * 10 + digit 연산 시 overflow 발생 여부 사전 체크 (양수)
                if (result > Integer.MAX_VALUE / 10 ||
                   (result == Integer.MAX_VALUE / 10 && digit > 7)) {

                    /*
                        MAX_VALUE = 2147483647

                        1. result > 214748364
                           → 10을 곱하는 순간 overflow 발생

                        2. result == 214748364
                           → 마지막 digit에 따라 결정

                           digit ≤ 7  → 정상
                           digit > 7  → overflow
                    */
                    return 0;
                }

                // result * 10 + digit 연산 시 overflow 발생 여부 사전 체크 (음수)
                if (result < Integer.MIN_VALUE / 10 ||
                   (result == Integer.MIN_VALUE / 10 && digit < -8)) {

                    /*
                        MIN_VALUE = -2147483648

                        1. result < -214748364
                           → 10을 곱하는 순간 overflow 발생

                        2. result == -214748364
                           → 마지막 digit에 따라 결정

                           digit ≥ -8 → 정상
                           digit < -8 → overflow
                    */
                    return 0;
                }

                // ========================================
                // 🔹 자리수 뒤집기 연산
                // ========================================

                /*
                    result * 10 → 기존 숫자를 한 자리 왼쪽으로 이동
                    + digit     → 새 자릿수를 뒤에 추가

                    예)
                    result = 32, digit = 1
                    → 32 * 10 + 1 = 321
                */
                result = result * 10 + digit;
            }

            // 뒤집힌 결과 반환
            return result;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        /*
            [main 역할]
            - reverse() 메서드 테스트
            - 다양한 입력값으로 정상 동작 및 overflow 확인
        */

        System.out.println(sol.reverse(123));         // 321
        System.out.println(sol.reverse(-123));        // -321
        System.out.println(sol.reverse(120));         // 21
        System.out.println(sol.reverse(1534236469));  // 0 (overflow)
    }
}