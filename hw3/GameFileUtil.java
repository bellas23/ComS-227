package hw3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import api.Tile;

/**
 * Utility class with static methods for saving and loading game files.
 */
public class GameFileUtil {
	/**
	 * Saves the current game state to a file at the given file path.
	 * <p>
	 * The format of the file is one line of game data followed by multiple lines of
	 * game grid. The first line contains the: width, height, minimum tile level,
	 * maximum tile level, and score. The grid is represented by tile levels. The
	 * conversion to tile values is 2^level, for example, 1 is 2, 2 is 4, 3 is 8, 4
	 * is 16, etc. The following is an example:
	 * 
	 * <pre>
	 * 5 8 1 4 100
	 * 1 1 2 3 1
	 * 2 3 3 1 3
	 * 3 3 1 2 2
	 * 3 1 1 3 1
	 * 2 1 3 1 2
	 * 2 1 1 3 1
	 * 4 1 3 1 1
	 * 1 3 3 3 3
	 * </pre>
	 * 
	 * @param filePath the path of the file to save
	 * @param game     the game to save
	 */
	public static void save(String filePath, ConnectGame game) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			// TODO: write to file, can use writer.write()
			writer.write(game.getGrid().getWidth()+" "+game.getGrid().getHeight()+" "+game.getMinTileLevel()+" "+game.getMaxTileLevel()+" "+ game.getScore()+"\n");
			for(int j=0;j<game.getGrid().getHeight();j++) {
				for(int k=0;k<game.getGrid().getWidth();k++) {
					//if(k<game.getGrid().getWidth()-1) {
						writer.write(game.getGrid().getTile(k, j).getLevel()+" ");
					//}else {
						//writer.write(String.valueOf(game.getGrid().getTile(k, j).getLevel()));
					//}
				}
				writer.write("\n");
			}
			
			
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the file at the given file path into the given game object. When the
	 * method returns the game object has been modified to represent the loaded
	 * game.
	 * <p>
	 * See the save() method for the specification of the file format.
	 * 
	 * @param filePath the path of the file to load
	 * @param game     the game to modify
	 * @throws FileNotFoundException 
	 */
	public static void load(String filePath, ConnectGame game){
		for(int j=0;j<game.getGrid().getWidth();j++) {
			for(int k=0;k<game.getGrid().getHeight();k++) {
				game.unselect(j, k);
			}
		}
		File f = new File(filePath);
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int lineCount=-1;
		int xCount=0;
		while(s.hasNextLine()) {
			xCount=0;
			String str = s.nextLine();
			String[] a = str.split(" ");
			if(lineCount ==-1) {
				game.setGrid(new Grid(Integer.parseInt(a[0]),Integer.parseInt(a[1])));
				game.setMinTileLevel(Integer.parseInt(a[2]));
				game.setMaxTileLevel(Integer.parseInt(a[3]));
				game.setScore(Integer.parseInt(a[4]));
			}else {
				for(String n:a) {
					game.getGrid().setTile(new Tile(Integer.parseInt(n)), xCount, lineCount);
					xCount++;
				}
			}
			lineCount++;
		}
			
	}
}
