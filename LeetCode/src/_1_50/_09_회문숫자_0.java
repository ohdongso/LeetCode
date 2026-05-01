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
               (예: -121 → 뒤집으면 121- 이 되어 다름)

            2. 끝자리가 0인데, 0이 아닌 경우는 회문 불가
               (예: 10 → 01 → 1, 앞자리 0은 사라져 값이 달라짐)

            3. 숫자의 "절반만 뒤집어서" 비교
               → 전체를 뒤집지 않아 overflow 방지
               → 절반만 비교해도 회문 판별 가능

            시간복잡도: O(log n)
            공간복잡도: O(1)
         */
        public boolean isPalindrome(int x) {

            // 1. 음수는 회문이 될 수 없음
            if (x < 0) {
                return false;
            }

            // 2. 0이 아닌데 끝자리가 0이면 회문이 될 수 없음
            //    뒤집으면 앞자리가 0이 되어 사라지므로 값이 달라짐 (예: 10 → 1)
            if (x != 0 && x % 10 == 0) {
                return false;
            }

            // 뒤쪽 절반을 뒤집어서 저장하는 변수
            int reversedHalf = 0;

            /*
                숫자의 절반만 뒤집는 과정

                예: 12321

                진행 과정:
                x = 12321, reversedHalf = 0
                → x = 1232, reversedHalf = 1
                → x = 123,  reversedHalf = 12
                → x = 12,   reversedHalf = 123 (여기서 종료)

                while 종료 시 상태:
                - x는 앞쪽 절반
                - reversedHalf는 뒤쪽 절반을 뒤집은 값

                → 홀수 자리일 경우 reversedHalf가 한 자리 더 많음
             */
            while (x > reversedHalf) {

                int digit = x % 10; // x의 마지막 자리 추출
                reversedHalf = reversedHalf * 10 + digit; // 기존 값에 자릿수 확장 후 digit을 뒤에 붙임
                x /= 10; // 마지막 자리 제거 (앞쪽 숫자 줄이기)
            }

            /*
                최종 비교

                [짝수 자리]
                예: 1221
                x = 12, reversedHalf = 12
                → 앞쪽 == 뒤쪽 → 회문

                [홀수 자리]
                예: 12321
                x = 12, reversedHalf = 123
                → 가운데 숫자(3)는 비교에서 제외해야 함
                → reversedHalf / 10 → 12
                → 앞쪽 == 뒤쪽 → 회문

                → 두 경우를 OR 조건으로 동시에 처리
             */
            return x == reversedHalf || x == reversedHalf / 10;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        /*
            [테스트 케이스]
         */
        System.out.println(sol.isPalindrome(121));   // true
        System.out.println(sol.isPalindrome(-121));  // false
        System.out.println(sol.isPalindrome(10));    // false
        System.out.println(sol.isPalindrome(12321)); // true
        System.out.println(sol.isPalindrome(123));   // false
    }
}