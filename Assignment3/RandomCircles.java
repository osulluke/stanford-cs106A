import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class RandomCircles extends GraphicsProgram {
	int numCircles = 200;
	GOval[] circle = new GOval[numCircles];
	int circleSize = 0;
	int x = 0;
	int y = 0;
	
	int lPause = 0;
	int uPause = 1;

	public void run() {

  		for(int i = 0; i < numCircles; i++) {

			circleSize = rgen.nextInt(90, 100);

			circle[i] = new GOval(circleSize, circleSize);
			circle[i].setFillColor(rgen.nextColor());
			circle[i].setColor(circle[i].getFillColor());
			circle[i].setFilled(true);
			
			x = rgen.nextInt(getWidth());
			y = rgen.nextInt(getHeight());
			
			if (x + circleSize > getWidth()) {
				x = x - circleSize;
			}
			
			if (x - circleSize < 0) {
				x = x + circleSize;
			}
			
			if (y + circleSize > getHeight()) {
				y = y - circleSize;
			}
			
			if (y - circleSize < 0) {
				y = y + circleSize;
			}
			
			add(circle[i], x, y);
		}
  		
  		while(true) {

  			for (int i = 0; i < numCircles; i++) {
  				circle[i].setColor(rgen.nextColor());
  				circle[i].setFillColor(circle[i].getColor());
  				pause(rgen.nextDouble(lPause, uPause));
  				
  				/*
  				 * Choose the positions within the bounds of the array
  				 */

  				int posX = rgen.nextInt(getWidth());
  				int posY = rgen.nextInt(getHeight());
  				double rBound = getWidth() - circle[i].getWidth();
  				double lBound = 0 + circle[i].getWidth();
  				double uBound = getHeight() - circle[i].getHeight();
  				double bBound = 0 + circle[i].getHeight();
  				
  				//X boundary
  				if (posX > rBound || posX < lBound) {
  					posX = rgen.nextInt(getWidth());
  					continue;
  				}
  				
  				//Y boundary
  				if (posY > uBound || posY < bBound) {
  					posY = rgen.nextInt(getHeight());
  					continue;
  				}
  				/*
  				 * Place the circle in the corrected area.
  				 */
   				circle[i].setLocation(posX, posY);
  			}
  			
  			for (int i = 0; i < numCircles; i++) {
  				circle[i].setColor(rgen.nextColor());
  				circle[i].setFillColor(circle[i].getColor());
  				/*
  				 * Choose the positions within the bounds of the array
  				 */
  	  			int posX = 0;
  	  			int posY = 0;
  				
  				posX = rgen.nextInt(getWidth());
  				posY = rgen.nextInt(getHeight());
  				double rBound = getWidth() - circle[i].getWidth();
  				double lBound = 0 + circle[i].getWidth();
  				double uBound = getHeight() - circle[i].getHeight();
  				double bBound = 0 + circle[i].getHeight();
  				
  				//X boundary
  				if (posX > rBound || posX < lBound) {
  					posX = rgen.nextInt(getWidth());
  					continue;
  				}
  				
  				//Y boundary
  				if (posY > uBound || posY < bBound) {
  					posY = rgen.nextInt(getHeight());
  					continue;
  				}
  				/*
  				 * Place the circle in the corrected area.
  				 */
  				circle[i].setLocation(posX, posY);
  				
  			}
			pause(rgen.nextDouble(lPause, uPause));
  		}
	}
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
