package _1_50;

public class _05_가장긴회문부분문자열_0 {

    static class Solution {

        /*
            이 문제는 회문이 중심을 기준으로 대칭이라는 점을 이용한다.
            각 문자를 중심으로 하는 홀수 회문과,
            두 문자 사이를 중심으로 하는 짝수 회문을 모두 검사한다.
            중심에서 좌우로 확장하면서 가장 긴 회문을 찾는다.

            시간복잡도: O(n^2)
            공간복잡도: O(1)

            앞에서 읽든 뒤에서 읽든 같은 문자열 == 회문
        */
        public String longestPalindrome(String s) {

            // 길이가 1 이하이면 그대로 반환
            // 길이가 0 또는 1인 문자열은 그 자체로 회문이다.
            // 또한 null인 경우 length()를 호출하면 에러가 발생하므로 예외 처리한다.
            if (s == null || s.length() < 2) {
                return s;
            }

            // 현재까지 찾은 가장 긴 회문의 시작 위치와 끝 위치
            int start = 0;
            int end = 0;

            // 문자열의 각 인덱스 i를 중심으로 회문을 찾는다.
            for (int i = 0; i < s.length(); i++) {

                // 홀수 길이 회문 검사
                // 홀수 회문은 중심이 1개이므로 left와 right를 같은 위치(i)로 시작한다.
                int len1 = expandFromCenter(s, i, i);

                // 짝수 길이 회문 검사
                // 짝수 회문은 중심이 두 문자 사이에 있으므로 left=i, right=i+1로 시작한다.
                int len2 = expandFromCenter(s, i, i + 1);

                // 두 경우 중 더 긴 회문 길이 선택
                int len = Math.max(len1, len2);

                // len : 현재 중심에서 새로 찾은 회문 길이
                // end - start + 1 : 지금까지 찾은 최장 회문 길이
                // 새로 찾은 회문이 더 길면 start, end를 갱신한다.
                if (len > end - start + 1) {

                    // 중심 i를 기준으로 회문의 시작 위치 계산
                    // 홀수/짝수 회문을 모두 처리할 수 있는 공식
                    start = i - (len - 1) / 2;

                    // 중심 i를 기준으로 회문의 끝 위치 계산
                    end = i + len / 2;
                }
            }

            // substring의 끝 인덱스는 포함되지 않으므로 end + 1
            return s.substring(start, end + 1);
        }

        // 주어진 중심에서 시작해 좌우로 확장하면서 회문 길이를 구하는 함수
        // left는 0보다 작아지면 안 되고,
        // right는 문자열 길이 이상이 되면 안 된다.
        // 또한 양쪽 문자가 같아야 회문이므로 세 조건을 만족할 때만 확장한다.
        private int expandFromCenter(String s, int left, int right) {

            // 범위를 벗어나지 않고, 양쪽 문자가 같으면 계속 확장
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }

            // while 종료 시 left, right는 실제 회문 범위보다 한 칸 더 나간 상태
            // 따라서 실제 회문 길이는 right - left - 1
            return right - left - 1;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        // 🔥 테스트 케이스
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