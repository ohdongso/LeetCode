
package _1_50;

public class _19_리스트끝에서n번째노드를제거합니다_0 {

    /*
     * LeetCode 19. Remove Nth Node From End of List
     *
     * 문제:
     * 단일 연결 리스트에서 끝에서 n번째 노드를 제거한다.
     *
     * 풀이:
     * Dummy Node + Two Pointers (fast, slow)
     *
     * 시간 복잡도: O(N)
     * 공간 복잡도: O(1)
     *
     * LeetCode 제출 결과:
     * Accepted - 208 / 208 Testcases Passed
     */

    // 단일 연결 리스트의 노드
    // val: 현재 노드의 값
    // next: 다음 노드 객체의 참조값
    static class ListNode {

        int val;
        ListNode next;

        ListNode() {

        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Solution {

        public ListNode removeNthFromEnd(ListNode head, int n) {

            // 1. 실제 첫 번째 노드 앞에 임시 노드 생성
            // 첫 번째 노드를 삭제하는 경우도 동일하게 처리
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            // 2. 두 참조 변수가 동일한 dummy 노드를 참조
            ListNode fast = dummy;
            ListNode slow = dummy;

            // 3. fast를 n + 1칸 먼저 이동
            // 두 포인터 사이에 n + 1칸의 간격 생성
            for (int i = 0; i <= n; i++) {
                fast = fast.next;
            }

            // 4. fast가 null이 될 때까지 함께 이동
            // 반복문 종료 시 slow는 삭제 대상의 바로 앞 노드
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }

            // 5. 삭제 대상 노드를 건너뛰도록 연결 변경
            // 예: 1 -> 2 -> 3 -> 4 -> 5
            // n = 2인 경우 4번 노드 제거
            // 결과: 1 -> 2 -> 3 -> 5
            slow.next = slow.next.next;

            // 6. 임시 노드를 제외한 실제 리스트 반환
            return dummy.next;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        // ========================================
        // 예제 1. 끝에서 두 번째 노드 제거
        // ========================================

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);

        ListNode result1 = sol.removeNthFromEnd(head1, 2);

        System.out.print("예제 1: ");
        printList(result1); // [1, 2, 3, 5]

        // ========================================
        // 예제 2. 노드가 하나인 경우
        // ========================================

        ListNode head2 = new ListNode(1);

        ListNode result2 = sol.removeNthFromEnd(head2, 1);

        System.out.print("예제 2: ");
        printList(result2); // []

        // ========================================
        // 예제 3. 마지막 노드 제거
        // ========================================

        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);

        ListNode result3 = sol.removeNthFromEnd(head3, 1);

        System.out.print("예제 3: ");
        printList(result3); // [1]

        // ========================================
        // 추가 학습 1. 참조 변수 재대입
        // ========================================

        // head4와 temp4는 처음에 동일한 노드를 참조한다.
        ListNode head4 = new ListNode(1);
        head4.next = new ListNode(2);

        ListNode temp4 = head4;

        // temp4만 새로운 노드(3)를 참조하도록 변경
        // head4가 참조하는 기존 리스트는 변경되지 않는다.
        temp4 = new ListNode(3);

        System.out.print("참조 변수 재대입 - head4: ");
        printList(head4); // [1, 2]

        System.out.print("참조 변수 재대입 - temp4: ");
        printList(temp4); // [3]

        // ========================================
        // 추가 학습 2. next 필드 변경
        // ========================================

        ListNode head5 = new ListNode(1);
        head5.next = new ListNode(2);

        // head5와 temp5는 동일한 노드(1)를 참조
        ListNode temp5 = head5;

        // 값이 3인 새로운 노드 생성
        // temp5가 참조하는 노드의 next 연결 변경
        temp5.next = new ListNode(3);

        // head5도 동일한 노드를 참조하므로
        // head5.next 역시 새로운 노드(3)를 가리킨다.
        System.out.print("next 필드 변경 - head5: ");
        printList(head5); // [1, 3]

        System.out.print("next 필드 변경 - temp5: ");
        printList(temp5); // [1, 3]

        // ========================================
        // 학습 핵심 정리
        // ========================================

        // 1. temp = new ListNode(4);
        //    -> temp 참조 변수 자체를 재대입한다.
        //    -> 기존 객체의 next 연결은 변경되지 않는다.

        // 2. temp.next = new ListNode(4);
        //    -> temp가 참조하는 객체의 next 필드를 변경한다.
        //    -> 동일한 객체를 참조하는 다른 변수에도 반영된다.

        // 3. temp = temp.next;
        //    -> temp가 다음 노드를 참조하도록 이동한다.
        //    -> 기존 노드의 next 연결은 변경되지 않는다.

        // 4. slow.next = slow.next.next;
        //    -> slow 다음 노드를 건너뛰도록 연결을 변경한다.
        //    -> 연결 리스트에서 노드를 제거하는 핵심 로직이다.
    }

    // 연결 리스트 출력 메서드
    public static void printList(ListNode head) {

        System.out.print("[");

        while (head != null) {

            System.out.print(head.val);

            if (head.next != null) {
                System.out.print(", ");
            }

            // 다음 노드로 참조 변수 이동
            head = head.next;
        }

        System.out.println("]");
    }
}
