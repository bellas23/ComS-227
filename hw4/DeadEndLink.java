package hw4;

import api.Point;

/**
 * models a link that connects a single path to nothing
 * 
 * @author Isabelle Singh
 *
 */
public class DeadEndLink extends AbstractLink {

	/**
	 * creates a new DeadEndLink
	 */
	public DeadEndLink() {
		setNumPaths(1);
	}

	/**
	 * returns the point connected to the given point (in this case null)
	 */
	@Override
	public Point getConnectedPoint(Point point) {
		return null;
	}
}
