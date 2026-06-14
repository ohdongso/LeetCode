package _1_50;

public class _13_로마숫자에서정수로변환_0 {

    static class Solution {

        /*
            [전체 흐름 정리]

            이 문제는 로마 숫자 문자열 s를 정수로 변환하는 문제다.

            핵심은 현재 문자의 값보다
            오른쪽 문자의 값이 더 크면 빼고,
            그렇지 않으면 더하는 것이다.

            예)
            IV = 4

            I = 1
            V = 5

            I가 V보다 작으므로
            I는 더하지 않고 뺀다.

            결과:
            -1 + 5 = 4

            예)
            MCMXCIV = 1994

            M  = 1000
            C  = -100
            M  = 1000
            X  = -10
            C  = 100
            I  = -1
            V  = 5

            결과:
            1000 - 100 + 1000 - 10 + 100 - 1 + 5 = 1994

            시간 복잡도 : O(n)
            공간 복잡도 : O(1)
        */
        public int romanToInt(String s) {

            int answer = 0;

            for (int i = 0; i < s.length(); i++) {

                // 현재 로마 숫자의 값
                int current = getValue(s.charAt(i));

                // 다음 로마 숫자의 값
                int next = 0;

                // 다음 문자가 존재할 때만 값 확인
                if (i + 1 < s.length()) {
                    next = getValue(s.charAt(i + 1));
                }

                /*
                    현재 값이 다음 값보다 작으면 빼기

                    예)
                    IV에서 I는 V보다 작으므로 -1
                    IX에서 I는 X보다 작으므로 -1
                    XL에서 X는 L보다 작으므로 -10
                */
                if (current < next) {
                    answer -= current;
                } else {
                    answer += current;
                }
            }

            return answer;
        }

        // 로마 숫자 문자를 정수 값으로 변환
        private int getValue(char ch) {

            if (ch == 'I') {
                return 1;
            } else if (ch == 'V') {
                return 5;
            } else if (ch == 'X') {
                return 10;
            } else if (ch == 'L') {
                return 50;
            } else if (ch == 'C') {
                return 100;
            } else if (ch == 'D') {
                return 500;
            } else if (ch == 'M') {
                return 1000;
            }

            return 0;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        System.out.println(sol.romanToInt("III"));     // 3
        System.out.println(sol.romanToInt("LVIII"));   // 58
        System.out.println(sol.romanToInt("MCMXCIV")); // 1994
    }
}