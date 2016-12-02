/*
 * File: ColorChangingSquare.java
 * ------------------------------
 * This program puts up a square in the center of the window
 * and randomly changes its color every second.
 */
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class ColorChangingSquare extends GraphicsProgram {
	
	/* Size of the square in pixels */
	private static final int SQUARE_SIZE = 100;
	
	/* Pause time in milliseconds */
	private static final int PAUSE_TIME = 1000;
	
	public void run() {
		
		GRect square = new GRect(SQUARE_SIZE, SQUARE_SIZE);
		GRect square1 = new GRect(SQUARE_SIZE, SQUARE_SIZE);
		GRect square2 = new GRect(SQUARE_SIZE, SQUARE_SIZE);
		GRect square3 = new GRect(SQUARE_SIZE, SQUARE_SIZE);
		GRect square4 = new GRect(SQUARE_SIZE, SQUARE_SIZE);
		
		square.setFilled(true);
		square1.setFilled(true);
		square2.setFilled(true);
		square3.setFilled(true);
		square4.setFilled(true);
		
		add(square, (getWidth() - SQUARE_SIZE) / 2,
				(getHeight() - SQUARE_SIZE) / 2);
		
		add(square1, (getWidth() - 3*SQUARE_SIZE) / 2,
				(getHeight() - 3*SQUARE_SIZE) / 2);
		
		add(square2, (getWidth() + 1*SQUARE_SIZE) / 2,
				(getHeight() - 3*SQUARE_SIZE) / 2);
		
		add(square3, (getWidth() - 3*SQUARE_SIZE) / 2,
				(getHeight() + SQUARE_SIZE) / 2);
		
		add(square4, (getWidth() + 1*SQUARE_SIZE) / 2,
				(getHeight() + 1*SQUARE_SIZE) / 2);
		
		/* Note: we meant to have this infinite loop */
		while (true) {
			square.setColor(rgen.nextColor());
			square1.setColor(rgen.nextColor());
			square2.setColor(rgen.nextColor());
			square3.setColor(rgen.nextColor());
			square4.setColor(rgen.nextColor());
			pause(rgen.nextDouble(150, 1000));
		}
	}
	
	/* Private instance variables */
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
