import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;




public class crackingTheCodingInterview {
	
	public static class LinkedList<T> {
		T val;
		LinkedList<T> next;
		
		LinkedList() {
			this.val = null;
			this.next = null;
		}
		
	}
	
	public static class TreeNode {
		Integer val;
		TreeNode left;
		TreeNode right;
		TreeNode(Integer x) { val = x; }
	}
	
	public static String reverse(String str) {
		StringBuilder ret = new StringBuilder();
		for(int i = str.length()-1; i >= 0; i--) {
			ret.append(str.charAt(i));
		}
		return new String(ret);
	}
	
	/* CHAPTER 1 */
	
	/* Check if every character is unique without a 
	 * different data structure. */
	public static boolean isUnique(String str) {
		for(int i = 0; i < str.length(); i++) {
			for(int j = i + 1; j < str.length(); j++) {
				if(str.charAt(i) == str.charAt(j)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean isUnique2(String str) {
		HashSet<Character> hash = new HashSet<>();
		for (Character c : str.toCharArray()) {
			if(hash.contains(c)) {
				return false;
			} else {
				hash.add(c);
			}
		}
		return true;
	}
	
	/* More efficient isUnique O(n) */
	public static boolean betterIsUnique(String str) {
		HashSet<Character> hash = new HashSet<>();
		
		for(int i = 0; i < str.length(); i++) {
			if(hash.contains(str.charAt(i))) {
				return false;
			}
			hash.add(str.charAt(i));
		}
		return true;
	}
	
	/* Check if two strings are permutations of each other. */
	public static boolean checkPermutations(String first, String second) {
		if(first.length() != second.length()) {
			return false;
		}
		
		char[] firstArray = first.toCharArray();
		char[] secondArray = second.toCharArray();
		Arrays.sort(firstArray);
		Arrays.sort(secondArray);
		
		for(int i = 0; i < first.length(); i++) {
			if(firstArray[i] != secondArray[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean checkPermutations2(String first, String second) {
		if(first.length() != second.length()) {
			return false;
		}
		
		char[] firstArray = first.toCharArray();
		char[] secondArray = second.toCharArray();
		Arrays.sort(firstArray);
		Arrays.sort(secondArray);
		
		return firstArray.toString().equals(secondArray.toString());
	}
	
	
	/* URLify */ 
	public static String urlify(String str) {
		char[] string = str.toCharArray();
		int curr = str.length() - 1;
		int move = str.length() - 1;
		while(!Character.isAlphabetic(string[move])) {
			move--;
		}
		for(int i = move; i >= 0; i--) {
			if(Character.isAlphabetic(string[i])) {
				string[curr--] = string[i];
			} else {
				string[curr--] = '0';
				string[curr--] = '2';
				string[curr--] = '%';
			}
		}
		
		return new String(string);
	}
	
	/* O(n) time*/
	public static boolean palindromePermutation(String str) {
		ArrayList<Character> arr = new ArrayList<>();
		for(Character c : str.toCharArray()) {
			if(Character.isAlphabetic(c)){
				if(arr.contains(c)) {
					arr.remove(c);
				} else {
					arr.add(c);
				}
			}
		}
		if(arr.size() <= 1) {
			return true;
		}
		return false;
	}
	
	/* Check if two strings are one edit away from each other.
	 * O(n + m) time */
	public static boolean oneAway(String first, String second) {
		if(Math.abs(first.length() - second.length()) > 1) {
			return false;
		}
		
		if(first.length() == second.length()) {
			return oneEditAway(first, second);
		} else {
			return oneAddRemoveAway(first, second);
		}
	}
	
	public static boolean oneEditAway(String first, String second) {
		int count = 0;
		
		if(first == second) {
			return true;
		} else {
			for(int i = 0; i < first.length(); i++) {
				if(first.charAt(i) != second.charAt(i)) {
					count++;
					if(count > 1) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static boolean oneAddRemoveAway(String first, String second) {
		
		ArrayList<Character> chars = new ArrayList<>();
		
		for(int i = 0; i < first.length(); i++) {
			chars.add(first.charAt(i));
		}
		
		for(int j = 0; j < second.length(); j++) {
			Character curr = second.charAt(j);
			if(chars.contains(curr)) {
				chars.remove(curr);
			}
		}
		if(chars.size() > 1) {
			return false;
		} 
		return true;
	}
	
	/* String compression */
	public static String stringCompression(String s) {
    	StringBuilder ret = new StringBuilder();
    	Character curr = s.charAt(0);
    	int count = 1;
    	
    	for(int i = 1; i < s.length(); i++) {
    		if(s.charAt(i) == curr) {
    			count++;
    		} else {
    			ret.append(curr);
    			ret.append(count);
    			curr = s.charAt(i);
    			count = 1;
    		}
    	}
    	ret.append(curr);
		ret.append(count);
		
    	return new String(ret);
    }
	
	
	/* Chapter 2 Linked List */
	public static void removeDups(LinkedList<Integer> list) {
		LinkedList<Integer> head = list;
		HashSet<Integer> set = new HashSet<>();
		set.add(list.val);
		
		while(head.next != null) {
			if(set.contains(head.next.val)) {
				head.next = head.next.next;
			} else {
				set.add(head.next.val);
				head = head.next;
			}
		}
		
	}
	
	public static LinkedList<Integer> returnKthToLast(LinkedList<Integer> node, int k) {
    	LinkedList<Integer> fast = new LinkedList<>();
    	LinkedList<Integer> ans = new LinkedList<>();
    	
    	while(k >= 0) {
    		fast = fast.next;
    		k--;
    	}
    	
    	while(fast.next != null) {
    		fast = fast.next;
    		ans = ans.next;
    	}
    	
    	return ans;	
    }
	
	public static boolean deleteMiddleNode(LinkedList<Integer> node) {
		if(node == null || node.next == null) {
			return false;
		}
		
		node.val = node.next.val;
		node.next = node.next.next;
		
		return true;
	}
	
	/* Trees and Graphs */
	public static ArrayList<LinkedList<TreeNode>> createLinkedList(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> ret = new ArrayList<>();
		createLinkedList(root, ret, 0);
		return ret;
	}
	
	public static void createLinkedList(TreeNode cur, ArrayList<LinkedList<TreeNode>> ret, int level) {
		if (cur == null) {
			return;
		}
		
		if(ret.get(level) == null) {
			ret.add(new LinkedList<TreeNode>());
			ret.get(level).val = cur;
		} else {
			ret.get(level).val = cur;
		}
		createLinkedList(cur.left, ret, level+1);
		createLinkedList(cur.right, ret, level+1);
	}
	
	public static boolean validBST(TreeNode root) {
    	return validBST(root, null, null);
    }
    
    public static boolean validBST(TreeNode cur, Integer min, Integer max) {
    	if(cur == null) {
    		return true;
    	}
    	
    	if((min != null && cur.val < min) || (max != null && cur.val > max)) {
    		return false;
    	}
    	
    	if(!validBST(cur.left, min, cur.val) || !validBST(cur.right, cur.val, max)) {
    		return false;
    	}
    	
    	return true;
    }
	
	/* Recursion and Dynamic Programming */
    /* Brute Force */
    public static int countWays(int n) {
		if(n < 0) return 0;
		if(n == 0) return 1;
		
		return countWays2(n-1) + countWays2(n-2) + countWays2(n-3);	
	}
    
    /* Dynamic Programming */
	public static int countWays2(int n) {
		return 10;
	}
	
	public static ArrayList<Point> getPath(boolean[][] maze) {
		ArrayList<Point> path = new ArrayList<>();
		if(getPath(maze, maze.length, maze[0].length, path)) {
			return path;
		};
		return null;
	}
	
	public static boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path) {
		if(row == 0 && col == 0) {
			return true;
		} else if (row < 0 || col < 0) {
			return false;
		} else {
			path.add(new Point(row, col));
			return getPath(maze, row - 1, col, path) || getPath(maze, row, col - 1, path);
		}
	}
	
	public static int magicIndex(int[]  arr) {
		if(arr.length == 0) {
			return -1;
		}
		return magicIndex(arr, 0, arr.length - 1);
	}
	
	public static int magicIndex(int[] arr, int low, int high) {
		if(high < low) {
			return -1;
		}
		int mid = low + high / 2;
		if(mid > arr[mid]) {
			return magicIndex(arr, low, mid - 1);
		} else if (mid <= arr[mid]) {
			return magicIndex(arr, mid, high);
		} else {
			return mid;
		}
	}
	
	public static ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> set) {
		if(set.size() == 0) {
			return null;
		}
		ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
		subsets(set, new ArrayList<Integer>(), ret, 0);
		return ret;
	}
	
	public static void subsets(ArrayList<Integer> set, ArrayList<Integer> temp, ArrayList<ArrayList<Integer>> ret, int index) {
		if(index == set.size()) {
			ret.add(new ArrayList<>(temp));
		} else {
			temp.add(set.get(index));
			subsets(set, temp, ret, index + 1);
			temp.remove(temp.size() - 1);
			subsets(set, temp, ret, index + 1);
		}
	} 
	
	public static ArrayList<String> getPerms(String str) {
		ArrayList<String> ret = new ArrayList<>();
		char[] strArr = str.toCharArray();
		Arrays.sort(strArr);
		getPerms(strArr, ret, new boolean[str.length()], new StringBuilder());
		return ret;
	}

	public static void getPerms(char[] str, ArrayList<String> ret, boolean[] used, StringBuilder temp) {
		if(temp.length() == str.length) {
			ret.add(new String(temp));
		} else {
			for(int i = 0; i < str.length; i++) {
				if(used[i] || i > 0 && str[i] == str[i - 1] && !used[i - 1]) continue;
				temp.append(str[i]);
				used[i] = true;
				getPerms(str, ret, used, temp);
				temp.delete(temp.length() - 1, temp.length());
				used[i] = false;
				
			}
		}
	}
	
	public static int coins(int n) {
		int[] vals = {25, 10, 5, 1};
		return coins(n, vals, 0);
		
	}
	
	public static int coins(int n, int[] vals, int ind) {
		if(n == 0) {
			return 1;
		}
		if(n < 0) {
			return 0;
		}
		if(ind == 0) {
			return coins(n - vals[ind], vals, ind) + coins(n - vals[ind + 1], vals, ind + 1)
				+ coins(n - vals[ind + 2], vals, ind + 2) + coins(n - vals[ind + 3], vals, ind + 3);
		} else if (ind == 1) {
			return coins(n - vals[ind], vals, ind) + coins(n - vals[ind + 1], vals, ind + 1)
			+ coins(n - vals[ind + 2], vals, ind + 2);
		} else if (ind == 2) {
			return coins(n - vals[ind], vals, ind) + coins(n - vals[ind + 1], vals, ind + 1);
		} else {
			return coins(n - vals[ind], vals, ind);
		}
	}
	
	
	public static void main(String args[]) {
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(1);
		arr.add(2);
		arr.add(3);
		ArrayList<ArrayList<Integer>> array = subsets(arr);
		//System.out.println(checkPermutations2("jemuel", "leumej"));
		
		
		TreeNode tree = new TreeNode(50);
		tree.left = new TreeNode(25);
		tree.left.left = new TreeNode(10);
		tree.left.left.left = new TreeNode(5);
		tree.left.left.right = new TreeNode(15);
		tree.left.left.right.left = new TreeNode(12);
		tree.right = new TreeNode(75);
		tree.right.right = new TreeNode(100);
		tree.right.left = new TreeNode(90);
		
		TreeNode tree2 = new TreeNode(50);
		tree2.left = new TreeNode(25);
		tree.right = new TreeNode(75);
		
		System.out.println(validBST(tree2));
		
		System.out.println(countWays(10) + " " + countWays2(10));
		System.out.println(coins(25));
		
	}
}
