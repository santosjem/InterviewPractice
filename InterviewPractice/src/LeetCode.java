import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/*
 * All of the solutions added on here are answers to problems
 * found on https://leetcode.com/problemset/all/?listId=wpwgkgt
 */

public class LeetCode {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public boolean isAnagram(String s, String t) {
		HashMap<Character, Integer> letters = new HashMap<>();
		for(char c : s.toCharArray()) {
			if(letters.containsKey(c)) {
				letters.put(c, letters.get(c) + 1);
			} else {
				letters.put(c, 1);
			}
		}
		for(char c : t.toCharArray()) {
			if(letters.containsKey(c)) {
				if(letters.get(c) == 1) {
					letters.remove(c);
				} else {
					letters.put(c, letters.get(c) - 1);
				}
			} else {
				return false;
			}
		}
		return letters.isEmpty();
	}
	
	public boolean betterIsAnagram(String s, String t) {
		if (s.length() != t.length()) return false;
		
		char[] str1 = s.toCharArray();
		char[] str2 = s.toCharArray();
		Arrays.sort(str1);
		Arrays.sort(str2);
		return Arrays.equals(str1, str2);
	}
	
	public void deleteNode(ListNode node) {
		if(node.next == null) return;
		
        node.val = node.next.val;
        node.next = node.next.next;
    }
	
	public boolean containsDuplicate(int[] nums) {
		HashSet<Integer> hs = new HashSet<>();
        for(int i : nums) {
        	if(hs.contains(i)) {
        		return true;
        	} else {
        		hs.add(i);
        	}
        }
        return false;
    }
	
	public int[] intersect(int[] nums1, int[] nums2) {
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        
        for(int i : nums1) {
        	arr1.add(i);
        }
        
        for(int i : nums2) {
        	if(arr1.contains(i)) {
        		arr2.add(i);
        		arr1.remove((Object) i);
        	}
        }
        int[] ret = new int[arr2.size()];
        for(int i = 0; i < arr2.size(); i++) {
        	ret[i] = arr2.get(i);
        }
        return ret; 
    }
	
	
	public static void main(String[] args) {
		System.out.println("This is for testing");
	}
}
