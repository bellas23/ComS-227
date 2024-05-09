package hw4;

import api.Point;
import api.PointPair;

/**
 * models a link with a minimum of 2 to a maximum of 6 paths
 * 
 * @author Isabelle Singh
 *
 */
public class MultiSwitchLink extends AbstractLink {

	/**
	 * array to keep track of the number of point pairs
	 */
	private PointPair[] connections;

	/**
	 * variable returned in getConnectedPoint
	 */
	private Point connectedPoint;

	/**
	 * models a link with a minimum of 2 to a maximum of 6 paths
	 * 
	 * @param connections
	 */
	public MultiSwitchLink(PointPair[] connections) {
		this.connections = connections;
		setNumPaths(connections.length);
	}

	/**
	 * alters pointPair for connectedPoints
	 * 
	 * @param pointPair
	 * @param index
	 */
	public void switchConnection(PointPair pointPair, int index) {
		if (isTrainCrossing()) {
			connections[index] = pointPair;
		}
	}

	/**
	 * returns the point connected to the given point
	 */
	@Override
	public Point getConnectedPoint(Point point) {
		for (int i = 0; i < connections.length; i++) {
			if (connections[i].getPointA() == point) {
				connectedPoint = connections[i].getPointB();
				return connectedPoint;
			} else if (connections[i].getPointB() == point) {
				connectedPoint = connections[i].getPointA();
				return connectedPoint;
			}
		}
		return null;
	}
}
