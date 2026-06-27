package _1_50;

public class _14_가장긴공통접두사_0 {

    static class Solution {

        /*
            [전체 흐름 정리]

            문자열 배열에서
            가장 긴 공통 접두사를 찾는 문제이다.

            예)

            flower
            flow
            flight

            앞에서부터 비교하면

            f -> 공통
            l -> 공통
            o -> 다름

            따라서 결과는 "fl"

            첫 번째 문자열을 기준으로
            각 위치의 문자를 다른 문자열들과 비교한다.

            하나라도 다르면
            지금까지의 접두사를 반환한다.

            시간 복잡도 : O(n * m)

            n : 문자열 개수
            m : 문자열 길이
        */
        public String longestCommonPrefix(String[] strs) {

            // 예외 처리
            if (strs == null || strs.length == 0) {
                return "";
            }

            // 첫 번째 문자열 기준
            String first = strs[0];

            // 첫 번째 문자열 문자 하나씩 확인
            for (int i = 0; i < first.length(); i++) {

                char current = first.charAt(i);

                // 나머지 문자열들과 비교
                for (int j = 1; j < strs.length; j++) {

                    /*
                        현재 문자열 길이를 초과했거나

                        문자가 다르면

                        지금까지의 접두사 반환
                    */
                    if (i >= strs[j].length() || strs[j].charAt(i) != current) {

                        return first.substring(0, i);
                    }
                }
            }

            // 첫 번째 문자열 전체가 공통인 경우
            return first;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        System.out.println(sol.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        // fl

        System.out.println(sol.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        // ""

        System.out.println(sol.longestCommonPrefix(new String[]{"abc", "abc", "abc"}));
        // abc
    }
}