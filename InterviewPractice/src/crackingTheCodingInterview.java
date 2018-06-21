import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	/* Recursion and Dynamic Programming */
	public static int countWays(int n) {
		int[] ways = new int[n + 1];
		Arrays.fill(ways, -1);
		return countWays(n, ways);
	}
	
	public static int countWays(int n, int[] ways) {
		if(n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else if (ways[n] > -1) {
			return ways[n];
		} else {
			ways[n] =  countWays(n - 3) + countWays(n - 2) + countWays(n - 1);
			return ways[n];
		}
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
	
	public static void main(String args[]) {
		System.out.println(oneAway("", ""));
	}
}
