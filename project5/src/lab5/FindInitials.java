package lab5;

import java.util.Scanner;

public class FindInitials {
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter persons full name:");
		String fullName = scnr.nextLine();
		String initial = findInitials(fullName);
		System.out.println("Initials:" + initial);
		System.out.println("Enter String to find first vowel index in it:");
		String s = scnr.nextLine();
		int index = indexVowel(s);
		System.out.println("Index of first vowel in " + s + " is : " + index);
	}

	public static String findInitials(String fullName) {
		String initial = "";
		String name[] = fullName.split(" ");
		for (int i = 0; i < name.length; i++)
			initial = initial.concat(Character.toString(name[i].charAt(0)));
		return initial;
	}

	public static int indexVowel(String s) {
		int index;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if ("aeiouAEIOU".indexOf(ch) >= 0) {
				index = i;
				return index;
			}
		}
		return -1;
	}
}