package lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LineNumberer {
	public static void main(String[] args) throws FileNotFoundException {
		getline();
	}

	public static void getline() throws FileNotFoundException {
		File file = new File("story.txt");
		Scanner scanner = new Scanner(file);
		int lineCount = 1;
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			char[] ch = line.toCharArray();
			System.out.print(lineCount + ": ");
			int counter = 0;
			for (int i = 0; i < ch.length; i++) {
				if (ch[i] == ' ' || ch[i] == ',') {
					counter++;
				}
			}
			System.out.println(line + " | " + (counter + 1));
			lineCount += 1;
		}
		scanner.close();
	}
}