package hw3;

import java.util.Random;
import java.util.ArrayList;

import api.ScoreUpdateListener;
import api.ShowDialogListener;
import api.Tile;

/**
 * Class that models a game.
 */
public class ConnectGame {
	private ShowDialogListener dialogListener;
	private ScoreUpdateListener scoreListener;
	/**
	 * width of grid
	 */
	private int width;
	/**
	 * height of grid
	 */
	private int height;
	/**
	 * grid to store files
	 */
	private Grid game;
	/**
	 * minimum level for tiles
	 */
	private int min;
	/**
	 * max level for tiles
	 */
	private int max;
	/**
	 * used to generate random numbers
	 */
	private Random r;
	/**
	 * players score
	 */
	private long playerScore;
	/**
	 * list of selected tiles
	 */
	private ArrayList<Tile> selected = new ArrayList<Tile>();

	/**
	 * Constructs a new ConnectGame object with given grid dimensions and minimum
	 * and maximum tile levels.
	 * 
	 * @param width  grid width
	 * @param height grid height
	 * @param min    minimum tile level
	 * @param max    maximum tile level
	 * @param rand   random number generator
	 */
	public ConnectGame(int width, int height, int min, int max, Random rand) {
		this.width = width;
		this.height = height;
		game = new Grid(width, height);
		this.min = min;
		this.max = max;
		r = rand;
	}

	/**
	 * Gets a random tile with level between minimum tile level inclusive and
	 * maximum tile level exclusive. For example, if minimum is 1 and maximum is 4,
	 * the random tile can be either 1, 2, or 3.
	 * <p>
	 * DO NOT RETURN TILES WITH MAXIMUM LEVEL
	 * 
	 * @return a tile with random level between minimum inclusive and maximum
	 *         exclusive
	 */
	public Tile getRandomTile() {
		Tile t;
		int range = max - min;
		int level = r.nextInt(range) + min;
		t = new Tile(level);
		return t;
	}

	/**
	 * Regenerates the grid with all random tiles produced by getRandomTile().
	 */
	public void radomizeTiles() {
		for (int j = 0; j < width; j++) {
			for (int k = 0; k < height; k++) {
				game.setTile(getRandomTile(), j, k);
			}
		}
		selectedFalse();

	}

	/**
	 * sets all tiles isSelected to false clears list of selected tiles
	 */
	private void selectedFalse() {
		for (int j = 0; j < width; j++) {
			for (int k = 0; k < height; k++) {
				game.getTile(j, k).setSelect(false);
			}
		}
		selected.clear();
	}

