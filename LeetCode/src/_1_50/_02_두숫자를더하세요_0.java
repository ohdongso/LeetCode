package _1_50;

public class _02_두숫자를더하세요_0 {
	
	/*
	 	두 개의 비어 있지 않은 연결 리스트가 존재
	 	순서가 역순으로 저장
	 	이 경우 역순에서 순방향으로 전환하고 두개의 숫자를 더하는 문제
	 	
	 	==> 일단 이코드 분석하고, 이코드에서 dummyHead사용 이유 파악해보자
	 */
	
    // ListNode 정의
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Solution {
    	
    	/*
    		전체 실행 흐름
    		l1 = 2 → 4 → 3
			l2 = 5 → 6 → 4
    		
    		1번째 반복
    		2 + 5 = 7
    		7
    		
    		2번째 반복
    		4 + 6 = 10
    		0 저장
			carry = 1
    		
    		3번째 반복
    		3 + 4 + 1 = 8
    		
    		마지막 반환
    		return dummyHead.next;
    		dummyHead는 가짜 노드이므로 첫 번째 노드를 제외하고 반환합니다.
    		
    		이 코드는 LinkedList를 한 칸씩 이동하면서 자리올림(carry)을 계산해 결과 리스트를 만드는 알고리즘입니다.
    		
    	 */
    	
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        	
        	// l1 → 첫 번째 숫자 리스트
            // l2 → 두 번째 숫자 리스트
        	
        	// 결과 리스트를 만들 때 처음 노드를 처리하기 쉽게 하기 위해 가짜 시작 노드를 하나 둡니다.
            ListNode dummyHead = new ListNode(0);
            
            // curr은 현재 결과 리스트의 마지막 노드를 가리키는 포인터입니다.
            ListNode curr = dummyHead;
            
            // carry는 자리 올림 값입니다.
            /*
             	예를 들어 > 7 + 5 = 12
            	2 → 저장
				1 → carry
             */
            int carry = 0;
            
            /*
            	l1이 남아있다
				l2가 남아있다
				carry가 남아있다
				
				예를 들어 마지막 계산에서 9 + 9 = 18 이면 carry가 남기 때문에 한 번 더 반복해야 합니다.
             */
            while (l1 != null || l2 != null || carry != 0) {
            	
            	/*
            		l1이 있으면 값 사용
					없으면 0 사용
					
					예를 들어
					l1 = null
					l2 = 5
					
					x = 0
					y = 5
            	 */
                int x = (l1 != null) ? l1.val : 0;
                int y = (l2 != null) ? l2.val : 0;
                
                // int sum = x + y + carry;
                /*
                	2 + 5 + 0 = 7
                 */
                int sum = x + y + carry;
                
                // carry = sum / 10;
                /*
                 	자리올림 계산
                	sum = 7 → carry = 0
					sum = 10 → carry = 1
					sum = 18 → carry = 1
                 */
                carry = sum / 10;
                
                // 현재 자리 저장, curr.next = new ListNode(sum % 10);
                curr.next = new ListNode(sum % 10);
                
                // 포인터 이동
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
    	
        // [2,4,3]
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        // [5,6,4]
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

        // 출력: 7 0 8
    }
}