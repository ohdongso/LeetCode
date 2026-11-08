
package _1_50;

public class _19_리스트끝에서n번째노드를제거합니다_0 {

    // 단일 연결 리스트의 노드를 표현하는 정적 중첩 클래스
    static class ListNode {

        // 현재 노드가 저장하는 정수 값
        int val;

        // 다음 ListNode 객체를 가리키는 참조 변수
        // 다음 노드가 없으면 null
        ListNode next;

        // 기본 생성자
        ListNode() {
        }

        // 정수 값을 전달받아 노드 생성
        ListNode(int val) {
            this.val = val;
        }

        // 정수 값과 다음 노드의 참조를 전달받아 노드 생성
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Solution {

        public ListNode removeNthFromEnd(ListNode head, int n) {

            // TODO: 다음 학습 - dummy, fast, slow 포인터 분석
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode fast = dummy;
            ListNode slow = dummy;

            for (int i = 0; i <= n; i++) {
                fast = fast.next;
            }

            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }

            slow.next = slow.next.next;

            return dummy.next;
        }
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        // 예제 1
        // head1은 첫 번째 노드를 가리키는 참조 변수
        ListNode head1 = new ListNode(1);

        // next를 통해 다음 노드를 연결
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);

        // TODO: 다음 학습 - next를 연속 사용하여 노드에 접근하는 과정
        ListNode result1 = sol.removeNthFromEnd(head1, 2);
        printList(result1);

        // 예상 결과 : [1, 2, 3, 5]

        // 예제 2
        ListNode head2 = new ListNode(1);
        ListNode result2 = sol.removeNthFromEnd(head2, 1);
        printList(result2);

        // 예상 결과 : []

        // 예제 3
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);

        ListNode result3 = sol.removeNthFromEnd(head3, 1);
        printList(result3);

        // 예상 결과 : [1]
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
