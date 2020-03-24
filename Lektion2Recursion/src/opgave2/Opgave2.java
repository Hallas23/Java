package opgave2;

public class Opgave2 {

	public static void main(String[] args) {
		System.out.println(isPalindrome("amma"));
	}

	public static boolean isPalindrome(String s) {

		if (s.length() <= 1) {
			return true;	
		} 
		else if (s.charAt(0) == s.charAt(s.length() - 1))
		{
			return isPalindrome(s.substring(1, s.length() - 1));
		} 
		else 
		{
			return false;
		}
	}
}