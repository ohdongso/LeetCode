
package _1_50;

public class _19_리스트끝에서n번째노드를제거합니다_0 {

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
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);

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
