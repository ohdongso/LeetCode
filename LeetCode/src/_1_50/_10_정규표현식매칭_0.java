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

                    char sc = s.charAt(i - 1);
                    char pc = p.charAt(j - 1);

                    // 현재 문자가 같거나 '.'이면 이전 상태를 그대로 따른다.
                    if (pc == sc || pc == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }

                    // '*'는 바로 앞 문자를 0번 이상 반복한다.
                    else if (pc == '*') {

                        // 1. 앞 문자 + '*'를 0번 사용해서 제거하는 경우
                        dp[i][j] = dp[i][j - 2];

                        char prev = p.charAt(j - 2);

                        // 2. '*' 앞 문자가 현재 문자와 맞으면, 현재 문자 하나를 '*'가 소비하는 경우
                        if (prev == sc || prev == '.') {
                            dp[i][j] |= dp[i - 1][j];
                        }
                    }
                }
            }

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