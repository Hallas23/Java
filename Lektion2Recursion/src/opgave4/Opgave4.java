package opgave4;

public class Opgave4 {

	public static void main(String[] args) {
		printAnagrams("", "cat");
	}

	public static void printAnagrams(String prefix, String word) {
		if (word.length() == 0) {
			System.out.println(prefix);
		}
		else {
			for (int i = 0; i < word.length(); i++ ) {
				printAnagrams(prefix + word.charAt(i), word.substring(0, i) + word.substring(i+1, word.length()));
			}
		}
	}
}
