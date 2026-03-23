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
    	*/
        public String longestPalindrome(String s) {

            // 길이가 1 이하이면 그대로 반환
            if (s == null || s.length() < 2) {
                return s;
            }

            int start = 0; // 가장 긴 회문의 시작 인덱스
            int end = 0;   // 가장 긴 회문의 끝 인덱스

            for (int i = 0; i < s.length(); i++) {

                // 홀수 길이 회문 검사 (중심이 i 하나)
                int len1 = expandFromCenter(s, i, i);

                // 짝수 길이 회문 검사 (중심이 i, i+1 두 개)
                int len2 = expandFromCenter(s, i, i + 1);

                // 두 경우 중 더 긴 회문 길이 선택
                int len = Math.max(len1, len2);

                // 현재 찾은 회문이 기존 최장 회문보다 길면 시작/끝 갱신
                if (len > end - start + 1) {
                    start = i - (len - 1) / 2;
                    end = i + len / 2;
                }
            }

            return s.substring(start, end + 1);
        }

        private int expandFromCenter(String s, int left, int right) {

            // 범위를 벗어나지 않고, 양쪽 문자가 같으면 계속 확장
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }

            // while 종료 시 left, right는 한 칸씩 더 나간 상태이므로 길이는 right - left - 1
            return right - left - 1;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        String s1 = "babad";
        String s2 = "cbbd";

        System.out.println(sol.longestPalindrome(s1)); // "bab" 또는 "aba"
        System.out.println(sol.longestPalindrome(s2)); // "bb"
    }
}