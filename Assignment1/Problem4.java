import stanford.karel.*;

public class Problem4 extends SuperKarel {
	public void run() {
		initializeKarel();
		placeBrickInMiddle();
		return;
	}
	
	public void initializeKarel() {
		setBeepersInBag(10000);
		return;
	}
	
	public void placeBrickInMiddle() {
		/*
		 * Problem strategy will be to lay down one full row of bricks
		 * and then cycle back and forth between the end points, picking
		 * up the ends of the rows, in turn, until there is only one 
		 * brick remaining.
		 */
		layDownFirstRow();
		pickUpEdges();
		pushEnds();
		
		return;
	}
	
	public void layDownFirstRow() {
		putBeeper();
		while ( frontIsClear() ) {
			move();
			putBeeper();
		}
		return;
	}
	
	public void pickUpEdges() {
		while ( frontIsClear() ) {
			move();
		}
		pickBeeper();
		turnAround();
		
		while ( frontIsClear() ) {
			move();
		}
		pickBeeper();
		turnAround();
		move();
		
		return;
	}
	
	public void pushEnds() {
		
		while ( beepersPresent() ) {
			findEdgeBeeper();
			if ( checkLastBeeper() ) {
				break;
			}
			else {
				pickBeeper();
				/*
				 * Call findEdgeBeeper() to begin scan again.
				 */
				findEdgeBeeper();
			}
		}
		return;
	}
	
	public void findEdgeBeeper() {
		/*
		 * Move forward until there are no more beepers.
		 */
		while ( beepersPresent() ) {
			move();
		}
		// Go back to previous beeper
		turnAround();
		move(); //Karel should now be on the previous (and an edge...) beeper.
	}
	
	public boolean checkLastBeeper() {
		/*
		 * This function is called when Karel is on an edge beeper
		 * that needs to be tested if it's the last one in the world.
		 * If it is, the function returns true, and if not, returns 
		 * false.
		 */
		
		move();
		if ( beepersPresent() ) {
			turnAround();
			move();
			return false;
		}
		else {
			return true;
		}
	}
}
