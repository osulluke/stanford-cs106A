import acm.program.*;
import acm.graphics.*;
import java.awt.*;


public class HelloWorld extends GraphicsProgram {
	public void run() {
		add (new GLabel ("Hello, world!", 100, 75));
		add (new GOval (50,50,30,30));
		GLabel label = new GLabel("hello, world", 200, 150);
		Font myFont = new Font("SansSerif", Font.BOLD, 36);
		label.setFont(myFont);
		label.setColor(Color.RED);
		add(label);
	}
}
