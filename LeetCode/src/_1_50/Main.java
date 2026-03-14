package _1_50;

import _1_50._01_두개의합계_0.Solution;

public class Main {
	
	static class Solution {
		
		public int[] twoSum(int[] nums, int target) {
			
			int leng = nums.length;
			
			for(int i = 0; i < leng - 1; i++) {
				for(int j = i + 1; i < leng; j++) {
					if(target == nums[i] + nums[j]) {
						return new int[] {i, j};
					}
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
