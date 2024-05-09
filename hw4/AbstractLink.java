package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;

/**
 * abstract method for all link methods to extend
 * 
 * @author Isabelle Singh
 *
 */
public abstract class AbstractLink implements Crossable {

	/*
	 * number of paths in a link
	 */
	private int numPaths;

	/*
	 * boolean to indicate whether the train has entered the crossing
	 */
	private boolean enteredCrossing;

	/*
	 * boolean to indicate whether the train is turning
	 */
	private boolean turn;

	/**
	 * shifts the location of the given positionVector to be between the next pair
	 * of points
	 */
	public void shiftPoints(PositionVector positionVector) {
		if (!enteredCrossing) {
			Point nextPoint = getConnectedPoint(positionVector.getPointB());
			if (nextPoint != null) {
				positionVector.setPointA(nextPoint);
				Point temp = positionVector.getPointB();
				temp.setPointIndex(positionVector.getPointA().getPointIndex() + 1);
				positionVector.setPointB(temp);
			}
		}
	}

	/**
	 * returns number of paths in a link
	 */
	public int getNumPaths() {
		return numPaths;
	}

	/**
	 * indicates the train has entered the crossing
	 */
	public void trainEnteredCrossing() {
		enteredCrossing = true;

	}

	/**
	 * indicates the train has exited the crossing
	 */
	public void trainExitedCrossing() {
		enteredCrossing = false;
	}

	/**
	 * returns whether the train has entered the crossing
	 * 
	 * @return enteredCrossing
	 */
	protected boolean isTrainCrossing() {
		return enteredCrossing;
	}

	/**
	 * sets the number of paths in a link
	 * 
	 * @param paths
	 */
	protected void setNumPaths(int paths) {
		this.numPaths = paths;
	}

	/**
	 * returns whether the train is turning
	 * 
	 * @return turn
	 */
	protected boolean isTrainTurning() {
		return turn;
	}

	/**
	 * sets whether the train is turning in a link
	 * 
	 * @param turn
	 */
	protected void setTurn(boolean turn) {
		this.turn = turn;
	}
}