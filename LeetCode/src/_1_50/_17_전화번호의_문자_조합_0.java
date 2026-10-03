package _1_50;

import java.util.ArrayList;
import java.util.List;

public class _17_전화번호의_문자_조합_0 {

    static class Solution {

        /*
         * [LeetCode 17 - Letter Combinations of a Phone Number]
         *
         * 전화번호 숫자 2~9에는 각각 알파벳이 지정되어 있다.
         *
         * 2 → abc
         * 3 → def
         * 4 → ghi
         * 5 → jkl
         * 6 → mno
         * 7 → pqrs
         * 8 → tuv
         * 9 → wxyz
         *
         * 입력된 각 숫자에서 알파벳을 하나씩 선택하여
         * 만들 수 있는 모든 문자열 조합을 반환한다.
         *
         *
         * 예)
         *
         * digits = "23"
         *
         * 2 → abc
         * 3 → def
         *
         * 결과
         *
         * [ad, ae, af,
         *  bd, be, bf,
         *  cd, ce, cf]
         *
         *
         * --------------------------------------------------
         * [DFS + Backtracking]
         * --------------------------------------------------
         *
         * DFS
         * → 현재 숫자에서 문자 하나를 선택하고
         *   다음 숫자로 깊게 탐색한다.
         *
         * Backtracking
         * → 하나의 조합을 만든 후
         *   마지막으로 선택한 문자를 제거하고
         *   직전 선택 지점으로 돌아가 다른 문자를 탐색한다.
         *
         *
         * 예) digits = "23"
         *
         * index = 0
         * 2 → abc
         *
         * a 선택
         *
         *      ↓ DFS
         *
         * index = 1
         * 3 → def
         *
         *      d 선택 → ad 저장
         *      d 제거
         *
         *      e 선택 → ae 저장
         *      e 제거
         *
         *      f 선택 → af 저장
         *      f 제거
         *
         * index = 1 탐색 완료
         *
         *      ↑ 이전 재귀호출로 복귀
         *
         * a 제거
         *
         * index = 0의 for문에서
         * 다음 문자인 b를 선택하여 다시 탐색한다.
         *
         *
         * 중요)
         *
         * backtrack(..., index + 1, ...)
         *
         * 위 코드는 현재 호출의 index 자체를 변경하는 것이 아니다.
         *
         * 현재 호출은 자신의 index와 for문 위치를 유지한 상태로
         * 다음 재귀호출에게 index + 1 값을 전달한다.
         *
         * 따라서 자식 재귀호출이 끝나면
         * 부모 재귀호출의 기존 for문 위치로 돌아와
         * 다음 문자를 계속 탐색할 수 있다.
         *
         *
         * --------------------------------------------------
         * [주요 변수]
         * --------------------------------------------------
         *
         * digits
         * → 입력받은 전화번호 숫자 문자열
         *
         * index
         * → 현재 처리하고 있는 digits의 위치
         *
         * current
         * → 현재까지 선택한 문자들을 저장하는 StringBuilder
         *
         * result
         * → 완성된 문자열 조합들을 저장하는 List
         *
         * map
         * → 전화번호 숫자와 알파벳의 대응표
         *
         *
         * --------------------------------------------------
         * [기저 조건]
         * --------------------------------------------------
         *
         * index == digits.length()
         *
         * 모든 숫자에서 문자 선택이 완료되었다는 의미이다.
         *
         * 예) digits = "234"
         *
         * index = 0 → 숫자 2 처리
         * index = 1 → 숫자 3 처리
         * index = 2 → 숫자 4 처리
         * index = 3 → 모든 숫자 처리 완료
         *
         * 이때 current에 완성된 문자열이 들어 있으므로
         * result에 저장하고 현재 재귀호출을 종료한다.
         *
         *
         * --------------------------------------------------
         * [핵심 흐름]
         * --------------------------------------------------
         *
         * 문자 선택
         *      ↓
         * current에 추가
         *      ↓
         * 다음 index 재귀호출 (DFS)
         *      ↓
         * 문자열 완성
         *      ↓
         * result에 저장
         *      ↓
         * return하여 직전 재귀호출로 복귀
         *      ↓
         * 마지막 문자 제거 (Backtracking)
         *      ↓
         * for문의 다음 문자 선택
         *
         *
         * 시간 복잡도 : O(N × 4^N)
         *
         * N : digits의 길이
         *
         * 각 숫자는 최대 4개의 문자(7, 9)를 가질 수 있고,
         * 완성된 문자열을 만들 때 길이 N의 문자열을 생성한다.
         */

