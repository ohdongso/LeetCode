package _1_50;

public class _08_문자열을정수로변환_atoi_0 {

    static class Solution {

        /*
            [문제 설명]
            문자열 s를 32비트 부호 있는 정수로 변환한다.

            처리 규칙
            1. 앞쪽 공백은 무시한다.
            2. 첫 문자가 '+' 또는 '-'이면 부호를 결정한다.
            3. 그 뒤부터 숫자만 읽어서 정수로 변환한다.
            4. 숫자가 아닌 문자를 만나면 읽기를 중지한다.
            5. 결과가 int 범위를 벗어나면
               Integer.MAX_VALUE 또는 Integer.MIN_VALUE를 반환한다.

            예)
            "42"         -> 42
            "   -42"     -> -42
            "1337c0d3"   -> 1337
            "0-1"        -> 0
            "words 987"  -> 0

            시간복잡도: O(n)
            공간복잡도: O(1)
        */
        public int myAtoi(String s) {

            int index = 0;
            int n = s.length();
            int sign = 1;     // 기본은 양수
            int result = 0;

            // 1. 앞 공백 제거
            while (index < n && s.charAt(index) == ' ') {
                index++;
            }

            // 문자열이 공백뿐이면 0 반환
            if (index == n) {
                return 0;
            }

            // 2. 부호 확인
            if (s.charAt(index) == '+') {
                index++;
            } else if (s.charAt(index) == '-') {
                sign = -1;
                index++;
            }

            // 3. 숫자 읽기
            while (index < n) {
                char ch = s.charAt(index);

                // 숫자가 아니면 중지
                if (ch < '0' || ch > '9') {
                    break;
                }

                int digit = ch - '0';

                // 4. overflow 체크
                // result * 10 + digit 이 int 범위를 넘는지 사전 검사
                if (result > Integer.MAX_VALUE / 10 ||
                   (result == Integer.MAX_VALUE / 10 && digit > 7)) {

                    // 양수면 최대값, 음수면 최소값 반환
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }

                // 숫자 누적
                result = result * 10 + digit;
                index++;
            }

            // 5. 부호 적용 후 반환
            return result * sign;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        System.out.println(sol.myAtoi("42"));         // 42
        System.out.println(sol.myAtoi("   -42"));     // -42
        System.out.println(sol.myAtoi("1337c0d3"));   // 1337
        System.out.println(sol.myAtoi("0-1"));        // 0
        System.out.println(sol.myAtoi("words 987"));  // 0
        System.out.println(sol.myAtoi("-91283472332")); // -2147483648
    }
}