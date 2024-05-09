package hw4;

import api.Point;

/**
 * models a link that connects two given endpoints
 * 
 * @author Isabelle Singh
 *
 */
public class CouplingLink extends AbstractLink {
	/**
	 * variable to keep track of endpoint1
	 */
	private Point endpoint1;

	/**
	 * variable to keep track of endpoint2
	 */
	private Point endpoint2;

	/**
	 * creates a new CouplingLink
	 * 
	 * @param endpoint1
	 * @param endpoint2
	 */
	public CouplingLink(Point endpoint1, Point endpoint2) {
		this.endpoint1 = endpoint1;
		this.endpoint2 = endpoint2;
		setNumPaths(2);
	}

	/**
	 * returns the point connected to the given point
	 */
	@Override
	public Point getConnectedPoint(Point point) {
		if (point == endpoint1) {
			return endpoint2;
		} else if (point == endpoint2) {
			return endpoint1;
		} else {
			return null;
		}
	}
}
