package _1_50;

import java.util.HashMap;
import java.util.Map;

import _1_50._01_두개의합계_0.Solution;

public class Main {
	
	static class Solution {
		
		public int[] twoSum(int[] nums, int target) {
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			
			for(int i = 0; i < nums.length; i++) {
				map.put(nums[i], i);
			}
			
			for(int i = 0; i < nums.length; i++) {
				int complement = target - nums[i];
			
				if(map.containsKey(complement) && map.get(complement) != i) {
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
		
		System.out.println(result[0] + ", " + result[1]);
	}

}
