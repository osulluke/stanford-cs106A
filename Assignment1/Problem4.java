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
		
		findLastBeeper();

		move();
		pushEnds();
		
		return;
	}
	
	public void findLastBeeper() {
		while ( beepersPresent() ) {
			move();
		}
		turnAround();
		move();
		if ( beepersPresent() ) {
			pickBeeper();
		}
		else {
			putBeeper();
		}
	}
	
}
