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

		int height = 200;
		int width = 200;
		int mid = (height + width) / 4;
		int oSize = 72;
		int mSize = (int) (.65 * oSize);
		int cSize = (int) (.30 * oSize);

		GOval outer = new GOval(mid, mid, oSize, oSize);
		GOval middle = new GOval(mid + oSize/2 - mSize/2, mid + oSize/2 - mSize/2, mSize, mSize);
		GOval center = new GOval(mid + oSize/2 - cSize/2, mid + oSize/2 - cSize/2, cSize, cSize);

		outer.setColor(Color.RED);
		middle.setColor(Color.WHITE);
		center.setColor(Color.RED);

		outer.setFillColor(Color.RED);
		middle.setFillColor(Color.WHITE);
		center.setFillColor(Color.RED);

		outer.setFilled(true);
		middle.setFilled(true);
		center.setFilled(true);

		add(outer);
		add(middle);
		add(center);
		return;
	}
}
