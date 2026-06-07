package _1_50;

public class _12_정수를로마숫자로변환_0 {

    static class Solution {

        /*
            [전체 흐름 정리]

            이 문제는 정수 num을 로마 숫자 문자열로 변환하는 문제다.

            로마 숫자는 큰 값부터 작은 값 순서로 만들어야 한다.

            예를 들어 3749는
            3000 = MMM
            700  = DCC
            40   = XL
            9    = IX
            이므로 결과는 MMMDCCXLIX가 된다.

            중요한 점은 4, 9처럼 빼기 형태로 표현되는 값도
            미리 배열에 넣어두는 것이다.

            예:
            900 = CM
            400 = CD
            90  = XC
            40  = XL
            9   = IX
            4   = IV

            큰 값부터 확인하면서
            num이 해당 값보다 크거나 같으면
            로마 숫자를 결과에 추가하고,
            num에서 해당 값을 빼준다.

            num이 0이 될 때까지 반복하면 변환이 끝난다.

            시간 복잡도: O(1)
            공간 복잡도: O(1)
        */
        public String intToRoman(int num) {

            int[] values = {
                    1000, 900, 500, 400,
                    100, 90, 50, 40,
                    10, 9, 5, 4, 1
            };

            String[] romans = {
                    "M", "CM", "D", "CD",
                    "C", "XC", "L", "XL",
                    "X", "IX", "V", "IV", "I"
            };

            StringBuilder answer = new StringBuilder();

            for (int i = 0; i < values.length; i++) {

                while (num >= values[i]) {
                    answer.append(romans[i]);
                    num -= values[i];
                }
            }

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