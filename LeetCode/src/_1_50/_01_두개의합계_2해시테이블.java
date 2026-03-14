package _1_50;

import java.util.HashMap;
import java.util.Map;

public class _01_두개의합계_2해시테이블 {

    static class Solution {

        public int[] twoSum(int[] nums, int target) {
        	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        	
        	/*
				2  → 0
				7  → 1
				11 → 2
				15 → 3
				(key에 값을저장, value에 index저장)
        	 */
        	for(int i = 0; i < nums.length; i++) {
        		map.put(nums[i], i);
        	}
        	
        	for(int i = 0; i < nums.length; i++) {
        		
        		// 7 = 9 - 2 >>> 7이 map에 있으면 정답
        		int complement = target - nums[i];
        		
        		// 배열에서 차례로 조회 되는 숫자를 target에서 뺀다.
        		// 뺀숫자가 맵의 key값과 일치하는게 있다면 다음 조건으로 이동한다.
        		// 그리고 뺀숫자의 값에 해당하는 index를 반환 했을 때, 현재 i값과 일치하지 않으면 두수의 index를 반환한다.
        		// * target이 8인경우, 배열에 4밖에 없는 경우 동일한 index가 반환될수 있는 부분의 유효성 검사
        		if (map.containsKey(complement) && map.get(complement) != i) {
        			// 현재 인덱스 + complement의 인덱스 반환
                    return new int[] { i, map.get(complement) };
                }
        	}
        	
            return new int[] {};
        }
        
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = sol.twoSum(nums, target);

        System.out.println(result[0] + ", " + result[1]); // 출력: 0, 1
    }
}