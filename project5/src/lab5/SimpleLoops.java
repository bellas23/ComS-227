package lab5;

/**
 * An example with some buggy loops.
 */
public class SimpleLoops {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int count = countP("Mississippi");
		System.out.println(count);
		System.out.println("Expected 2");

		int result = findLastP("Mississippi");
		System.out.println(result);
		System.out.println("Expected 9");

		result = findLastP("Hello");
		System.out.println(result);
		System.out.println("Expected -1");

		result = findFirstP("stop");
		System.out.println(result);
		System.out.println("Expected 3");

		result = findFirstP("xxxyyyzzz");
		System.out.println(result);
		System.out.println("Expected -1");
	}
	
	/**
	 * Returns the number of P's in a string.
	 * 
	 * @param s the string to examine
	 * @return number of P's in s
	 */
	private static int countP(String s) {
		int count = 0;
		int i = 0;
		while (i < s.length()) {

			if (isLetterP(s.charAt(i))) {
				count += 1;
			}
			i += 1;
		}
		return count;
	}

	/**
	 * Returns the index of the last P in a string, or -1 if the string contains no
	 * P's.
	 * 
	 * @param s the string to examine
	 * @return index of the last P, or -1
	 */
	private static int findLastP(String s) {
		int lastIndex = -1;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == 'p' || c == 'P') {
				lastIndex = i;
			}
		}
		return lastIndex;
	}

	/**
	 * Returns the index of the first P in a string, or -1 if the string contains no
	 * P's.
	 * 
	 * @param s the string to examine
	 * @return index of the first vowel, or -1
	 */
	private static int findFirstP(String s) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == 'p' || c == 'P') {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns true if the given character is the letter "P" (lowercase or
	 * uppercase), false otherwise the character to check
	 * 
	 * @return true if the given character is a "P", false otherwise
	 */
	private static boolean isLetterP(char ch) {
		return (ch == 'p' || ch == 'P');
	}

}