package _1_50;

public class Main {
	
	// ListNode 정의
    static class ListNode {
        int val; // 노드의 실제 값
        ListNode next; // 노드와 연결 돼 있는 노드의 참조주소를 저장하는 변수

        // 노드의 실제 값을 받는 생성자
        ListNode(int val) {
            this.val = val;
        }
        
        // 노드의 실제 값과, 다른노드의 참조주소를 받는 생성자
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
	
	static class Solution {
		
		 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			 
			 ListNode dummyHead = new ListNode(0);
			 
			 ListNode curr = dummyHead;
			 
			 int carry = 0;
			 
			 while(l1 != null || l2 != null || carry != 0) {
				 int x = (l1 != null) ? l1.val : 0;
				 int y = (l2 != null) ? l2.val : 0;
				 
				 int sum = x + y + carry;
				 
				 carry = sum / 10;
				 
				 curr.next = new ListNode(sum % 10);
				 
				 curr = curr.next;
				 
				 if(l1 != null) l1 = l1.next;
				 if(l2 != null) l2 = l2.next;
				 
			 }
			 
			 return dummyHead.next;
		 }
		
	}
	
	public static void main(String[] args) {
		
		Solution sol = new Solution();
		
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
		
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);
		
		ListNode result = sol.addTwoNumbers(l1, l2);
		
		while(result != null) {
			System.out.print(result.val + " ");
			result = result.next;
		}
	}
}