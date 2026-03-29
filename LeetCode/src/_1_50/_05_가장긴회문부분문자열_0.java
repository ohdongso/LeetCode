package _1_50;

public class _05_가장긴회문부분문자열_0 {

    static class Solution {

        /*
         * [문제 해결 전략]
         * 회문(Palindrome)은 중심을 기준으로 좌우가 대칭이다.
         * → 각 인덱스를 중심으로 좌우로 확장하며 회문을 찾는다.
         *
         * 고려해야 할 경우:
         * 1) 홀수 길이 회문 (중심 1개) → (i, i)
         * 2) 짝수 길이 회문 (중심 2개) → (i, i+1)
         *
         * 시간복잡도: O(n^2)
         * 공간복잡도: O(1)
         */
        public String longestPalindrome(String s) {

            // 예외 처리: 길이 0 또는 1이면 그대로 반환 (자체가 회문)
            if (s == null || s.length() < 2) {
                return s;
            }

            // 현재까지 찾은 최장 회문의 시작/끝 인덱스
            int start = 0;
            int end = 0;

            // 모든 인덱스를 중심으로 회문 탐색
            for (int i = 0; i < s.length(); i++) {

                // 1) 홀수 회문 길이
                int len1 = expandFromCenter(s, i, i);

                // 2) 짝수 회문 길이
                int len2 = expandFromCenter(s, i, i + 1);

                // 더 긴 회문 선택
                int len = Math.max(len1, len2);

                // 기존 최장 길이보다 길 경우 갱신
                if (len > end - start + 1) {

                    /*
                     * 중심 i 기준으로 start, end 계산
                     * (홀수/짝수 모두 커버하는 공식)
                     */
                    start = i - (len - 1) / 2; // i를 기준으로 회문의 시작위치를 계산하는 공식
                    end = i + len / 2; // 회문의 끝 위치를 구하는 공식
                }
            }

            // substring은 end 미포함 → end + 1
            // substring(a, b)는 a 이상, b 미만
            return s.substring(start, end + 1);
        }

        /*
         * [중심 확장 함수]
         * 주어진 left, right를 중심으로 좌우 확장하면서
         * 회문의 길이를 반환한다.
         */
        private int expandFromCenter(String s, int left, int right) {

            // 범위 내 + 좌우 문자 동일 → 계속 확장
            while (left >= 0 && right < s.length()
                    && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }

            /*
             * 반복 종료 시 left, right는 한 칸 더 나간 상태
             * → 실제 길이 = right - left - 1
             */
            return right - left - 1;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        // 테스트 케이스
        System.out.println(sol.longestPalindrome("a"));       // a
        System.out.println(sol.longestPalindrome("ac"));      // a 또는 c
        System.out.println(sol.longestPalindrome("aa"));      // aa
        System.out.println(sol.longestPalindrome("aba"));     // aba
        System.out.println(sol.longestPalindrome("abba"));    // abba
        System.out.println(sol.longestPalindrome("babad"));   // bab 또는 aba
        System.out.println(sol.longestPalindrome("cbbd"));    // bb
        System.out.println(sol.longestPalindrome("racecar")); // racecar
        System.out.println(sol.longestPalindrome("aaaa"));    // aaaa
        System.out.println(sol.longestPalindrome("abcda"));   // a 또는 b 또는 c 또는 d
    }
}