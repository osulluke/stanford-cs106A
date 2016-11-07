import stanford.karel.*;

public class Problem4 extends SuperKarel {
	public void run() {
		placeBrickInMiddle();
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
		pickUpEnds();
		
		return;
	}
	
	public void layDownFirstRow() {
		return;
	}
	
	public void pickUpEnds() {
		return;
	}
	
}
