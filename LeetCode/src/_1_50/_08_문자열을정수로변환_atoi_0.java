package _1_50;

public class _08_문자열을정수로변환_atoi_0 {

    static class Solution {

        /*
            문자열을 왼쪽부터 읽으면서,
            정수로 해석 가능한 부분만 안전하게 추출한다.

            [문제 설명]
            문자열 s를 32비트 부호 있는 정수(int)로 변환한다.

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

            int index = 0;      // 현재 확인 중인 문자 위치(포인터)
            int n = s.length(); // 문자열 전체 길이

            // sign:
            // 최종 결과의 부호를 저장하는 변수
            // 기본값은 양수(1), '-'를 만나면 음수(-1)로 바뀐다.
            int sign = 1;

            // result:
            // 현재까지 읽은 숫자를 누적해서 저장하는 변수
            // 예: '1' -> 1, 다음 '2' -> 12, 다음 '3' -> 123
            int result = 0;

            // 1. 앞쪽 공백 제거
            // 숫자 해석에 의미 없는 선행 공백은 모두 건너뛴다.
            // 공백이 아닐 때까지 index를 오른쪽으로 이동시킨다.
            while (index < n && s.charAt(index) == ' ') {
                index++;
            }

            // 공백만 있는 문자열이거나 빈 문자열이면
            // 숫자로 해석할 수 있는 시작 지점이 없으므로 0 반환
            if (index == n) {
                return 0;
            }

            // 2. 부호 확인
            // '+'면 기본 양수이므로 index만 이동
            // '-'면 음수로 처리하기 위해 sign을 -1로 바꾸고 index 이동
            if (s.charAt(index) == '+') {
                index++;
            } else if (s.charAt(index) == '-') {
                sign = -1;
                index++;
            }

            // 3. 숫자만 읽어서 정수로 변환
            // 현재 위치부터 숫자인 동안만 계속 읽는다.
            while (index < n) {
                char ch = s.charAt(index);

                // 현재 문자가 숫자('0' ~ '9')가 아니면 변환 종료
                // 예: "1337c0d3" -> 'c'에서 멈추고 1337 반환
                if (ch < '0' || ch > '9') {
                    break;
                }

                // 문자 숫자를 실제 숫자값으로 변환
                // 예: '0' -> 0, '3' -> 3, '9' -> 9
                int digit = ch - '0';

                /*
                    4. overflow 사전 체크

                    다음 줄에서 수행할 연산:
                    result = result * 10 + digit;

                    그런데 연산을 먼저 해버리면 int 범위를 초과하는 순간
                    값이 깨질 수 있다.
                    그래서 "연산 전에" 미리 안전한지 검사해야 한다.

                    int 최대값은 2147483647 이다.

                    1) result가 214748364보다 크면
                       여기에 10을 곱하는 순간 이미 overflow 위험이 있다.

                    2) result가 정확히 214748364와 같다면
                       마지막 한 자리 숫자(digit)는 최대 7까지만 허용된다.
                       왜냐하면 최대값이 2147483647이기 때문이다.

                    즉,
                    - result > 214748364 이면 무조건 overflow
                    - result == 214748364 이고 digit > 7 이면 overflow
                 */
                if (result > Integer.MAX_VALUE / 10 ||
                   (result == Integer.MAX_VALUE / 10 && digit > 7)) {

                    // 문제 조건:
                    // int 범위를 벗어나면
                    // 양수는 최대값, 음수는 최소값으로 고정해서 반환
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }

                // 숫자 누적
                // 기존 result의 자릿수를 한 칸 왼쪽으로 밀고(*10)
                // 새 숫자 digit을 맨 뒤에 붙인다.
                // 예: result=12, digit=3 -> 12*10+3 = 123
                result = result * 10 + digit;

                // 다음 문자로 이동
                index++;
            }

            // 5. 최종적으로 부호 적용 후 반환
            // result는 양수 형태로 누적했기 때문에
            // 마지막에 sign을 곱해서 최종 부호를 결정한다.
            // 예: result=42, sign=-1 -> -42
            return result * sign;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        /*
            [main 역할]
            - myAtoi() 메서드 테스트
            - 다양한 입력값으로 정상 동작 및 overflow 확인
         */

        System.out.println(sol.myAtoi("42"));           // 42
        System.out.println(sol.myAtoi("   -42"));       // -42
        System.out.println(sol.myAtoi("1337c0d3"));     // 1337
        System.out.println(sol.myAtoi("0-1"));          // 0
        System.out.println(sol.myAtoi("words 987"));    // 0
        System.out.println(sol.myAtoi("-91283472332")); // -2147483648
    }
}