package hw4;

import api.Point;

/**
 * models a switchable link with 3 paths
 * 
 * @author Isabelle Singh
 *
 */
public class SwitchLink extends AbstractLink {

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
	 * variable to tell whether the train is turning
	 */
	private boolean turn;

	/**
	 * creates a new SwitchLink
	 * 
	 * @param endpointA
	 * @param endpointB
	 * @param endpointC
	 */
	public SwitchLink(Point endpointA, Point endpointB, Point endpointC) {
		this.endpointA = endpointA;
		this.endpointB = endpointB;
		this.endpointC = endpointC;
		setNumPaths(3);
	}

	/**
	 * sets whether the train is turning
	 */
	@Override
	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	/**
	 * returns the point connected to the given point
	 */
	@Override
	public Point getConnectedPoint(Point point) {

		if (isTrainCrossing()) {
			if (isTrainTurning() && point == endpointA) {
				return endpointC;
			} else if (!isTrainTurning() && point == endpointA) {
				return endpointB;
			} else if (point == endpointB) {
				return endpointA;
			} else if (point == endpointC) {
				return endpointA;
			} else {
				return null;
			}
		} else {
			if (point == endpointB) {
				return endpointA;
			} else if (point == endpointC) {
				return endpointA;
			} else {
				return null;
			}
		}
	}
}
