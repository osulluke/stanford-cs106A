import stanford.karel.*;

public class Voter extends SuperKarel {
	
	public void run() {
		move();
		fixBallot();
	}
	
	private void fixBallot() {
		while( !atBallotEdge() ) {
			checkIntent();
		}
		return;
	}
	
	
	/*
	 * Doing it like this will require that Karel never turns
	 * towards the east while inside one of the ballot rows.
	 */
	private boolean atBallotEdge() {
		if ( facingEast() && frontIsBlocked() ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * Method checks to see if the front is clear each time in order
	 * to prevent running into a wall.
	 */
	private void goToNext() {
		if ( frontIsClear() ) {
			move();
		}
		if ( frontIsClear() ) {
			move();
		}
		return;
	}
	
	/*
	 * This will go through a ballot square and if there happens
	 * to be a chad(s) there, it will clean it. It also explicitly
	 * avoids using the direction of '1' (East) while going through, and
	 * any of the turn methods to ensure it doesn't trip the 'atBallotEdge()'
	 * method unnecessarily and cause the program to crash/exit early.
	 */
	private void cleanSquare() {
		cleanIfDirty();
		setDirection(0);
		move();
		cleanIfDirty();
		setDirection(2);
		move();
		move();
		cleanIfDirty();
		setDirection(0);
		move();
		setDirection(1);
		return;
	}
	
	/*
	 * Function will determine whether or not someone had the intent
	 * to vote for a certain block by checking if the middle block 
	 * is removed. If the block is empty, it will clean the rest of 
	 * the square and go to the next square. If they didn't want to 
	 * vote for that block, it will simply just go to the next square.
	 */
	private void checkIntent() {
		if ( !beepersPresent() ) {
			cleanSquare();
			goToNext();
		}
		else {
			goToNext();
		}
	}
	
	/*
	 * Function checks to see if there are beepers present,
	 * and 'while' they are, Karel will pick them up until they 
	 * no longer remain.
	 */
	private void cleanIfDirty() {
		while ( beepersPresent() ) {
			pickBeeper();
		}
		return;
	}
}
