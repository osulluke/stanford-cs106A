/*
 * File: Target.java
 * Name:
 * Section Leader:
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {
	public void run() {
		GCanvas canv = new GCanvas();
		int height = canv.getHeight();
		int width  = canv.getWidth();

		int oSize = 72;
		int mSize = (int) .65 * oSize;
		int cSize = (int) .30 * oSize;

		GOval outer = new GOval(oSize, oSize, width / 2, height / 2);
		GOval middle = new GOval(mSize, mSize, width / 2, height / 2);
		GOval center = new GOval(cSize, cSize, width / 2, height / 2);
		
		outer.setColor(Color.red);
		middle.setColor(Color.white);
		center.setColor(Color.red);
/*
		add(outer.setColor(Color.RED));
		add(middle.setColor(Color.RED));
		add(center.setColor(Color.RED));
*/
		canv.add(outer);
		canv.add(middle);
		canv.add(center);
		return;
	}
}