        public List<String> letterCombinations(String digits) {

            // 완성된 문자열 조합을 저장할 List
            List<String> result = new ArrayList<>();

            /*
             * 입력값이 없는 경우 빈 List 반환
             *
             * null을 먼저 검사하여
             * NullPointerException 발생을 방지한다.
             */
            if (digits == null || digits.length() == 0) {
                return result;
            }

            /*
             * 전화번호 숫자 → 알파벳 대응표
             *
             * 배열 index를 전화번호 숫자와 동일하게 사용하기 위해
             * 0과 1은 빈 문자열로 둔다.
             *
             * map[2] → "abc"
             * map[3] → "def"
             * ...
             * map[9] → "wxyz"
             */
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

            /*
             * 첫 번째 숫자(index = 0)부터
             * DFS + Backtracking 시작
             *
             * current는 처음에는 아무 문자도 선택하지 않았으므로
             * 빈 StringBuilder로 시작한다.
             */
            backtrack(
                    digits,
                    0,
                    new StringBuilder(),
                    map,
                    result
            );

            // 모든 탐색이 완료된 결과 반환
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
             * [기저 조건]
             *
             * index가 digits의 길이와 같다는 것은
             * 모든 숫자에서 문자 하나씩 선택했다는 의미이다.
             *
             * 예)
             *
             * digits = "23"
             *
             * index = 0 → 숫자 2
             * index = 1 → 숫자 3
             * index = 2 → 모든 숫자 처리 완료
             *
             * current = "ad"와 같은 완성된 문자열을
             * result에 저장한다.
             */
            if (index == digits.length()) {

                result.add(current.toString());

                // 현재 재귀호출 종료
                // 자신을 호출했던 직전 재귀호출로 돌아간다.
                return;
            }

            /*
             * 현재 index 위치의 숫자 문자를
             * 실제 정수값으로 변환한다.
             *
             * 예)
             *
             * digits.charAt(index) = '2'
             *
             * '2' - '0'
             * 50  - 48
             * = 2
             */
            int digit = digits.charAt(index) - '0';

            /*
             * 현재 숫자에 대응하는 알파벳 문자열을 가져온다.
             *
             * digit = 2
             * map[2] = "abc"
             *
             * 따라서
             * letters = "abc"
             */
            String letters = map[digit];

            /*
             * 현재 숫자에 대응하는 알파벳을
             * 하나씩 선택한다.
             *
             * letters = "abc"라면
             *
             * i = 0 → a
             * i = 1 → b
             * i = 2 → c
             */
            for (int i = 0; i < letters.length(); i++) {

                /*
                 * [1. 문자 선택]
                 *
                 * 현재 문자를 current에 추가한다.
                 *
                 * 예)
                 *
                 * current = ""
                 * 문자 = 'a'
                 *
                 * ↓
                 *
                 * current = "a"
                 */
                current.append(letters.charAt(i));

                /*
                 * [2. DFS]
                 *
                 * 현재 선택한 문자를 유지한 상태에서
                 * 다음 숫자(index + 1)를 처리한다.
                 *
                 * 중요)
                 *
                 * 현재 호출의 index가 변경되는 것이 아니다.
                 *
                 * index + 1 값을
                 * 새롭게 호출되는 backtrack()에 전달한다.
                 *
                 * 따라서 현재 호출은 자신의 index와
                 * for문 위치를 그대로 유지하면서 기다린다.
                 */
                backtrack(
                        digits,
                        index + 1,
                        current,
                        map,
                        result
                );

                /*
                 * [3. Backtracking]
                 *
                 * 자식 재귀호출이 종료되어 돌아오면
                 * 마지막으로 선택했던 문자를 제거한다.
                 *
                 * 예)
                 *
                 * current = "ad"
                 *
                 * d 제거
                 *
                 * ↓
                 *
                 * current = "a"
                 *
                 * 이후 for문이 다음 문자로 이동한다.
                 *
                 * d → e → f
                 *
                 * 즉,
                 *
                 * "ad" 저장
                 *      ↓
                 * d 제거
                 *      ↓
                 * "ae" 탐색
                 */
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