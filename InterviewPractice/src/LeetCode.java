import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.activation.MailcapCommandMap;

/*
 * All of the solutions added on here are answers to problems
 * found on https://leetcode.com/problemset/all/?listId=wpwgkgt
 */

public class LeetCode {
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public static class Pair<T1, T2> {
		T1 val1;
		T2 val2;
		Pair(T1 v1, T2 v2) {
			val1 = v1;
			val2 = v2;
		}
	}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public List<String> letterCasePermutation(String S) {
        List<String> ret = new ArrayList<>();
        helper(S.toCharArray(), 0, "", ret);
        return ret;
        
    }
    
	
    public void helper(char[] string, int index, String curr, List<String> ret) {
        if(index == string.length) {
            ret.add(curr);
        }  else {
            if(Character.isLetter(string[index])) {
                helper(string, index + 1, curr + Character.toLowerCase(string[index]), ret);
                helper(string, index + 1, curr + Character.toUpperCase(string[index]), ret);                   
            } else {
                curr = curr + string[index];
                helper(string, index + 1, curr, ret);
            }
        }
    }
    
    public boolean exist(char[][] board, String word) {
        if(word == null) {
            return true;
        }
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == word.charAt(0)) {
                	if(word(board, word, i, j)) {
                		return true;
                	}
                }
            }
        }
        return false;
    }
    
    
    
    public boolean word(char[][] board, String word, int x, int y) {
    	for(int i = 1; i < word.length(); i++) {
            System.out.println("Hello");
    		if(above(board, word, x, y, i)) {
                System.out.println("Above " + word.charAt(i));
    			x -= 1;
    		} else if (below(board, word, x, y, i)) {
                System.out.println("Below " + word.charAt(i));
    			x += 1;
    		} else if (left(board, word, x, y, i)) {
                System.out.println("Left " + word.charAt(i));
    			y -= 1;
    		} else if (right(board, word, x, y, i)) {
                System.out.println("Right " + word.charAt(i));
    			y += 1;
    		} else {
    			return false;
    		}
    	}
    	return true;
    }
    
    public boolean above(char[][] board, String word, int x, int y, int curr) {
    	if(x < 1) return false;
        return board[x-1][y] == word.charAt(curr);
    }
    
    public boolean below(char[][] board, String word, int x, int y, int curr) {
    	if (x > board.length - 2) return false;
        return board[x+1][y] == word.charAt(curr);
    }
    
    public boolean left(char[][] board, String word, int x, int y, int curr) {
    	if (y < 1) return false;
        return board[x][y-1] == word.charAt(curr);
    }
    
    public boolean right(char[][] board, String word, int x, int y, int curr) {
    	if (y >= board[1].length) return false;
        return board[x][y+1] == word.charAt(curr);
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
	
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> ret = new ArrayList<List<String>>();
		
		if(strs.length == 0) {
			return ret;
		}
		
		HashMap<String, List<String>> map = new HashMap<>();
		for(String s : strs) {
			char[] str = s.toCharArray();
			Arrays.sort(str);
			String key = String.valueOf(str);
			if(!map.containsKey(key)) {
				map.put(key, new ArrayList<String>());
			}
			map.get(key).add(s);
		}
		
		return new ArrayList<List<String>>(map.values());
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
	
	public boolean isPalindrome(ListNode head) {
		if(head == null) return true;
		
		ListNode rev = reverse(head);
		while(head.next != null) {
			if(head.val != rev.val) {
				return false;
			}
			head = head.next;
			rev = rev.next;
		}
		return true;
	}
	
	public static ListNode reverse(ListNode head) {
		ListNode prev = null;
	    while (head != null) {
	        ListNode next = head.next;
	        head.next = prev;
	        prev = head;
	        head = next;
	    }
	    return prev;
	}
	
	public static class MinStack {
	    /** initialize your data structure here. */
	    int min;
	    Stack<Integer> stack;
	    ArrayList<Integer> minStack = new ArrayList<>();
		
	    public MinStack() {
	        stack = new Stack<>();
	    }
	    
	    public void push(int x) {
	    	if(stack.isEmpty()) {
	    		stack.push(x);
	    		minStack.add(x);
	    		min = x;
	    	} else {
	    		stack.push(x);
	    		if(x <= min) {
		        	min = x;
		        	minStack.add(x);
		        }
	    	}
	    	
	    }
	    
	    public void pop() {
	        if(stack.isEmpty()) return;
	        
	        int x = stack.pop();
	        if(minStack.contains((Object) x)) {
	        	minStack.remove((Object) x);
	        }
	        
	        if(minStack.isEmpty()) {
	        	min = 0;
	        } else {
	        	min = minStack.get(minStack.size() - 1);
	        }
	    }
	    
	    public int top() {
	        return stack.peek();
	    }
	    
	    public int getMin() {
	        return min;
	    }
	    
	}
	
	public boolean isSymmetric(TreeNode root) {
		return root == null || isSymmetric(root.left, root.right);
	}
	
	public boolean isSymmetric(TreeNode left, TreeNode right) {
		if(left == null || right == null) {
			return left == right;
		}
		
		if(left.val != right.val) {
			return false;
		}
		
		return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left); 	
	}
	
	public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> ret = new ArrayList<>();
        List<Pair<Integer, Integer>> temp = new ArrayList<>();
        
        
		
		
        return new ArrayList<>();
    }
	
	public static boolean detectCapitalUse(String word) {
		String start = word.substring(0, 0);
		String end = word.substring(1, word.length());
        if(word.toUpperCase() == word) {
        	return true;
        } else if (word.toLowerCase() == word) {
        	return true;
        } else if (start == start.toUpperCase() && end == end.toLowerCase()){
        	return true;
        }
		
		return false;
    }
	
	public static int kthSmallest(TreeNode root, int k) {
		int treeSize = countTree(root);
		
		if(k < treeSize) {
			kthSmallest(root.left, k);
		} else {
			kthSmallest(root.right, k - treeSize - 1);
		}
		
		return root.val;
	}
	
	public static int countTree(TreeNode root) {
		if (root == null) {
			return 0;
		} else {
			return (1 + countTree(root.left) + countTree(root.right)); 
		}
		
	}
	
	public static int kthSmallest(int[][] matrix, int k) {
        int len = matrix.length;
        int size = len * len;
		
		return matrix[(int) Math.floor(size / k)][(k % len) - len];
    }
	
	public static int findDuplicate(int[] nums) {
		HashSet<Integer> hash = new HashSet<>();
		
		for(int i = 0; i < nums.length; i++) {
			if(hash.contains(nums[i])) {
				return nums[i];
			}
			
			hash.add(nums[i]);
		}
		
		return 0;
	}
	
	public static String reverseString(String s) {
		String ret = "";
        
        for(int i = 0; i < s.length(); i++) {
            ret = s.charAt(0) + ret;
            s = s.substring(1);
        }
        
        return ret;
	}
	
	public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode ret = new TreeNode(nums[0]);
        TreeNode fin = ret;
		int len = nums.length;
		int half = (int) Math.ceil(len/2);
        
        for(int i = 1; i < len; i++) {
        	TreeNode cur = new TreeNode(nums[i]);
        	if(i < half) {
        		cur.left = ret;
        		ret = cur;
        	} else {
        		ret.right = cur;
        		ret = cur;
        	}
        }
        
		return fin;
    }
	
	public int numJewelsInStones(String J, String S) {
        HashSet<Character> hash = new HashSet<>();
        for(int c = 0; c < J.length(); c++) {
        	hash.add(J.charAt(c));
        }
        
        int count = 0;
        for(int i = 0; i < S.length(); i++) {
        	if(hash.contains(S.charAt(i))) {
        		count++;
        	}
        }
        return count;
    }
	
	public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        subsetsHelper(list, new ArrayList<Integer>(), nums, 0);
        return list;
    }
    
	public static void subsetsHelper(List<List<Integer>> list, List<Integer> curr, int[] nums, int n) {
        list.add(new ArrayList<>(curr));
        
        for(int i = n; i < nums.length; i++) {
	        curr.add(nums[i]);
	       	subsetsHelper(list, curr, nums, i);
	       	curr.remove(curr.size() - 1);
        }
    } 
    
    public static void print(List<Integer> num) {
    	
        for(int i = 0; i < num.size(); i++) {
            System.out.print(num.get(i) + " ");
        }
        System.out.println();
    }
	
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        permuteHelper(nums, res, new ArrayList<Integer>());
        return res;
    }
    
    public void permuteHelper(int[]nums, List<List<Integer>> res, List<Integer> temp) {
        if(temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
        } else {
            for(int i = 0; i < nums.length; i++) {
                if(temp.contains(nums[i])) continue;
                temp.add(nums[i]);
                permuteHelper(nums, res, temp);
                temp.remove(temp.size() - 1);
                
            }
        }
    }
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Boolean[] used = new Boolean[nums.length];
        
        for(int i = 0; i < used.length; i++) {
        	used[i] = false;
        }
        
        permuteHelper2(nums, res, new ArrayList<Integer>(), used);
        return res;
    }
    
    public void permuteHelper2(int[]nums, List<List<Integer>> res, List<Integer> temp, Boolean[] used) {
        if(temp.size() == nums.length && res.contains(temp)) {
            res.add(new ArrayList<>(temp));
        } else {
            for(int i = 0; i < nums.length; i++) {
                if(temp.contains(nums[i]) && used[i] != true) continue;
                temp.add(nums[i]);
                used[i] = true;
                permuteHelper2(nums, res, temp, used);
                temp.remove(temp.size() - 1);
                used[i] = false;
            }
        }
    }
    
    public static boolean checkPermutation(String s, String t) {
    	char[] a = s.toCharArray();
    	char[] b = t.toCharArray();
    	Arrays.sort(a);
    	Arrays.sort(b);
    	return a.equals(b);
    }
	
	public static void main(String[] args) {
		int[] test = {1, 1, 1, 2, 2, 3};
		int len = 2;
		List<Integer> res = topKFrequent(test, len);
		
		for(int i = 0; i < len; i++) {
			System.out.println(res.get(i));
		}
	}
}
