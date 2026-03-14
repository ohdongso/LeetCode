package _1_50;

import java.util.HashMap; 
import java.util.Map;

public class Main {
	
	static class Solution {
		
		public int[] twoSum(int[] nums, int target) {
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			
        	for (int i = 0; i < nums.length; i++) {
        		int complement = target - nums[i];
        		if (map.containsKey(complement)) { // target에서 뺀 값이 배열에 있다면
                    return new int[] { map.get(complement), i }; // 뺀값에 해당하는 인덱스, i의값(index) 2개를 반환
                }
        		map.put(nums[i], i);
        	}
        	
        	return new int[] {};
			
		}
		
	}
	
	public static void main(String[] args) {
		
		Solution sol = new Solution();
		
		int[] nums = {2, 7, 11, 15};
		int target = 9;
		
		int[] result = sol.twoSum(nums, target);
		
		System.out.println(result[0] + ", " + result[1]);
	}

}
