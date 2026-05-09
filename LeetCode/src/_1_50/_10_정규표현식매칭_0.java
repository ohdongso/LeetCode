package _1_50;

public class _10_정규표현식매칭_0 {

    static class Solution {
    	
        /*
            [문제 설명]
            문자열 s와 패턴 p가 주어졌을 때,
            p가 s 전체를 매칭하는지 여부를 반환한다.

            지원 패턴:
            '.' → 아무 문자 1개
            '*' → 앞 문자 0개 이상 반복

            예:
            "a*" → "", "a", "aa", "aaa" 모두 가능

            [핵심 아이디어 - DP]

            dp[i][j] = s의 i번째까지와 p의 j번째까지가 매칭되는지 여부

            점화식:

            1. 일반 문자 or '.'
               → 현재 문자끼리 같으면 이전 상태 따라감
               dp[i][j] = dp[i-1][j-1]

            2. '*' 처리
               → 두 가지 경우 존재

               (1) 0번 사용
                   dp[i][j] = dp[i][j-2]

               (2) 1번 이상 사용
                   (앞 문자가 s와 같거나 '.'일 때)
                   dp[i][j] = dp[i-1][j]

            시간복잡도: O(m * n)
            공간복잡도: O(m * n)
         */
        public boolean isMatch(String s, String p) {

            int m = s.length(); // 입력 받은 "문자열" 길이
            int n = p.length(); // 입력 받은 "정규식" 길이
            
            // 문자열 s와 패턴 p의 매칭 상태를 저장하는 표
            boolean[][] dp = new boolean[m + 1][n + 1];

            // 초기값
            dp[0][0] = true;

            // s는 비어있고, p만 있는 경우 (a*, a*b*, ...)
            // 2026-05-09 >>> 여기서부터 내일 시작
            for (int j = 2; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[0][j] = dp[0][j - 2];
                }
            }

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {

                    char sc = s.charAt(i - 1);
                    char pc = p.charAt(j - 1);

                    // 1. 현재 문자가 같거나 '.'
                    if (pc == sc || pc == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }

                    // 2. '*'
                    else if (pc == '*') {

                        // (1) 앞 문자를 0번 사용
                        dp[i][j] = dp[i][j - 2];

                        // (2) 앞 문자를 1번 이상 사용
                        char prev = p.charAt(j - 2);

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

        /*
            [테스트 케이스]
         */
        System.out.println(sol.isMatch("aa", "a"));     // false
        System.out.println(sol.isMatch("aa", "a*"));    // true
        System.out.println(sol.isMatch("ab", ".*"));    // true
        System.out.println(sol.isMatch("aab", "c*a*b"));// true
        System.out.println(sol.isMatch("mississippi", "mis*is*p*.")); // false
    }
}