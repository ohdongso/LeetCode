
package _1_50;

public class _19_리스트끝에서n번째노드를제거합니다_0 {

    // 단일 연결 리스트의 노드: 값(val)과 다음 노드의 참조(next)를 저장한다.
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

            // dummy는 실제 첫 노드 앞에 놓는 임시 노드다.
            // 첫 번째 노드를 삭제하는 경우도 같은 로직으로 처리할 수 있다.
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            // fast와 slow는 처음에 동일한 dummy 객체를 참조한다.
            ListNode fast = dummy;
            ListNode slow = dummy;

            // fast를 n+1칸 먼저 이동시켜 두 포인터 사이의 간격을 만든다.
            for (int i = 0; i <= n; i++) {
                fast = fast.next;
            }

            // fast가 null이 될 때까지 함께 이동하면
            // slow는 삭제할 노드의 바로 앞에 위치한다.
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }

            // 삭제 대상 노드를 건너뛰고 그다음 노드로 연결한다.
            // 예: 3 -> 4 -> 5 를 3 -> 5 로 변경
            slow.next = slow.next.next;

            // dummy 자체가 아니라 실제 리스트의 시작 노드를 반환한다.
            return dummy.next;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        // 예제 1: 1 -> 2 -> 3 -> 4 -> 5, 끝에서 두 번째(4) 삭제
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);

        // next를 연속 사용하면 참조를 따라 다음 노드에 접근한다.
        ListNode result1 = sol.removeNthFromEnd(head1, 2);
        printList(result1); // [1, 2, 3, 5]

        // 예제 2: 노드가 하나일 때 첫 노드 삭제
        ListNode head2 = new ListNode(1);
        ListNode result2 = sol.removeNthFromEnd(head2, 1);
        printList(result2); // []

        // 예제 3: 두 노드 중 마지막 노드 삭제
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);
        ListNode result3 = sol.removeNthFromEnd(head3, 1);
        printList(result3); // [1]

        // TODO (다음 학습): 참조 변수 자체를 재대입하는 것과
        // 참조 중인 객체의 next 필드를 변경하는 것의 차이를 비교하기.
        //
        // 1. temp = new ListNode(4);
        //    -> temp 참조 변수만 새로운 객체를 가리킨다.
        //
        // 2. temp.next = new ListNode(4);
        //    -> temp가 참조하는 객체의 next 연결을 변경한다.
        //
        // 다음 학습에서는 각각 temp를 새로 초기화한 뒤 비교한다.
    }

    public static void printList(ListNode head) {

        System.out.print("[");

        while (head != null) {
            System.out.print(head.val);

            if (head.next != null) {
                System.out.print(", ");
            }

            head = head.next;
        }

        System.out.println("]");
    }
}
