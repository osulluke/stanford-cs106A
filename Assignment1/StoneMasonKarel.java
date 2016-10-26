/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		fixCity();
	}
	
	private void placeBrick() {
		putBeeper();
	}
	
	private void fixCity() {
		checkColumn();
		while(anotherColumn()) {
			goToNextColumn();
			checkColumn();
		}
	}
	
	private void checkColumn() {
		climbColumn();
		descendColumn();		
	}
	
	private void goToNextColumn () {
		if(frontIsClear()) {
			for (int i = 0; i<4; i++) {
				move();
			}	
		}
		else {
			;
		}
	}
	
	private void climbColumn() {
		if(!beepersPresent()) {
			placeBrick();
		}
		turnLeft();
		while(frontIsClear()) {
			move();
			if (beepersPresent()) {
				;
			}
			else {
				placeBrick();
			}
		}
	}
	
	private void descendColumn() {
		turnAround();
		while(!frontIsBlocked()) {
			move();
		}
		turnLeft();
	}
	
	private boolean anotherColumn() {
		if (facingEast() && !frontIsBlocked()) {
			return true;
		}
		else {
			return false;
		}
	}

}
