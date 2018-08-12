import java.util.Arrays;
import java.util.HashSet;

public class ctci2 {
	
	/* Arrays and Strings */
	/* No Additional Data Structure */
	public static boolean isUnique(String s) {
		char[] arr = s.toCharArray();
		Arrays.sort(arr);
		
		for(int i = 1; i < s.length(); i++) {
			if(arr[i] == arr[i - 1]) {
				return false;
			}
		}
		return true;
	}
	
	/* With additional data structure */
	public static boolean isUnique2(String s) {
		HashSet<Character> hs = new HashSet<>();
		
		for(int i = 0; i < s.length(); i++) {
			if(hs.contains(s.charAt(i))) {
				return false;
			}
			hs.add(s.charAt(i));
		}
		return true;
	}
	
	public static boolean checkPermutation(String s, String t) {
		char[] sArr = s.toCharArray();
		char[] tArr = t.toCharArray();
		Arrays.sort(sArr);
		Arrays.sort(tArr);
		
		for(int i = 0; i < s.length(); i++) {
			if(sArr[i] != tArr[i]) {
				return false;
			}
		}
		
		
		return true;
	}
	
	public static String URLify(String str, int n) {
		
		return new String();
	}
	
	public static void main(String[] args) {
		System.out.println("Test isUnique: " + isUnique2("asjkfahw"));
		System.out.println("Test checkPermutation: " + checkPermutation("Jemuel", "leumeJ"));
		System.out.println("Test URLify: " + URLify("Mr John Smith       ", 13));
	}
}
