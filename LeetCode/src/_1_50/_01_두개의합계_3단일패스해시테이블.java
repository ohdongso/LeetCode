package _1_50;

import java.util.HashMap;
import java.util.Map;

public class _01_두개의합계_3단일패스해시테이블 {
	/*
		nums의 value와 key를 저장하는건 같다. 하지만 처음부터 다 저장하는게 아니라
		target - nums[i]를 한 뒤에 나머지숫자(complement)가 map에 있는지 비교하고
		없으면 map에 value와 index를 저장한다.
		만약 있다면 complement의 index값과 target- nums[i]의 i값 두개를 반환하여
		두수를 더해서 target값이 나오는 숫자의 index를 최종 반환한다.
		
		결론:있는지 비교하고 넣고, 있으면 바로 반환해서 끝낸다.
	 */
    static class Solution {

        public int[] twoSum(int[] nums, int target) {
        	Map<Integer, Integer> map = new HashMap<>();
        	
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

        System.out.println(result[0] + ", " + result[1]); // 출력: 0, 1
    }
}