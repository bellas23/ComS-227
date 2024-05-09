package hw2;

import api.PlayerPosition;
import api.BallType;
import static api.PlayerPosition.*;
import static api.BallType.*;

/**
 * Class that models the game of three-cushion billiards.
 * 
 * @author Isabelle Singh
 */
public class ThreeCushion {

	/**
	 * The cue ball of the current player
	 */
	private BallType CueBall;

	/**
	 * The player of the current inning
	 */
	private PlayerPosition inningPlayer;

	/**
	 * The lag winner
	 */
	private PlayerPosition LagWinner;

	/**
	 * The current inning
	 */
	private int inning;

	/**
	 * The number of points needed to win the game
	 */
	private int PointsToWin;

	/**
	 * Keeps track of the numbr of fouls in an inning
	 */
	private int numFouls = 0;

	/**
	 * Indicates when the cue ball has hit a cushion
	 */
	private int impactCushion;

	/**
	 * Indicates when the cue ball has hit the red ball
	 */
	private int redBallStrike;

	/**
	 * Keeps track of the total number of balls hit in a shot
	 */
	private int totalBallsHit;

	/**
	 * Keeps track of player A's points
	 */
	private int currentPointsA;

	/**
	 * Keeps track of player B's points
	 */
	private int currentPointsB;

	/**
	 * Indicates when the cue ball has struck the ball other than the red ball
	 */
	private int otherBallStrike;

	/**
	 * Indicates when the cue ball has hit a cushion before a ball
	 */
	private boolean cushionBeforeBall = false;

	/**
	 * Indicates when an inning has begun
	 */
	private boolean inningStarted = false;

	/**
	 * Indicates when a shot has started
	 */
	private boolean shotStarted = false;

	/**
	 * Indicates when the cue ball has hit a cushion after the very last ball (foul)
	 */
	private boolean cushionLast = false;

	/**
	 * Indicates when the method lagWinnerChooses() has run
	 */
	private boolean LagChose = false;

	/**
	 * Indicates when the game is over
	 */
	private boolean gameOver = false;

	/**
	 * Indicates a bank shot
	 */
	private boolean bankShot = false;

	/**
	 * Indicates a break shot
	 */
	private boolean breakShot;

	/**
	 * Creates a new game of three-cushion billiards with a given lag winner and the
	 * predetermined number of points required to win the game.
	 * 
	 * @param lagWinner   the winner of the lag
	 * @param pointsToWin the number of points needed to win the game
	 */
	public ThreeCushion(PlayerPosition lagWinner, int pointsToWin) {
		LagWinner = lagWinner;
		PointsToWin = pointsToWin;
		PointsToWin = 3;
		inning = 1;
	}

	/**
	 * Returns true if the game is over.
	 * 
	 * @return gameOver Indicates when the game has ended
	 */
	public boolean isGameOver() {
		if (getPlayerAScore() >= PointsToWin || getPlayerBScore() >= PointsToWin) {
			gameOver = true;
		} else {
			gameOver = false;
		}
		return gameOver;
	}

	/**
	 * Indicates the cue stick has struck the given ball.
	 * 
	 * @param ball the ball struck by the cue stick
	 */
	public void cueStickStrike(BallType ball) {
		if (!gameOver) {
			cushionBeforeBall = false;
			totalBallsHit = 0;
			impactCushion = 0;
			if (!gameOver) {
				if (shotStarted) {
					foul();
				} else {
					if (ball == CueBall) {
						inningStarted = true;
						shotStarted = true;
					} else {
						foul();
					}
				}
			}
			impactCushion = 0;
			totalBallsHit = 0;
		}
		if (gameOver) {
			inningStarted = false;
			shotStarted = false;
		}
	}

	/**
	 * Indicates that all balls have stopped motion.
	 */
	public void endShot() {
		if (!gameOver && numFouls == 0) {
			if (cushionBeforeBall && totalBallsHit >= 2 && redBallStrike > 0 && otherBallStrike > 0) {
				bankShot = true;
			}
			if (cushionLast) {
				foul();
			}
			if (LagChose) {
				if (totalBallsHit == 0 && numFouls == 0) {
					inningStarted = false;
					turnFinished();
				}
				if (numFouls == 0) {
					if (totalBallsHit >= 2 && impactCushion >= 3) {
						if (inningPlayer == PLAYER_A) {
							currentPointsA++;
						} else {
							currentPointsB++;
						}
					}
				}
				if (shotStarted && !gameOver) {
					shotStarted = false;
				}

				numFouls = 0;
				totalBallsHit = 0;
			}
			breakShot = false;
			redBallStrike = 0;
			otherBallStrike = 0;
		}
		if (gameOver) {
			inningStarted = false;
			shotStarted = false;
		}
	}

	/**
	 * Ends the player's inning, even if the current shot has not yet ended.
	 */
	public void foul() {
		if (LagChose && !gameOver) {
			shotStarted = false;
			inningStarted = false;
			bankShot = false;
			breakShot = false;
			numFouls++;
			turnFinished();
		}
		if (gameOver) {
			inningStarted = false;
			shotStarted = false;
		}
	}

