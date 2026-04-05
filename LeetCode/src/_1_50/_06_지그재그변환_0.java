package _1_50;

public class _06_지그재그변환_0 {

    static class Solution {

        /*
            [문제 설명]
            문자열을 지그재그 형태로 배치한 뒤,
            각 행을 위에서부터 순서대로 읽어 새로운 문자열을 만드는 문제이다.

            예)
            s = "PAYPALISHIRING", numRows = 3

            배치 결과:
            P   A   H   N
            A P L S I I G
            Y   I   R

            행 순서대로 읽으면:
            "PAHNAPLSIIGYIR"

            [핵심 아이디어]
            1. 각 행을 StringBuilder로 관리한다.
            2. 현재 행(currentRow)에 문자를 하나씩 추가한다.
            3. 아래로 내려가다가 마지막 행에 도달하면 위로 방향을 바꾼다.
            4. 위로 올라가다가 첫 번째 행에 도달하면 다시 아래로 방향을 바꾼다.

            즉, currentRow가
            0 -> 1 -> 2 -> 1 -> 0 -> 1 ...
            이런 식으로 움직이도록 구현하면 된다.

            시간복잡도: O(n)
            공간복잡도: O(n)
        */
        public String convert(String s, int numRows) {

            /*
                예외 처리
                - 문자열이 null인 경우
                - 행이 1개인 경우: 지그재그로 나눌 의미가 없음
                - 문자열 길이가 행 개수 이하인 경우: 그대로 읽어도 동일
            */
            if (s == null || s.length() <= numRows || numRows == 1) {
                return s;
            }

            // 각 행의 문자열을 저장할 StringBuilder 배열 생성
            /*
            	rows[0] = "PAHN"
				rows[1] = "APLSIIG"
				rows[2] = "YIR"
             */
            StringBuilder[] rows = new StringBuilder[numRows];

            // 각 행마다 StringBuilder 초기화
            // 배열만 만들었다고 해서 바로 문자열을 붙일 수 있는 건 아님
            // 각 칸에 실제 StringBuilder 객체를 넣어줘야 함
            for (int i = 0; i < numRows; i++) {
                rows[i] = new StringBuilder();
            }

            // 현재 문자를 넣을 행 번호
            int currentRow = 0;

            // 이동 방향
            // true  : 아래로 이동
            // false : 위로 이동
            boolean goingDown = false;

            // 문자열의 각 문자를 순서대로 지그재그 행에 배치
            for (char c : s.toCharArray()) {

                // 현재 행에 문자 추가
                rows[currentRow].append(c);

                /*
                    첫 행 또는 마지막 행에 도달하면 방향 반전

                    예를 들어 numRows = 3이면:
                    0 -> 1 -> 2 -> 1 -> 0 -> 1 -> 2 ...
                    이런 식으로 움직이게 된다.
                */
                // currentRow가 시작값 이거나 끝값이면, 방향 flag변수 반전
                if (currentRow == 0 || currentRow == numRows - 1) {
                    goingDown = !goingDown;
                }

                // 현재 방향에 따라 다음 행으로 이동
                // currentRow가 시작이나 끝값이라서 반전이 일어나면 현재행의 값을 증가시키거나 감소시키는 부분이 필요
                currentRow += goingDown ? 1 : -1;
            }

            // 각 행에 저장된 문자열을 위에서부터 순서대로 합침
            // 각 행의 배열을 한개의 StringBuilder에 합친다.
            StringBuilder result = new StringBuilder();
            for (StringBuilder row : rows) {
                result.append(row);
            }

            // 최종 변환 결과 반환
            return result.toString();
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        String s1 = "PAYPALISHIRING";

        // numRows = 3
        // 결과:
        // P   A   H   N
        // A P L S I I G
        // Y   I   R
        System.out.println(sol.convert(s1, 3)); // PAHNAPLSIIGYIR

        // numRows = 4
        // 결과:
        // P     I    N
        // A   L S  I G
        // Y A   H R
        // P     I
        System.out.println(sol.convert(s1, 4)); // PINALSIGYAHRPI
    }
}