	/**
	 * Determines if two tiles are adjacent to each other. The may be next to each
	 * other horizontally, vertically, or diagonally.
	 * 
	 * @param t1 one of the two tiles
	 * @param t2 one of the two tiles
	 * @return true if they are next to each other horizontally, vertically, or
	 *         diagonally on the grid, false otherwise
	 */
	public boolean isAdjacent(Tile t1, Tile t2) {
		int t1x = t1.getX();
		int t2x = t2.getX();
		int t1y = t1.getY();
		int t2y = t2.getY();
		int xdistance = Math.abs(t1x - t2x);
		int ydistance = Math.abs(t1y - t2y);
		if (xdistance <= 1 && ydistance <= 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Indicates the user is trying to select (clicked on) a tile to start a new
	 * selection of tiles.
	 * <p>
	 * If a selection of tiles is already in progress, the method should do nothing
	 * and return false.
	 * <p>
	 * If a selection is not already in progress (this is the first tile selected),
	 * then start a new selection of tiles and return true.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return true if this is the first tile selected, otherwise false
	 */
	public boolean tryFirstSelect(int x, int y) {
		if (countSelected() > 0) {
			return false;
		} else {
			game.getTile(x, y).setSelect(true);
			selected.add(game.getTile(x, y));
			return true;
		}
	}

	/**
	 * counts the number of selected tiles
	 * 
	 * @return number of tiles selected
	 */
	private int countSelected() {
		int count = 0;
		for (int j = 0; j < width; j++) {
			for (int k = 0; k < height; k++) {
				if (game.getTile(j, k).isSelected()) {
					count++;
				}
			}
		}
		// System.out.println("########### "+count);
		return count;
	}

	/**
	 * Indicates the user is trying to select (mouse over) a tile to add to the
	 * selected sequence of tiles. The rules of a sequence of tiles are:
	 * 
	 * <pre>
	 * 1. The first two tiles must have the same level.
	 * 2. After the first two, each tile must have the same level or one greater than the level of the previous tile.
	 * </pre>
	 * 
	 * For example, given the sequence: 1, 1, 2, 2, 2, 3. The next selected tile
	 * could be a 3 or a 4. If the use tries to select an invalid tile, the method
	 * should do nothing. If the user selects a valid tile, the tile should be added
	 * to the list of selected tiles.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 */
	public void tryContinueSelect(int x, int y) {
		if (countSelected() < 1) {
			tryFirstSelect(x, y);
		} else if (selected.size() >= 2 && game.getTile(x, y) == selected.get(selected.size() - 2)) {
			game.getTile(selected.get(selected.size() - 1).getX(), selected.get(selected.size() - 1).getY())
					.setSelect(false);
			selected.remove(selected.size() - 1);
		} else {
			if (selected.contains(game.getTile(x, y)) == false) {
				if (selected != null) {
					if (isAdjacent(game.getTile(x, y), selected.get(selected.size() - 1))) {
						if (selected.size() == 1) {
							if (selected.get(0).getLevel() == game.getTile(x, y).getLevel()) {
								game.getTile(x, y).setSelect(true);
								selected.add(game.getTile(x, y));
							}
						} else {
							if (selected.get(selected.size() - 1).getLevel() - game.getTile(x, y).getLevel() == -1
									|| selected.get(selected.size() - 1).getLevel()
											- game.getTile(x, y).getLevel() == 0) {
								game.getTile(x, y).setSelect(true);
								selected.add(game.getTile(x, y));
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Indicates the user is trying to finish selecting (click on) a sequence of
	 * tiles. If the method is not called for the last selected tile, it should do
	 * nothing and return false. Otherwise it should do the following:
	 * 
	 * <pre>
	 * 1. When the selection contains only 1 tile reset the selection and make sure all tiles selected is set to false.
	 * 2. When the selection contains more than one block:
	 *     a. Upgrade the last selected tiles with upgradeLastSelectedTile().
	 *     b. Drop all other selected tiles with dropSelected().
	 *     c. Reset the selection and make sure all tiles selected is set to false.
	 * </pre>
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return return false if the tile was not selected, otherwise return true
	 */
	public boolean tryFinishSelection(int x, int y) {
		if (selected.size() > 0 && game.getTile(x, y) == selected.get(selected.size() - 1)) {
			if (selected.size() == 1) {
				selectedFalse();
				return true;
			} else {
				for (int i = 0; i < selected.size(); i++) {
					playerScore += (Math.pow(2, selected.get(i).getLevel()));

				}
				// System.out.println("************");
				upgradeLastSelectedTile();
				dropSelected();
				// selectedFalse();
				return true;
			}

		} else {
			return false;
		}
	}

	/**
	 * Increases the level of the last selected tile by 1 and removes that tile from
	 * the list of selected tiles. The tile itself should be set to unselected.
	 * <p>
	 * If the upgrade results in a tile that is greater than the current maximum
	 * tile level, both the minimum and maximum tile level are increased by 1. A
	 * message dialog should also be displayed with the message "New block 32,
	 * removing blocks 2". Not that the message shows tile values and not levels.
	 * Display a message is performed with dialogListener.showDialog("Hello,
	 * World!");
	 */
	public void upgradeLastSelectedTile() {
		// TODO
		int currentLevel = selected.get(selected.size() - 1).getLevel();
		Tile t = selected.get(selected.size() - 1);
		t.setLevel(currentLevel + 1);
		// System.out.println(t+"***********");
		game.setTile(t, selected.get(selected.size() - 1).getX(), selected.get(selected.size() - 1).getY());
		// System.out.println(game.getTile(selected.get(selected.size()-1).getX(),
		// selected.get(selected.size()-1).getY())+"***********");
		selected.remove(selected.size() - 1);
		// System.out.println("********");
		if (t.getLevel() > max) {
			max = t.getLevel();
			min++;
			dialogListener.showDialog("New block " + Math.pow(2, max) + " removing blocks " + Math.pow(2, min - 1));
			dropLevel(min - 1);
			// System.out.println("**$$$***");
		}
	}

	/**
	 * Gets the selected tiles in the form of an array. This does not mean selected
	 * tiles must be stored in this class as a array.
	 * 
	 * @return the selected tiles in the form of an array
	 */
	public Tile[] getSelectedAsArray() {
		Tile[] a = new Tile[selected.size()];
		for (int i = 0; i < selected.size(); i++) {
			a[i] = selected.get(i);
		}
		return a;
	}

	/*
	 * drops a single tile
	 * 
	 * @param x the column of the tile selected
	 * 
	 * @param y the row of the tile selected
	 */
	private void dropTile(int x, int y) {
		for (int j = y; j > 0; j--) {
			game.setTile(game.getTile(x, j - 1), x, j);
		}
		game.setTile(getRandomTile(), x, 0);
	}

	/**
	 * Removes all tiles of a particular level from the grid. When a tile is
	 * removed, the tiles above it drop down one spot and a new random tile is
	 * placed at the top of the grid.
	 * 
	 * @param level the level of tile to remove
	 */
	public void dropLevel(int level) {
		for (int j = 0; j < width; j++) {
			for (int k = 0; k < height; k++) {
				if (game.getTile(j, k).getLevel() == level) {
					dropTile(j, k);
				}
			}
		}
	}

	/**
	 * Removes all selected tiles from the grid. When a tile is removed, the tiles
	 * above it drop down one spot and a new random tile is placed at the top of the
	 * grid.
	 */
	public void dropSelected() {
		for (int j = 0; j < width; j++) {
			for (int k = 0; k < height; k++) {
				if (game.getTile(j, k).isSelected()) {
					dropTile(j, k);
				}
			}
		}
		selectedFalse();
	}

	/**
	 * Remove the tile from the selected tiles.
	 * 
	 * @param x column of the tile
	 * @param y row of the tile
	 */
	public void unselect(int x, int y) {
		game.getTile(x, y).setSelect(false);
		selected.remove(game.getTile(x, y));
	}

	/**
	 * Gets the player's score.
	 * 
	 * @return the score
	 */
	public long getScore() {
		// TODO
		return playerScore;
	}

	/**
	 * Gets the game grid.
	 * 
	 * @return the grid
	 */
	public Grid getGrid() {
		// TODO
		return game;
	}

	/**
	 * Gets the minimum tile level.
	 * 
	 * @return the minimum tile level
	 */
	public int getMinTileLevel() {
		// TODO
		return min;
	}

	/**
	 * Gets the maximum tile level.
	 * 
	 * @return the maximum tile level
	 */
	public int getMaxTileLevel() {
		// TODO
		return max;
	}

	/**
	 * Sets the player's score.
	 * 
	 * @param score number of points
	 */
	public void setScore(long score) {
		// TODO
		playerScore = score;
	}

	/**
	 * Sets the game's grid.
	 * 
	 * @param grid game's grid
	 */
	public void setGrid(Grid grid) {
		game = grid;
	}

	/**
	 * Sets the minimum tile level.
	 * 
	 * @param minTileLevel the lowest level tile
	 */
	public void setMinTileLevel(int minTileLevel) {
		min = minTileLevel;
	}

	/**
	 * Sets the maximum tile level.
	 * 
	 * @param maxTileLevel the highest level tile
	 */
	public void setMaxTileLevel(int maxTileLevel) {
		// TODO
		max = maxTileLevel;
	}

	/**
	 * Sets callback listeners for game events.
	 * 
	 * @param dialogListener listener for creating a user dialog
	 * @param scoreListener  listener for updating the player's score
	 */
	public void setListeners(ShowDialogListener dialogListener, ScoreUpdateListener scoreListener) {
		this.dialogListener = dialogListener;
		this.scoreListener = scoreListener;
	}

	/**
	 * Save the game to the given file path.
	 * 
	 * @param filePath location of file to save
	 */
	public void save(String filePath) {
		GameFileUtil.save(filePath, this);
	}

	/**
	 * Load the game from the given file path
	 * 
	 * @param filePath location of file to load
	 */
	public void load(String filePath) {
		GameFileUtil.load(filePath, this);
	}
}
