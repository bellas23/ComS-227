package mini;

/**
 * Utility class with static methods for loop practice.
 */
public class LoopsInfinityAndBeyond {

	/**
	 * Private constructor to disable instantiation.
	 */
	private LoopsInfinityAndBeyond() {
	}

	/**
	 * Define a flying saucer as the following string pattern: one ‘(‘, followed by
	 * zero to many ‘=’, followed by one ‘)’. Write a Java method that, given a
	 * string find the first instance of a flying saucer (starting from the left)
	 * and return its length. If no flying saucer exists return 0.
	 * <p>
	 * For example: Given: "(==)" Return: 4
	 * <p>
	 * Given: "***()**(===)" Return: 2
	 * <p>
	 * Given: "****(***)" Return: 0
	 * 
	 * @param source input string
	 * @return the length
	 */
	public static int flyingSaucerLength(String str) {
		int count = 0;
		int start = -1;

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				count = 1;
				start = i;

				if (str.charAt(i + 1) != '=' && str.charAt(i + 1) != ')') {
					count = 0;
					break;
				}
			} else if (str.charAt(i) == ')') {
				if (count > 0) {
					return i - start + 1;
				}
			} else if (str.charAt(i) == '=') {
				count++;
			}
		}

		return count;
	}

	/**
	 * Write a Java method that, given a string which many contain a flying saucer
	 * broken into two parts with characters in between, return a string where the
	 * flying is fixed by removing the in between characters. Look for the two parts
	 * of the flying saucer from left to right and fix the saucer with the first
	 * available parts.
	 * <p>
	 * For example: Given: ***(==****===)*** Return: ***(=====)***
	 * <p>
	 * Given: ***(==****)**=)* Return: ***(==)**=)*
	 * <p>
	 * Given: ***(==)** Return: ***(==)**
	 * 
	 * @param s
	 * @return
	 */
	public static String fixFlyingSaucer(String s) {
		int indexOpenBracket = s.indexOf('(');

		int indexCloseBracket = s.indexOf(')');

		if (indexOpenBracket == -1 || indexCloseBracket == -1) {
			return s;
		}
		String betweenBrackets = s.substring(indexOpenBracket + 1, indexCloseBracket);
		String newBetweenBrackets = betweenBrackets.replaceAll("[^=]", "");
		return s.substring(0, indexOpenBracket + 1) + newBetweenBrackets + s.substring(indexCloseBracket);
	}

	/**
	 * Write a Java method that, given a string which many contain many flying
	 * saucers, return the number of flying saucers. For this problem a flying
	 * saucer may wrap around from the right side of the string to the left.
	 * <p>
	 * For example: Given: ***(===)*** Return: 1
	 * <p>
	 * Given: =)**(==)**( Return: 2
	 * <p>
	 * Given: ***(=*=)** Return: 0
	 * 
	 * @param s
	 * @return
	 */
	public static int countFlyingSaucers(String str) {
		int count;
		int totalCount;

		count = 0;
		totalCount = 0;

		int i = 0;
		while (i < str.length()) {
			if (str.charAt(i) == '(') {
				int j = i + 1;
				while (j < str.length() && str.charAt(j) == '=') {
					j++;
				}
				if (j < str.length() && str.charAt(j) == ')') {
					count++;
					i = j + 1;
				} else {
					i++;
				}
			} else {
				i++;
			}
		}

		String longStr = str + str;

		int k = 0;
		while (k < longStr.length()) {
			if (longStr.charAt(k) == '(') {
				int j = k + 1;
				while (j < longStr.length() && longStr.charAt(j) == '=') {
					j++;
				}
				if (j < longStr.length() && longStr.charAt(j) == ')') {
					totalCount++;
					k = j + 1;
				} else {
					k++;
				}
			} else {
				k++;
			}
		}

		return totalCount - count;
	}

	/**
	 * Write a Java method that, given a string which many contain many flying
	 * saucers, shifts all of the saucers one character to the right. For this
	 * problem a flying saucer may wrap around from the right side of the string to
	 * the left. The returned string should have the same number of characters as
	 * the given string. This is achieved by moving the character to the right of a
	 * saucer to its left. It can be assumed that saucers will never be touching
	 * each other (i.e., there will always be at least one character between any two
	 * saucers). Also, a saucer will not touch itself (e.g., "=)(=").
	 * <p>
	 * For example: Given: ***(===)*** Return: ****(===)**
	 * <p>
	 * Given: =)**(==)**( Return: (=)***(==)*
	 * <p>
	 * Given: a()bcde(=*=)fg Return: ab()cde(=*=)fg
	 * 
	 * @param s
	 * @return
	 */
	public static String flyingSaucersFly(String input) {
		int openCount = 0;
		int numSpaces = 0;
		int charCount = 0;
		int indexOpen = -1;
		int indexClose = 0;
		String flyingSaucer = "";
		String temp = "";

		for (int i = 0; i < input.length(); i++) {
			char currChar = input.charAt(i);

			if (currChar == '(') {
				openCount++;
				indexOpen = 1;

				if (input.charAt(i + 1) == ')') {
					openCount++;
					indexClose = i + 1;
					temp = input.substring(indexOpen, indexClose + 1);
					flyingSaucer = input.substring(0, indexOpen) + input.substring(indexClose + 1, indexClose + 2)
							+ temp + input.substring(indexClose + 2);
					break;
				}
			} else if (currChar == ')') {
				openCount++;
				indexClose = i;

				if (openCount >= 1 && charCount == 0 && numSpaces != 1) {
					temp = input.substring(input.length() - 1);
					flyingSaucer = temp + input.substring(0, input.length() - 1);
					break;
				} else if (openCount >= 1 && numSpaces == 1) {
					temp = input.substring(indexOpen, indexClose + 1);
					flyingSaucer = input.substring(0, indexOpen) + input.substring(indexClose + 1, indexClose + 2)
							+ temp + input.substring(indexClose + 2);
					break;
				}
			} else if (currChar == '=') {
				openCount++;
			} else if (currChar != ' ') {
				if (openCount > 1) {
					charCount = 0;
				} else if (openCount < 1) {
					numSpaces += 1;
				} else {
					charCount += 1;
				}
			}
		}

		return flyingSaucer;
	}

}