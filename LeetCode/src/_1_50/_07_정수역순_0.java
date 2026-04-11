package _1_50;

public class _07_정수역순_0 {

    static class Solution {

        /*
            [문제 설명]
            32비트 정수 x가 주어지면, 숫자를 뒤집어서 반환한다.

            단,
            뒤집은 결과가 32비트 정수 범위를 벗어나면 0을 반환한다.

            예)
            123  → 321
            -123 → -321
            120  → 21

            [핵심 아이디어]
            1. x의 마지막 자리수를 하나씩 꺼낸다. (x % 10)
            2. 기존 결과값에 *10을 하고 새 자리수를 더한다.
            3. overflow 발생 여부를 미리 체크한다.

            시간복잡도: O(log n)
            공간복잡도: O(1)
        */
        public int reverse(int x) {

            int result = 0;

            while (x != 0) {

                 int digit = x % 10;   // 마지막 자리 추출
                 x /= 10;              // 자리수 제거

		         // ========================================
		         // 🔹 overflow 체크 (핵심 로직)
		         // ========================================
		
		         // 우리가 곧 수행할 연산:
		         // result = result * 10 + digit
		         // → 이 연산을 했을 때 int 범위(-21억 ~ 21억)를 넘는지 "미리" 검사하는 부분
		
		         // [양수 overflow 체크]
		         if (result > Integer.MAX_VALUE / 10 || 
		            (result == Integer.MAX_VALUE / 10 && digit > 7)) {
		             
		             // 설명:
		             // 1. result가 이미 MAX_VALUE/10보다 크면
		             //    → result * 10 하는 순간 무조건 범위 초과
		             
		             // 2. result가 딱 MAX_VALUE/10(=214748364)이라면
		             //    → 마지막 자리(digit)에 따라 결과가 갈림
		             //    → digit이 7까지는 괜찮지만, 8 이상이면 overflow 발생
		             
		             return 0;
		         }
		
		         // [음수 overflow 체크]
		         if (result < Integer.MIN_VALUE / 10 || 
		            (result == Integer.MIN_VALUE / 10 && digit < -8)) {
		             
		             // 설명:
		             // 1. result가 이미 MIN_VALUE/10보다 작으면
		             //    → result * 10 하는 순간 무조건 범위 초과
		             
		             // 2. result가 딱 MIN_VALUE/10(=-214748364)이라면
		             //    → 마지막 자리(digit)에 따라 결과가 갈림
		             //    → digit이 -8까지는 괜찮지만, -9 이하이면 overflow 발생
		             
		             return 0;
		         }
		
		         // ========================================
		         // 🔹 실제 자리수 뒤집기 연산
		         // ========================================
		
		         // 기존 숫자를 한 자리 왼쪽으로 밀고(*10)
		         // 새로 꺼낸 digit을 뒤에 붙임(+ digit)
		         // 예: 32 → 32*10 + 1 = 321
		         result = result * 10 + digit;
            }

            return result;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();
        
        /*
        	32비트라는 고정된 공간이 있고, 거기에 123을 2진수로 변환해서 넣는다.
        	00000000 00000000 00000000 01111011
         */
        System.out.println(sol.reverse(123));    // 321
        System.out.println(sol.reverse(-123));   // -321
        System.out.println(sol.reverse(120));    // 21
        System.out.println(sol.reverse(1534236469)); // 0 (overflow)
    }
}