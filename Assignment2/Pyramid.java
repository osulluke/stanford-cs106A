/*
 * File: Pyramid.java
 * Name: Luke O
 * Section Leader: N/A
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
/*
* This is to make it easier to set/pull data from the size of the window.
*/
	private static final int cWidth = BRICKS_IN_BASE * BRICK_WIDTH + 4 * BRICK_WIDTH;
	private static final int cHeight = BRICKS_IN_BASE * BRICK_HEIGHT + 4 * BRICK_HEIGHT;

	public void run() {
		GCanvas canv = new GCanvas();
		canv.setSize(cWidth, cHeight);
		buildPyramid();
		return;
	}

	private void buildPyramid() {
		for (int i = BRICKS_IN_BASE; i >= 0; i--) {
			layBricks(i);
		}
	}

	private void layBricks(int i) {
		int xPos = (cWidth + (i * BRICK_WIDTH )) / 2;
		int yPos = (cHeight + (BRICKS_IN_BASE - i*BRICK_HEIGHT));

		for (int j = 0; j < BRICKS_IN_BASE - i; j++) {
			GRect rec = new GRect(xPos + j*BRICK_WIDTH, yPos, BRICK_WIDTH, BRICK_HEIGHT);
			add(rec);
		}
	}
}