	/**
	 * Ends the current player's turn
	 */
	private void turnFinished() {
		if (!gameOver) {
			inning++;
			if (inningPlayer == PLAYER_A) {
				inningPlayer = PLAYER_B;
			} else {
				inningPlayer = PLAYER_A;
			}
			if (CueBall == WHITE) {
				CueBall = YELLOW;
			} else {
				CueBall = WHITE;
			}
		}
		if (gameOver) {
			inningStarted = false;
			shotStarted = false;
		}
	}

	/**
	 * Indicates the player's cue ball has struck the given ball.
	 * 
	 * @param ball the ball struck by the current player's cue ball
	 */
	public void cueBallStrike(BallType ball) {
		if (!gameOver) {
			if (ball == RED) {
				redBallStrike++;
			} else {
				otherBallStrike++;
			}
			cushionLast = false;
			if (shotStarted) {
				inningStarted = true;
				if (breakShot && impactCushion > 0 && totalBallsHit == 0) {
					foul();
				}
				totalBallsHit++;
				if (breakShot && totalBallsHit == 1 && ball != RED) {
					foul();
				}
				if (impactCushion < 3) {
					bankShot = false;
				}

			}
		}
		if (gameOver) {
			inningStarted = false;
			shotStarted = false;
		}
	}

	/**
	 * Indicates the given ball has impacted the given cushion.
	 */
	public void cueBallImpactCushion() {
		impactCushion++;
		cushionLast = true;
		if (totalBallsHit == 0 && impactCushion >= 3) {
			cushionBeforeBall = true;
		}
	}

	/**
	 * Returns true if and only if the most recently completed shot was a bank shot.
	 * 
	 * @return bankShot represents the bank shot
	 */
	public boolean isBankShot() {
		return bankShot;
	}

	/**
	 * Gets the current player.
	 * 
	 * @return inningPlayer the player of the current inning
	 */
	public PlayerPosition getInningPlayer() {
		return inningPlayer;
	}

	/**
	 * Returns true if the shooting player has taken their first shot of the inning.
	 * 
	 * @return innningStarted represents when an inning has started
	 */
	public boolean isInningStarted() {
		return inningStarted;
	}

	/**
	 * Returns true if a shot has been taken (see cueStickStrike()), but not ended
	 * (see endShot()).
	 * 
	 * @return shotStarted represents when a shot has started
	 */
	public boolean isShotStarted() {
		return shotStarted;
	}

	/**
	 * Gets the inning number.
	 * 
	 * @return inning the number of the current inning
	 */
	public int getInning() {
		return inning;
	}

	/**
	 * Player B's score
	 * 
	 * @return currentPointsB current points of player B
	 */
	public int getPlayerBScore() {
		return currentPointsB;
	}

	/**
	 * Player A's score
	 * 
	 * @return currentPointsA current points of player A
	 */
	public int getPlayerAScore() {
		return currentPointsA;
	}

	/**
	 * Gets the cue ball of the current player.
	 * 
	 * @return CueBall the current player's cue ball
	 */
	public BallType getCueBall() {
		return CueBall;
	}

	/**
	 * Returns true if and only if this is the break shot.
	 * 
	 * @return breakShot Represents the break shot
	 */
	public boolean isBreakShot() {
		return breakShot;
	}

	/**
	 * Sets whether the player that won the lag chooses to break (take first shot),
	 * or chooses the other player to break.
	 * 
	 * @param selfBreak indicates whether the lag winner chooses to break
	 * @param cueBall   the player's cue ball
	 */
	public void lagWinnerChooses(boolean selfBreak, BallType cueBall) {
		CueBall = cueBall;
		LagChose = true;

		if (selfBreak) {
			breakShot = true;
		} else {
			breakShot = false;
		}
		if (LagWinner == PLAYER_A && selfBreak) {
			inningPlayer = PLAYER_A;
			if (CueBall == WHITE) {
				CueBall = WHITE;
			} else {
				CueBall = YELLOW;
			}
		}

		else {
			inningPlayer = PLAYER_B;
			if (CueBall == WHITE) {
				CueBall = WHITE;
			} else {
				CueBall = YELLOW;
			}
		}
	}

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player A*: X Player B: Y, Inning: Z</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which player is at the
	 * table this inning. The number after the player's name is their score. Z is
	 * the inning number. Other messages will appear at the end of the string.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String fmt = "Player A%s: %d, Player B%s: %d, Inning: %d %s%s";
		String playerATurn = "";
		String playerBTurn = "";
		String inningStatus = "";
		String gameStatus = "";
		if (getInningPlayer() == PLAYER_A) {
			playerATurn = "*";
		} else if (getInningPlayer() == PLAYER_B) {
			playerBTurn = "*";
		}
		if (isInningStarted()) {
			inningStatus = "started";
		} else {
			inningStatus = "not started";
		}
		if (isGameOver()) {
			gameStatus = ", game result final";
		}
		return String.format(fmt, playerATurn, getPlayerAScore(), playerBTurn, getPlayerBScore(), getInning(),
				inningStatus, gameStatus);
	}
}
