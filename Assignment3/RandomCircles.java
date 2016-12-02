import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class RandomCircles extends GraphicsProgram {
	int numCircles = 10;
	GOval[] circle = new GOval[numCircles];
	int circleSize = 0;
	int x = 0;
	int y = 0;

	public void run() {

  		for(int i = 0; i < numCircles; i++) {

			circleSize = rgen.nextInt(10, 100);

			circle[i] = new GOval(circleSize, circleSize);
			circle[i].setFillColor(rgen.nextColor());
			circle[i].setFilled(true);
			
			x = rgen.nextInt(getWidth());
			y = rgen.nextInt(getHeight());
			
			if (x + circleSize > getWidth()) {
				x = x - 1*circleSize;
			}
			
			if (x - circleSize < 0) {
				x = x + (circleSize);
			}
			
			if (y + circleSize > getHeight()) {
				y = y - 1*circleSize;
			}
			
			if (y - circleSize < 0) {
				y = y + (circleSize);
			}
			
			add(circle[i], x, y);
		}
  		
  		while(true) {
  			;
  		}
	}
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
