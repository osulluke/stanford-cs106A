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
		/*
		int height = canv.getHeight();
		int width  = canv.getWidth();
		*/
		
		int height = 400;
		int width = 400;
		int mid = (height + width) / 4;
		int oSize = 72;
		int mSize = (int) (.65 * oSize);
		int cSize = (int) (.30 * oSize);

		GOval outer = new GOval(mid, mid, oSize, oSize);
		GOval middle = new GOval(mid + oSize, mid + oSize, mSize, mSize);
		GOval center = new GOval(mid, mid, cSize, cSize);

		outer.setColor(Color.RED);
		middle.setColor(Color.WHITE);
		center.setColor(Color.RED);

		outer.setFillColor(Color.RED);
		middle.setFillColor(Color.WHITE);
		center.setFillColor(Color.RED);
		
		outer.setFilled(true);
		middle.setFilled(true);
		center.setFilled(true);
/*
		add(outer.setColor(Color.RED));
		add(middle.setColor(Color.RED));
		add(center.setColor(Color.RED));
*/
		add(outer);
		add(middle);
		add(center);
		return;
	}
}
