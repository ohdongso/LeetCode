package _1_50;

import java.util.HashSet;  
import java.util.Set;

public class _03_중복문자가없는가장긴부분문자열_0 {

    /*
        문제 정리
        문자열 s가 주어졌을 때,
        중복 문자가 없는 가장 긴 부분 문자열의 길이를 구하는 문제이다.

        핵심 아이디어
        문자열을 처음부터 끝까지 확인하면서
        현재 구간(window) 안에 중복 문자가 없도록 유지한다.

        left  : 현재 부분 문자열의 시작 위치
        right : 현재 부분 문자열의 끝 위치
		
        Set 필요성
        현재 부분 문자열 안에 어떤 문자가 들어있는지 빠르게 확인하기 위해 사용한다.
        중복 문자가 들어오면 left를 이동시키면서 중복이 사라질 때까지 제거한다.

        결론
        Sliding Window + HashSet을 사용하면
        중복 여부를 빠르게 확인하면서 가장 긴 길이를 효율적으로 구할 수 있다.
     */

    static class Solution {

        public int lengthOfLongestSubstring(String s) {

            // 현재 윈도우 안에 포함된 문자 저장
            Set<Character> set = new HashSet<>();

            int left = 0; // 현재 부분 문자열의 시작 위치
            int maxLength = 0; // 지금까지 발견한 가장 긴 길이 저장

            for (int right = 0; right < s.length(); right++) { // right 포인터가 문자열 끝까지 이동한다.

                char current = s.charAt(right); // 현재 문자 가져오기

                // 중복 문자가 있다면 중복이 사라질 때까지 left 이동
                while (set.contains(current)) {
                    set.remove(s.charAt(left));
                    left++;
                }

                // 중복이 없는 상태가 되었으므로 현재 문자 추가
                set.add(current);

                // 현재 윈도우(중복되지 않은 문자열) 길이 계산
                int length = right - left + 1;

                // 최대 길이 갱신
                maxLength = Math.max(maxLength, length);
            }

            return maxLength;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        String s4 = "";
        String s5 = "dvdf";

        System.out.println("입력: " + s1 + " / 결과: " + sol.lengthOfLongestSubstring(s1)); // 3
        System.out.println("입력: " + s2 + " / 결과: " + sol.lengthOfLongestSubstring(s2)); // 1
        System.out.println("입력: " + s3 + " / 결과: " + sol.lengthOfLongestSubstring(s3)); // 3
        System.out.println("입력: " + s4 + " / 결과: " + sol.lengthOfLongestSubstring(s4)); // 0
        System.out.println("입력: " + s5 + " / 결과: " + sol.lengthOfLongestSubstring(s5)); // 3
    }
}