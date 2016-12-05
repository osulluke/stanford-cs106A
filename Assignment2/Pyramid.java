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
import acm.util.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 24;

	public void run() {
		buildPyramid();

		return;
	}

	private void buildPyramid() {
		for (int i = 0; i <= BRICKS_IN_BASE; i++) {
			layBricks(BRICKS_IN_BASE-i);
		}
	}

	private void layBricks(int i) {

		int xPos = (getWidth() - BRICKS_IN_BASE * BRICK_WIDTH)/2;
		int yPos = (getHeight() - (i * BRICK_HEIGHT) - BRICK_HEIGHT);

		for (int j = 0; j < BRICKS_IN_BASE - i; j++) {
			GOval rec = new GOval(xPos + j*BRICK_WIDTH + i*BRICK_WIDTH / 2, yPos, BRICK_WIDTH, BRICK_HEIGHT);
			rec.setFilled(true);
			rec.setFillColor(rgen.nextColor());
			add(rec);
		}
	}
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
