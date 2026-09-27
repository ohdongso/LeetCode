package _1_50;

import java.util.ArrayList;
import java.util.List;

public class _17_전화번호의_문자_조합_0 {

    static class Solution {

        /*
            [전체 흐름 정리]

            전화번호 숫자 2~9에는 각각 알파벳이 지정되어 있다.

            2 → abc
            3 → def
            4 → ghi
            5 → jkl
            6 → mno
            7 → pqrs
            8 → tuv
            9 → wxyz

            입력된 각 숫자에서 알파벳을 하나씩 선택하여
            만들 수 있는 모든 문자열 조합을 반환한다.

            예)

            digits = "23"

            2 → abc
            3 → def

            결과

            [ad, ae, af, bd, be, bf, cd, ce, cf]

            입력 숫자의 개수에 따라
            만들어지는 문자열의 길이도 달라진다.

            "2"   → 1글자
            "23"  → 2글자
            "234" → 3글자


            [DFS + Backtracking]

            DFS
            → 문자를 하나 선택하고 다음 숫자로 깊게 탐색한다.

            Backtracking
            → 하나의 조합을 만든 후
              마지막 선택을 취소하고 이전 선택 지점으로 돌아간다.

            예) digits = "234"

            a → d → g → "adg" 저장

            g 제거 → "ad"

            h 선택 → "adh" 저장

            h 제거 → "ad"

            i 선택 → "adi" 저장

            즉, 처음까지 돌아가는 것이 아니라
            직전 선택 지점으로 돌아가 다른 문자를 탐색한다.


            [주요 변수]

            index
            → 현재 처리하고 있는 digits의 숫자 위치

            current
            → 현재 만들고 있는 문자열

            result
            → 완성된 문자열들을 저장하는 List


            [기저 조건]

            index == digits.length()

            모든 숫자에 대한 문자 선택이 완료된 상태이다.

            예) digits = "234"

            index = 0 → 숫자 2
            index = 1 → 숫자 3
            index = 2 → 숫자 4
            index = 3 → 모든 숫자 처리 완료

            이때 current의 문자열을 result에 저장하고
            현재 재귀 호출을 종료한다.


            [핵심 흐름]

            문자 선택
            → current에 추가
            → 재귀 호출(DFS)
            → 문자열 완성
            → result 저장
            → 이전 단계로 복귀
            → 마지막 문자 제거(Backtracking)
            → 다른 문자 선택


            시간 복잡도 : O(N × 4^N)

            N : digits의 길이
        */

        public List<String> letterCombinations(String digits) {

            // 완성된 문자열 조합 저장
            List<String> result = new ArrayList<>();

            // 입력값이 없는 경우
            if (digits == null || digits.length() == 0) {

                return result;

            }

            // 전화번호 숫자 → 알파벳 대응표
            String[] map = {
                    "",
                    "",
                    "abc",
                    "def",
                    "ghi",
                    "jkl",
                    "mno",
                    "pqrs",
                    "tuv",
                    "wxyz"
            };

            // 첫 번째 숫자(index = 0)부터 DFS + Backtracking 시작
            backtrack(
                    digits,
                    0,
                    new StringBuilder(),
                    map,
                    result
            );

            return result;

        }

        private void backtrack(
                String digits,
                int index,
                StringBuilder current,
                String[] map,
                List<String> result
        ) {

            /*
                기저 조건

                모든 숫자에 대한 문자 선택 완료

                current의 완성된 문자열을
                result에 저장
            */
            if (index == digits.length()) {

                result.add(current.toString());

                return;

            }

            // 현재 index 위치의 숫자 가져오기
            int digit = digits.charAt(index) - '0';

            // 현재 숫자에 해당하는 알파벳 가져오기
            String letters = map[digit];

            // 현재 숫자의 알파벳을 하나씩 선택
            for (int i = 0; i < letters.length(); i++) {

                // 문자 선택
                current.append(letters.charAt(i));

                // 다음 숫자로 이동 (DFS)
                backtrack(
                        digits,
                        index + 1,
                        current,
                        map,
                        result
                );

                // 마지막 선택 취소 (Backtracking)
                current.deleteCharAt(current.length() - 1);

            }

        }

    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        System.out.println(sol.letterCombinations("23"));

        // [ad, ae, af, bd, be, bf, cd, ce, cf]


        System.out.println(sol.letterCombinations("2"));

        // [a, b, c]


        System.out.println(sol.letterCombinations("79"));

        // [pw, px, py, pz,
        //  qw, qx, qy, qz,
        //  rw, rx, ry, rz,
        //  sw, sx, sy, sz]


        System.out.println(sol.letterCombinations("234"));

        // [adg, adh, adi,
        //  aeg, aeh, aei,
        //  afg, afh, afi,
        //  bdg, bdh, bdi,
        //  beg, beh, bei,
        //  bfg, bfh, bfi,
        //  cdg, cdh, cdi,
        //  ceg, ceh, cei,
        //  cfg, cfh, cfi]

    }

}