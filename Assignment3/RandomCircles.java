import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class RandomCircles extends GraphicsProgram {
	int numCircles = 20;
	GOval[] circle = new GOval[numCircles];
	int circleSize = 0;
	int x = 0;
	int y = 0;

	public void run() {

  		for(int i = 0; i < numCircles; i++) {

			circleSize = rgen.nextInt(10, 100);

			circle[i] = new GOval(circleSize, circleSize);
			circle[i].setFillColor(rgen.nextColor());
			circle[i].setColor(circle[i].getFillColor());
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

  			for (int i = 0; i < numCircles; i++) {
  				circle[i].setColor(rgen.nextColor());
  				circle[i].setFillColor(circle[i].getColor());
  				pause(rgen.nextDouble(150, 400));
  				
  				/*
  				 * Choose the positions within the bounds of the array
  				 */

  				
  				int posX = rgen.nextInt(getWidth());
  				int posY = rgen.nextInt(getHeight());
  				
  				if (circle[i].getX() < posX + circle[i].getWidth()) {
  					posX = posX - (int) circle[i].getWidth();
  				}
  				else if (posX - circle[i].getX() > circle[i].getWidth()) {
  					posX = posX + (int) circle[i].getWidth();
  				}
  				
  				if (circle[i].getY() > posY + circle[i].getHeight()) {
  					posY = posY - (int) 1.0 * (int) circle[i].getHeight();
  				}
  				else if (posY - circle[i].getY() > circle[i].getHeight()) {
  					posY = posY + (int) 1.0 * (int) circle[i].getHeight();
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
  				
  				if (circle[i].getX() < posX + circle[i].getWidth()) {
  					posX = posX - (int) circle[i].getWidth();
  				}
  				else if (posX - circle[i].getX() > circle[i].getWidth()) {
  					posX = posX + (int) circle[i].getWidth();
  				}
  				
  				if (circle[i].getY() > posY + circle[i].getHeight()) {
  					posY = posY - (int) circle[i].getHeight();
  				}
  				else if (posY - circle[i].getY() > circle[i].getHeight()) {
  					posY = posY + (int) circle[i].getHeight();
  				}
  				/*
  				 * Place the circle in the corrected area.
  				 */
  				circle[i].setLocation(posX, posY);
  				
  			}
			pause(rgen.nextDouble(150, 1000));
  		}
	}
	
	private int fixX (int x) {
		
	}

	private RandomGenerator rgen = RandomGenerator.getInstance();
}
