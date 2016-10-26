import stanford.karel.*;

public class OurKarelProgram extends Karel {
	public void run() {
		turnLeft();
		move();
		turnRight();
		move();
		turnLeft();
		move();
		turnRight();
		move();
		for(int i = 0; i<5; i++) {
			move();
		}

		move();
		turnLeft();
		for (int i = 0; i<5; i++){
			move();
		}
		move();
		turnAround();
		move();
		aboutFace();
		moveToWall();
		moveToWall();
	}
	
	private void turnRight() {
		turnLeft();
		turnLeft();
		turnLeft();
	}
	
	private void turnAround() {
		turnRight();
		turnRight();
	}
	
	private void aboutFace() {

		turnAround();
		move();
		turnAround();
	}

	private void moveToWall() {
		while(frontIsClear()) {
			move();
		}
		turnAround();
	}
}
