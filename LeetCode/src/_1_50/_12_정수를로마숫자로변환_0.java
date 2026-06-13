package _1_50;

public class _12_정수를로마숫자로변환_0 {

    static class Solution {

        /*
            [문제]

            정수(num)를 로마 숫자로 변환한다.

            예)
            3749 → MMMDCCXLIX
            58   → LVIII
            1994 → MCMXCIV

            --------------------------------------------------

            [핵심 아이디어]

            숫자 값과 로마 숫자를 서로 매핑해둔다.

            1000 → M
             900 → CM
             500 → D
             400 → CD
             100 → C
              90 → XC
              50 → L
              40 → XL
              10 → X
               9 → IX
               5 → V
               4 → IV
               1 → I

            큰 값부터 차례대로 확인하면서

            1. 현재 숫자가 해당 값 이상이면
               - 로마 숫자를 결과에 추가
               - 해당 값을 숫자에서 차감

            2. 더 이상 차감할 수 없으면
               다음 숫자로 이동

            3. num이 0이 될 때까지 반복

            --------------------------------------------------

            [예시]

            num = 58

            58 >= 50
            → L 추가
            → num = 8

            8 >= 5
            → V 추가
            → num = 3

            3 >= 1
            → I 추가
            → num = 2

            2 >= 1
            → I 추가
            → num = 1

            1 >= 1
            → I 추가
            → num = 0

            결과 : LVIII

            --------------------------------------------------

            시간 복잡도 : O(1)
            (배열 크기가 고정 13개)

            공간 복잡도 : O(1)
        */
        public String intToRoman(int num) {

            // 숫자 값 배열
            int[] values = {
                    1000, 900, 500, 400,
                    100, 90, 50, 40,
                    10, 9, 5, 4, 1
            };

            // values와 같은 인덱스의 로마 숫자 배열
            String[] romans = {
                    "M", "CM", "D", "CD",
                    "C", "XC", "L", "XL",
                    "X", "IX", "V", "IV", "I"
            };

            // 결과를 저장할 문자열
            StringBuilder answer = new StringBuilder();

            // 가장 큰 값부터 순서대로 확인
            for (int i = 0; i < values.length; i++) {

                /*
                    현재 숫자가 values[i] 이상인 동안 반복

                    예)
                    num = 3749
                    values[i] = 1000

                    M 추가
                    num = 2749

                    M 추가
                    num = 1749

                    M 추가
                    num = 749
                 */
                while (num >= values[i]) {

                    // 해당 로마 숫자를 결과에 추가
                    answer.append(romans[i]);

                    // 사용한 값만큼 차감
                    num -= values[i];
                }
            }

            // 최종 로마 숫자 반환
            return answer.toString();
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        System.out.println(sol.intToRoman(3749)); // MMMDCCXLIX
        System.out.println(sol.intToRoman(58));   // LVIII
        System.out.println(sol.intToRoman(1994)); // MCMXCIV
    }
}