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
        	 */
        	for(int i = 0; i < nums.length; i++) {
        		map.put(nums[i], i);
        	}
        	
        	
        	for(int i = 0; i < nums.length; i++) {
        		
        		// 7 = 9 - 2 >>> 7이 map에 있으면 정답
        		int complement = target - nums[i];
        		
        		// map에 complement가 존재하는가? > 자기 자신은 아닌가?
        		/*
        			이 경우 3 + 3 = 6
					같은 숫자지만 다른 인덱스여야 하므로
        		 */
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