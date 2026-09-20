package _1_50;

import java.util.ArrayList;
import java.util.List;

public class _17_전화번호의_문자_조합_0 {

    static class Solution {

        public List<String> letterCombinations(String digits) {

            /*
             * [LeetCode 17 - Letter Combinations of a Phone Number]
             *
             * 전화번호 숫자 2~9가 주어졌을 때
             * 각 숫자에 대응하는 문자들을 이용하여
             * 만들 수 있는 모든 문자 조합을 반환하는 문제.
             *
             *
             * [전화번호 문자 대응]
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
             *
             * 예)
             *
             * digits = "23"
             *
             * 2 → abc
             * 3 → def
             *
             * 가능한 조합
             *
             * a + d → ad
             * a + e → ae
             * a + f → af
             *
             * b + d → bd
             * b + e → be
             * b + f → bf
             *
             * c + d → cd
             * c + e → ce
             * c + f → cf
             *
             * 결과
             *
             * [ad, ae, af, bd, be, bf, cd, ce, cf]
             *
             *
             * [핵심 알고리즘]
             *
             * 백트래킹(Backtracking)을 사용한다.
             *
             * 숫자를 하나씩 확인하면서
             * 해당 숫자에 대응하는 문자를 하나 선택한다.
             *
             *              ↓
             *
             * 다음 숫자로 이동한다.
             *
             *              ↓
             *
             * 모든 숫자에 대한 문자를 선택하면
             * 하나의 문자열 조합이 완성된다.
             *
             *              ↓
             *
             * 완성된 문자열을 result에 저장한다.
             *
             *              ↓
             *
             * 마지막에 추가했던 문자를 제거하고
             * 다른 문자를 선택한다.
             *
             *
             * [알고리즘 순서]
             *
             * ① 숫자 → 문자 대응표를 만든다.
             *
             *      2 → abc
             *      3 → def
             *      ...
             *
             *              ↓
             *
             * ② digits의 첫 번째 숫자부터 탐색한다.
             *
             *              ↓
             *
             * ③ 현재 숫자에 대응하는 문자들을 하나씩 선택한다.
             *
             *              ↓
             *
             * ④ 문자를 하나 추가하고 다음 숫자로 이동한다.
             *
             *              ↓
             *
             * ⑤ digits의 모든 숫자를 사용했다면
             *    완성된 문자열을 result에 저장한다.
             *
             *              ↓
             *
             * ⑥ 마지막 문자를 제거한 후
             *    다음 문자를 선택한다.
             *
             *
             * [시간복잡도]
             *
             * 숫자 하나당 최대 4개의 문자가 존재한다.
             *
             * digits 길이를 N이라고 하면
             *
             * 최대 조합 개수 : 4^N
             *
             * 각 조합을 문자열로 만드는 비용까지 고려하면
             *
             * 시간복잡도 : O(N × 4^N)
             *
             * 문제에서 digits.length <= 4이므로
             * 충분히 빠르게 처리할 수 있다.
             */

            // 최종 문자 조합을 저장할 리스트
            List<String> result = new ArrayList<>();

            /*
             * digits가 비어있는 경우
             *
             * 만들 수 있는 문자 조합이 없으므로
             * 빈 리스트를 반환한다.
             */
            if (digits == null || digits.length() == 0) {

                return result;

            }

            /*
             * 숫자 → 문자 대응표
             *
             * 배열의 index를 전화번호 숫자와 동일하게 맞춘다.
             *
             * index 0 → ""
             * index 1 → ""
             * index 2 → "abc"
             * index 3 → "def"
             * ...
             *
             * 이렇게 만들어두면
             *
             * map[2] → "abc"
             * map[7] → "pqrs"
             *
             * 형태로 바로 접근할 수 있다.
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
             * 백트래킹 시작
             *
             * index = 0
             * → digits의 첫 번째 숫자부터 시작
             *
             * StringBuilder
             * → 현재까지 선택한 문자 조합을 저장
             */
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
             * 종료 조건
             *
             * index가 digits.length()와 같아졌다는 것은
             * 모든 숫자에 대해 문자를 하나씩 선택했다는 의미이다.
             *
             * 예)
             *
             * digits = "23"
             *
             * index = 0 → 숫자 2
             * index = 1 → 숫자 3
             * index = 2 → 모든 숫자 선택 완료
             *
             * current = "ad"
             *
             * → result에 저장
             */
            if (index == digits.length()) {

                result.add(current.toString());

                return;

            }

            /*
             * 현재 위치의 숫자를 가져온다.
             *
             * 예)
             *
             * digits = "23"
             * index = 0
             *
             * digits.charAt(0)
             * → 문자 '2'
             *
             * '2' - '0'
             * → 숫자 2
             *
             * 문자 형태의 숫자를 실제 int 숫자로 변환하는 방법이다.
             */
            int digit = digits.charAt(index) - '0';

            /*
             * 현재 숫자에 대응하는 문자들을 가져온다.
             *
             * digit = 2
             *
             * map[2]
             * → "abc"
             */
            String letters = map[digit];

            /*
             * 현재 숫자에 대응하는 모든 문자를 하나씩 선택한다.
             *
             * 예)
             *
             * letters = "abc"
             *
             * a 선택
             * b 선택
             * c 선택
             *
             * 각각의 경우에 대해 다음 숫자로 이동한다.
             */
            for (int i = 0; i < letters.length(); i++) {

                /*
                 * 현재 문자 선택
                 *
                 * 예)
                 *
                 * current = ""
                 *
                 * 'a' 선택
                 *
                 * current = "a"
                 */
                current.append(letters.charAt(i));

                /*
                 * 다음 숫자로 이동
                 *
                 * 예)
                 *
                 * digits = "23"
                 *
                 * 현재 index = 0
                 * → 숫자 2 처리 중
                 *
                 * index + 1
                 * → 숫자 3으로 이동
                 */
                backtrack(
                        digits,
                        index + 1,
                        current,
                        map,
                        result
                );

                /*
                 * 백트래킹
                 *
                 * 방금 추가했던 마지막 문자를 제거한다.
                 *
                 * 예)
                 *
                 * current = "ad"
                 *
                 * 'd'에 대한 탐색 완료
                 *
                 * 마지막 문자 제거
                 *
                 * current = "a"
                 *
                 * 이후
                 *
                 * 'e', 'f'
                 *
                 * 를 다시 선택할 수 있다.
                 *
                 *
                 * 즉,
                 *
                 * append()
                 * → 선택
                 *
                 * deleteCharAt()
                 * → 선택 취소
                 *
                 * 이것이 백트래킹의 핵심이다.
                 */
                current.deleteCharAt(current.length() - 1);

            }

        }

    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        /*
         * digits = "23"
         *
         * 2 → abc
         * 3 → def
         *
         * 가능한 조합
         *
         * ad ae af
         * bd be bf
         * cd ce cf
         */
        System.out.println(sol.letterCombinations("23"));

        // 결과 :
        // [ad, ae, af, bd, be, bf, cd, ce, cf]


        /*
         * digits = "2"
         *
         * 2 → abc
         */
        System.out.println(sol.letterCombinations("2"));

        // 결과 :
        // [a, b, c]


        /*
         * digits = "79"
         *
         * 7 → pqrs
         * 9 → wxyz
         *
         * 총 4 × 4 = 16개의 조합
         */
        System.out.println(sol.letterCombinations("79"));

        // 결과 :
        // [pw, px, py, pz,
        //  qw, qx, qy, qz,
        //  rw, rx, ry, rz,
        //  sw, sx, sy, sz]

    }

}