package _1_50;

public class _10_정규표현식매칭_0 {

    static class Solution {

        /*
            [전체 흐름 정리]

            이 문제는 문자열 s 전체가 패턴 p 전체와 매칭되는지 확인하는 문제다.

            dp[i][j]는
            문자열 s의 앞 i글자와
            패턴 p의 앞 j글자가
            서로 매칭되는지 여부를 의미한다.

            핵심 처리 방식은 다음과 같다.

            1. 일반 문자 또는 '.'
               - 현재 문자끼리 매칭되면
               - 이전 상태 dp[i - 1][j - 1] 값을 이어받는다.

            2. '*'
               - '*'는 바로 앞 문자를 0번 이상 반복한다.

               2-1. 0번 사용하는 경우
                    - 앞 문자 + '*'를 제거한다.
                    - dp[i][j] = dp[i][j - 2]

               2-2. 1번 이상 사용하는 경우
                    - '*' 앞 문자가 현재 문자열 문자와 매칭되면
                    - 문자열만 한 글자 줄이고 패턴은 그대로 유지한다.
                    - dp[i][j] |= dp[i - 1][j]

            마지막으로 dp[m][n]을 반환한다.
            즉, 문자열 전체와 패턴 전체가 매칭되는지 확인한다.
        */
        public boolean isMatch(String s, String p) {

            int m = s.length();
            int n = p.length();

            // dp[i][j] = s의 앞 i글자와 p의 앞 j글자가 매칭되는지 여부
            // i, j는 index가 아니라 "사용한 글자 수" 기준이다.
            boolean[][] dp = new boolean[m + 1][n + 1];

            // 빈 문자열과 빈 패턴은 매칭된다.
            dp[0][0] = true;

            // s가 빈 문자열일 때,
            // p가 "a*", "a*b*"처럼 제거 가능한 패턴인지 미리 처리한다.
            for (int j = 2; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[0][j] = dp[0][j - 2];
                }
            }

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {

                    // dp의 i, j는 "사용한 글자 수" 기준이고,
                    // 실제 문자열 index는 0부터 시작하므로 -1을 해준다.
                    char sc = s.charAt(i - 1);
                    char pc = p.charAt(j - 1);

                    // 현재 패턴 문자가 일반 문자와 같거나 '.'이면 현재 문자 1개는 매칭된다.
                    // 따라서 이전 문자열과 이전 패턴까지 매칭됐는지 결과를 이어받는다.
                    if (pc == sc || pc == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }

                    // 현재 패턴 문자가 '*'인 경우
                    // '*'는 바로 앞 문자를 0번 이상 반복할 수 있다.
                    else if (pc == '*') {

                        // 1. '*'를 0번 사용하는 경우
                        // 앞 문자 + '*'를 통째로 제거한 상태를 확인한다.
                        dp[i][j] = dp[i][j - 2];

                        // '*' 앞 문자 확인
                        // 예: "a*"라면 prev는 'a'
                        // 예: ".*"라면 prev는 '.'
                        char prev = p.charAt(j - 2);

                        // 2. '*'를 1번 이상 사용하는 경우
                        // '*' 앞 문자가 현재 문자열 문자와 같거나 '.'이면
                        // 현재 문자열 문자 하나를 '*'가 처리할 수 있다.
                        if (prev == sc || prev == '.') {

                            // 문자열은 한 글자 줄이고,
                            // 패턴은 '*'를 계속 사용할 수 있으므로 그대로 유지한다.
                            dp[i][j] |= dp[i - 1][j];
                        }
                    }
                }
            }

            // 문자열 전체와 패턴 전체가 매칭되는지 최종 결과 반환
            return dp[m][n];
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        System.out.println(sol.isMatch("aa", "a"));                  // false
        System.out.println(sol.isMatch("aa", "a*"));                 // true
        System.out.println(sol.isMatch("ab", ".*"));                 // true
        System.out.println(sol.isMatch("aab", "c*a*b"));             // true
        System.out.println(sol.isMatch("mississippi", "mis*is*p*.")); // false
        System.out.println(sol.isMatch("", "a*b"));                  // false
    }
}