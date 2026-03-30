package _1_50;

public class _06_지그재그변환_0 {

    static class Solution {

        /*
            이 문제는 문자열을 지그재그 형태로 배치한 후,
            위에서부터 한 줄씩 읽어서 결과를 만드는 문제이다.

            핵심 아이디어:
            - 각 행(row)을 StringBuilder로 관리
            - 아래로 내려가다가 마지막 행에 도달하면 방향을 위로 변경
            - 위로 올라가다가 첫 행에 도달하면 다시 아래로 변경

            시간복잡도: O(n)
            공간복잡도: O(n)
        */
        public String convert(String s, int numRows) {

            // row가 1이면 그대로 반환 (지그재그 의미 없음)
            if (s == null || s.length() <= numRows || numRows == 1) {
                return s;
            }

            // 각 행을 저장할 StringBuilder 배열
            StringBuilder[] rows = new StringBuilder[numRows];

            for (int i = 0; i < numRows; i++) {
                rows[i] = new StringBuilder();
            }

            int currentRow = 0;
            boolean goingDown = false; // 방향 제어

            for (char c : s.toCharArray()) {

                rows[currentRow].append(c);

                // 첫 행 or 마지막 행이면 방향 변경
                if (currentRow == 0 || currentRow == numRows - 1) {
                    goingDown = !goingDown;
                }

                // 방향에 따라 이동
                currentRow += goingDown ? 1 : -1;
            }

            // 결과 합치기
            StringBuilder result = new StringBuilder();
            for (StringBuilder row : rows) {
                result.append(row);
            }

            return result.toString();
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        String s1 = "PAYPALISHIRING";

        System.out.println(sol.convert(s1, 3)); // "PAHNAPLSIIGYIR"
        System.out.println(sol.convert(s1, 4)); // "PINALSIGYAHRPI"
    }
}