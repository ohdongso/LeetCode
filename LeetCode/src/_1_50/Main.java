package _1_50;

import java.util.HashSet;
import java.util.Set;

import _1_50.Main.Solution;

public class Main {
		
	static class Solution {
		
		public int lengthOfLongestSubstring(String s) {

            Set<Character> set = new HashSet<>();
            
            int left = 0;
            int maxLength = 0;
            
            for(int right = 0; right < s.length(); right++) {
            	
            	char current = s.charAt(right);
            	
            	while(set.contains(current)) {
            		set.remove(s.charAt(left));
            		left++;
            	}
            	
            	set.add(current);
            	
            	int length = right - left + 1;
            	
            	maxLength = Math.max(maxLength, length);
            }
            
            return maxLength;
        }
		
	}
	
	public static void main(String[] args) {
		Solution sol = new Solution();

        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        String s4 = "";
        String s5 = "dvdf";

        System.out.println("입력: " + s1 + " / 결과: " + sol.lengthOfLongestSubstring(s1)); // 3
        System.out.println("입력: " + s2 + " / 결과: " + sol.lengthOfLongestSubstring(s2)); // 1
        System.out.println("입력: " + s3 + " / 결과: " + sol.lengthOfLongestSubstring(s3)); // 3
        System.out.println("입력: " + s4 + " / 결과: " + sol.lengthOfLongestSubstring(s4)); // 0
        System.out.println("입력: " + s5 + " / 결과: " + sol.lengthOfLongestSubstring(s5)); // 3
	}
}