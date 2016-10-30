import stanford.karel.*;

public class Problem3 extends SuperKarel {

	public void run() {
		placeBrick();

		switch ( exploreWorld() ) {
		case 1:
			placeHorizontalBeepers();
			break;
		case 2:
			placeVerticalBeepers();
			break;
		case 3: 
			layDownBeepers();
			break;
		}
	}

	private void layDownBeepers() {
		while (!reachedEndOfWorld()) {

			if (frontIsClear()) {
				move();
				//setDirection(1);
				if (frontIsClear()){
					move();
					placeBrick();
				}
				else {
					setDirection(0);
					if (frontIsClear()) {
						move();
						placeBrick();
						setDirection(3);
					}
				}
			}

			if(frontIsBlocked()) {
				setDirection(0);
				if (frontIsClear()) {
					move();
					setDirection(1);
					setDirection(getDirection() + 2 % 4);
				}
				else{
					turnLeft();
					if (frontIsClear()) {
						move();
					}
				}
			}

			if (frontIsBlocked() && facingWest()) {
				setDirection(0);
				if(!frontIsBlocked()) {
					move();
				}
				turnRight();
			}
		}
		return;
	}

	private void placeHorizontalBeepers() {
		setDirection(1);
		while(!frontIsBlocked()){
			if(!frontIsBlocked()) {
				move();
			}
			if(!frontIsBlocked()) {
				move();
				placeBrick();
			}
		}
	}

	private void placeVerticalBeepers() {
		setDirection(0);
		while(!frontIsBlocked()){
			if(!frontIsBlocked()) {
				move();
			}
			if(!frontIsBlocked()) {
				move();
				placeBrick();
			}
		}
	}

	private boolean reachedEndOfWorld() {
		if (facingNorth() && frontIsBlocked()) {
			return true;
		}
		return false;
	}

	private void placeBrick() {
		setBeepersInBag(1);
		putBeeper();
	}

	private int exploreWorld() {
		//Code for vertical world:
		if (facingEast() && frontIsBlocked()) {
			return 2;
		}

		//Code for horizontal world:
		turnLeft();
		if (facingNorth() && frontIsBlocked()) {
			return 1;
		}


		//Code for normal square world:
		turnRight();
		return 3;
	}

}