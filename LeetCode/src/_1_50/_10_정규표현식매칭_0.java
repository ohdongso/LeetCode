package _1_50;

public class _10_정규표현식매칭_0 {

    static class Solution {

        public boolean isMatch(String s, String p) {

            int m = s.length();
            int n = p.length();

            // dp[i][j] = s의 앞 i글자와 p의 앞 j글자가 매칭되는지 여부
            // i, j는 index가 아니라 "사용한 글자 수" 기준이다.
            boolean[][] dp = new boolean[m + 1][n + 1];

            // 빈 문자열과 빈 패턴은 매칭된다.
            dp[0][0] = true;

            // s가 빈 문자열일 때, p가 "a*", "a*b*"처럼 제거 가능한 패턴인지 처리
            for (int j = 2; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[0][j] = dp[0][j - 2];
                }
            }

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {

                    // dp의 i, j는 "사용한 글자 수" 기준
                    // 실제 문자열 index는 0부터 시작하므로 -1 처리
                    char sc = s.charAt(i - 1);
                    char pc = p.charAt(j - 1);

                    // 현재 문자가 같거나 '.'이면 현재 문자는 매칭 성공
                    // 따라서 이전 문자열까지 매칭됐는지 그대로 이어받는다.
                    if (pc == sc || pc == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }

                    // '*'는 바로 앞 문자를 0번 이상 반복할 수 있다.
                    else if (pc == '*') {

                        // 1. 앞 문자 + '*'를 제거하고 사용하지 않는 경우
                        // 예: "c*" 제거
                        dp[i][j] = dp[i][j - 2];

                        // '*' 앞 문자 확인
                        char prev = p.charAt(j - 2);

                        // 2. '*' 앞 문자가 현재 문자와 매칭되면
                        // '*'가 현재 문자를 하나 소비할 수 있다.
                        // 문자열만 한 글자 줄이고 패턴은 그대로 유지
                        if (prev == sc || prev == '.') {
                            dp[i][j] |= dp[i - 1][j];
                        }
                    }
                }
            }

            // 문자열 전체와 패턴 전체가 매칭되는지 반환
            return dp[m][n];
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        System.out.println(sol.isMatch("aa", "a"));      // false
        System.out.println(sol.isMatch("aa", "a*"));     // true
        System.out.println(sol.isMatch("ab", ".*"));     // true
        System.out.println(sol.isMatch("aab", "c*a*b")); // true
        System.out.println(sol.isMatch("mississippi", "mis*is*p*.")); // false
        System.out.println(sol.isMatch("", "a*b"));      // false
    }
}