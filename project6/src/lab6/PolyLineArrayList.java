package lab6;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import plotter.Plotter;
import plotter.Polyline;

public class PolyLineArrayList {
	public static void main(String[] args) throws FileNotFoundException {
		Plotter plotter = new Plotter();
		File file = new File("hello.txt");
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			Polyline p = parseOneLine(line);
			plotter.plot(p);
		}
	}

	private static ArrayList<Polyline> readFile(String filename) throws FileNotFoundException {
		ArrayList<Polyline> p = new ArrayList<Polyline>();
		File file = new File("hello.txt");
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			System.out.println(line);
			p.add(parseOneLine(line));
		}
		scanner.close();
		System.out.println("*****************************");
		System.out.println(p.get(0));
		return p;
	}

	private static Polyline parseOneLine(String line) {
		Polyline p1;
		try {
			String[] arr = line.split(" ");
			int t = Integer.parseInt(arr[0]);
			p1 = new Polyline(arr[1], t);
			for (int i = 2; i < arr.length - 1; i += 2) {
				p1.addPoint(new Point(Integer.parseInt(arr[i]), Integer.parseInt(arr[i + 1])));
			}
			
		} catch (Exception e) {
			String[] arr = line.split(" ");
			p1 = new Polyline(arr[0]);
			for (int i = 1; i < arr.length - 1; i += 2) {
				p1.addPoint(new Point(Integer.parseInt(arr[i]), Integer.parseInt(arr[i + 1])));
			}
		}
		return p1;
	}
}
