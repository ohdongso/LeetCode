package _1_50;

public class _02_두숫자를더하세요_0 {
	
	/*
	 	문제정리
	 	두 개의 비어 있지 않은 연결 리스트가 존재 순서가 역순으로 저장
	 	이 경우 역순에서 순방향으로 전환하고 두개의 숫자를 더하는 문제
	 	
	 	Dummy Node 필요성
	 	더미 노드가 없으면 노드를 삽입할 때마다 현재 리스트의 head가 null인지 확인해야 한다.
		이렇게 null 여부를 확인하는 이유는 지금 삽입하려는 노드가 첫 번째 노드인지 판단하기 위해서이다.
		첫 번째 노드라면 head에 직접 할당해야 하고,
		첫 번째 노드가 아니라면 이전 노드의 next에 연결하는 다른 로직이 필요하다.
	 	
	 	Pointer 필요성
	 	링크드 리스트에 노드를 삽입할 때 현재 마지막 노드의 위치를 포인터가 가리키고 있도록 한다.
		이렇게 하면 새로운 노드를 쉽게 연결할 수 있고, 
		노드를 추가할 때마다 포인터를 다음 노드로 이동시켜 계속 리스트의 끝을 관리할 수 있다.
	 	
	 	결론:Dummy Node는 첫 노드 예외 처리를 없애기 위해 사용하고, 
	 	Pointer는 리스트의 마지막 노드를 추적하면서 새로운 노드를 연결하기 위해 사용한다.
	 */
	 
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
        	
        	// 결과 리스트를 만들 때 처음 노드를 처리하기 쉽게 하기 위해 '가짜 시작 노드'를 하나 둡니다.
            ListNode dummyHead = new ListNode(0);
            
            // curr은 현재 결과 리스트의 마지막 노드를 가리키는 포인터입니다.
            ListNode curr = dummyHead;
            
            // carry는 자리 올림 값입니다.
            int carry = 0;
            
            while (l1 != null || l2 != null || carry != 0) {
                int x = (l1 != null) ? l1.val : 0; // 2,4,3
                int y = (l2 != null) ? l2.val : 0; // 5,6,4
                
                /*
                 	2+5+0=7
                 	4+6+0=10 > 0, 1
                 	3+4+1=8
                 */
                int sum = x + y + carry;
                
                carry = sum / 10; // 자리올림 계산
                
                // 0, 7, 0, 8
                curr.next = new ListNode(sum % 10);
                
                // 포인터 이동(다음값을 저장해야 하기때문에 이동필요)
                curr = curr.next;
                
                // 입력 리스트 이동
                if (l1 != null) l1 = l1.next;
                if (l2 != null) l2 = l2.next;
            }

            return dummyHead.next;
        }
    }

    public static void main(String[] args) {

    	Solution sol = new Solution();
    	
        // (2,4,3)을 연결리스트로 저장
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        // (5,6,4)를 연결리스트로 저장
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        
        // 저장된 LinkNode 전달
        ListNode result = sol.addTwoNumbers(l1, l2);
        
        // 결과 출력
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}