package _1_50;

public class _09_회문숫자_0 {

    static class Solution {

        /*
            [문제 설명]
            정수 x가 주어졌을 때,
            앞에서 읽으나 뒤에서 읽으나 같은 숫자이면 true,
            아니면 false를 반환한다.

            예)
            121  -> true
            -121 -> false
            10   -> false

            [핵심 아이디어]
            1. 음수는 무조건 false
               (예: -121 -> 뒤집으면 121- 이 되어 다름)

            2. 숫자를 뒤집어서 원래 값과 비교

            3. 문자열로 변환하지 않고 수학적으로 처리

            시간복잡도: O(log n)
            공간복잡도: O(1)
         */
        public boolean isPalindrome(int x) {

            // 1. 음수는 회문이 될 수 없음
            if (x < 0) {
                return false;
            }

            // 2. 끝자리가 0인데, 0이 아닌 숫자는 회문이 될 수 없음
            // 예: 10 -> 01 (다름)
            if (x != 0 && x % 10 == 0) {
                return false;
            }

            int reversedHalf = 0;

            /*
                숫자의 절반만 뒤집어서 비교하는 방식

                예: 12321
                - 앞: 123
                - 뒤: 123 (뒤집은 값)

                절반까지만 비교하면 충분하다.
             */
            while (x > reversedHalf) {

                // 마지막 자리 추출
                int digit = x % 10;

                // 뒤집은 숫자에 추가
                reversedHalf = reversedHalf * 10 + digit;

                // 기존 숫자는 한 자리 줄이기
                x /= 10;
            }

            /*
                짝수 자리:
                x == reversedHalf

                홀수 자리:
                가운데 숫자 제외해야 하므로
                x == reversedHalf / 10
             */
            return x == reversedHalf || x == reversedHalf / 10;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        /*
            [main 역할]
            - isPalindrome() 테스트
         */

        System.out.println(sol.isPalindrome(121));   // true
        System.out.println(sol.isPalindrome(-121));  // false
        System.out.println(sol.isPalindrome(10));    // false
        System.out.println(sol.isPalindrome(12321)); // true
        System.out.println(sol.isPalindrome(123));   // false
    }
}