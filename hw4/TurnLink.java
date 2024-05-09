package hw4;

import api.Point;

/**
 * models a fixed link with 3 paths
 * 
 * @author Isabelle Singh
 *
 */
public class TurnLink extends AbstractLink {
	/**
	 * variable to keep track of endpointA
	 */
	private Point endpointA;

	/**
	 * variable to keep track of endpointB
	 */
	private Point endpointB;

	/**
	 * variable to keep track of endpointC
	 */
	private Point endpointC;

	/**
	 * creates a new TurnLink
	 * 
	 * @param endpointA
	 * @param endpointB
	 * @param endpointC
	 */
	public TurnLink(Point endpointA, Point endpointB, Point endpointC) {
		this.endpointA = endpointA;
		this.endpointB = endpointB;
		this.endpointC = endpointC;
		setNumPaths(3);
	}

	/**
	 * returns the point connected to the given point
	 */
	@Override
	public Point getConnectedPoint(Point point) {
		if (point == endpointA) {
			return endpointC;
		} else if (point == endpointB) {
			return endpointA;
		} else if (point == endpointC) {
			return endpointA;
		} else {
			return null;
		}
	}
}
