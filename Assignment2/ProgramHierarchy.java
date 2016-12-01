/*
 * File: ProgramHierarchy.java
 * Name:
 * Section Leader:
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {
	public void run() {
		/*
		 * Add initial Program box.
		 */
		makeBox("Program", 350, 50);

		/*
		 * Add next row of "sub" program boxes.
		 */
		makeBox("GraphicsProgram", 150, 100);
		makeBox("ConsoleProgram", 325, 100);
		makeBox("DialogProgram", 500, 100);

		/*
		 * Build the three connecting lines.
		 */
		GLine line1 = new GLine(375, 58, 375, 85);
		GLine line2 = new GLine(375, 58, 200, 85);
		GLine line3 = new GLine(375, 58, 550, 85);

		/*
		 * Add the three connecting lines.
		 */
		add(line1);
		add(line2);
		add(line3);

		return;
	}
	/*
	 * This function makes a rectangle with a centered label, at the specified position.
	 */
	public void makeBox(String title, int x, int y) {
		GLabel lab = new GLabel(title, x, y);

		final int width = (int) lab.getWidth();
		final int height = (int) lab.getHeight();

		GRect rec = new GRect((x - width / 4), y - height, 1.5*width, 1.5*height);
		add(lab);
		add(rec);

		return;
	}
}
