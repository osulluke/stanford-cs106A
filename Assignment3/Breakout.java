/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 *
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;
	
/** Paddle Dimensions */
	private static final int PADDLE_TOP = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
	private static final int PADDLE_BOTTOM = HEIGHT - PADDLE_Y_OFFSET;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	private static final int BALL_DIAMETER = 2*BALL_RADIUS;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/** Initial Y velocity (+ is downward) */
	private static final double Y_VEL = 5.0;
	
/** Constant that controls how much 'ENERGY' is lost by the ball */
	private static final double E_LOSS = .90;

/** Runs the Breakout program. */
	public void run() {
		/* Add the mouse listeners for the paddle to move*/
		this.addMouseListeners();
		
		/* Add all the rows of bricks to the world. Make the paddle and then apply the fixPaddle()
		 * function to fill it in and add it to the middle
		 * of the board.
		 * */
		this.setUpBricks();
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		this.fixPaddle(paddle);

		/* Begin game play; you have three lives/turns, after
		 * which, game play ends. 
		 * */
		for (int lives = 0; lives < NTURNS; lives++) {
			ball = makeBall();
			this.add(ball);
			this.play();
			if(numHits == youWon) {
				break;
			}
		}

		/* After your three lives/turns, it's game over. */
		this.gameOver();

		return;
	}

	private void setUpBricks() {
		/* Loop through each of the number of rows changing
		 * the color after every second row. */
		for (int j = 0; j < NBRICK_ROWS; j++) {
			yPos = yPos + (BRICK_HEIGHT) + BRICK_SEP;

			if (j < 2) {
				layBrickRow(Color.RED);
			}
			else if (j < 4) {
				layBrickRow(Color.ORANGE);
				}
			else if (j < 6) {
				layBrickRow(Color.YELLOW); ;
			}
			else if (j < 8) {
				layBrickRow(Color.GREEN); ;
			}
			else if (j < 10) {
				layBrickRow(Color.CYAN); ;
			}
		}

		return;
	}

	private void layBrickRow(Color c) {
		/* Add two rows of that color brick. */
		for (int i = 0; i < NBRICKS_PER_ROW; i++) {

			/* Make a new brick.*/
			GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			brick.setFillColor(c);
			brick.setColor(c);
			brick.setFilled(rgen.nextBoolean(1));
			
			/* Add a row of bricks. */
			add(brick , xPos + i*BRICK_WIDTH + (i+1)*BRICK_SEP, yPos);
		}

		return;
	}

	private void fixPaddle(GRect p) {
		/* Adjust the paddle properties and add to the game. */
		p.setFillColor(Color.BLACK);
		p.setFilled(true);

		/* Add paddle to game board. */
		this.add(p, WIDTH / 2, HEIGHT - PADDLE_Y_OFFSET);

		return;
	}

	public void mouseMoved(MouseEvent e) {
		/* Cascading 'if' statement makes sure to keep
		 * the paddle within the boundaries of the game board. */
		if (e.getX() < 0) {
			paddle.setLocation(0, HEIGHT - PADDLE_Y_OFFSET);
		}
		else if (e.getX() + PADDLE_WIDTH > WIDTH) {
			paddle.setLocation(WIDTH - PADDLE_WIDTH, HEIGHT - PADDLE_Y_OFFSET);
		}
		else {
			paddle.setLocation(e.getX(), HEIGHT - PADDLE_Y_OFFSET);
		}

		return;
	}

	public void mouseClicked(MouseEvent e) {

		return;
	}

	private GOval makeBall() {
		/* Make a new (black) ball and place it in the middle of the
		 * game board. */
		ball = new GOval(WIDTH / 2, HEIGHT / 2, BALL_RADIUS, BALL_RADIUS);
		ball.setFilled(true);
		ball.setFillColor(Color.BLACK);
		ball.sendToBack();

		return ball;
	}

	private void play() {
		/* Don't start the game until there is a click from the user.*/
		this.waitForClick();

		/* Initialize a new, random x velocity and send the ball downward. */
		vx = rgen.nextDouble(2.0, 4.0);
		vy = Y_VEL;
		
		/* Every time the ball starts, make it go left or right 50/50. */
		if (rgen.nextBoolean(0.5)) {
			vx = -vx;
		}

		/* Start the ball moving and check for collisions. If there is no 
		 * collision that returns 'true' continue to move the ball. */
		while(!checkCollision()) {
			/* Code to ensure ball won't fly through paddle
			 */
			boolean skipsPaddle = false;
			skipsPaddle = ball.getY() + ball.getHeight() + vy > PADDLE_BOTTOM && ball.getY() + vy > PADDLE_TOP;
			if (skipsPaddle && this.checkPaddleX(ball)) {
				vy = -vy;
				ball.move(vx, vy);
				skipsPaddle = false;
			}
			ball.move(vx, vy);
			this.pause(30);
			/* Add in the effect of gravity
			 */
			vy += .2;
			
			/**/
			if(numHits == youWon) {break;}
		}
		
		/* After a collision that returns true, remove pause momentarily, 
		 * and then remove the ball. */
		this.pause(1000);
		this.removeBall(ball);
		
		return;
	}

	private boolean checkCollision() {
		/* The ball can collide with basically three 
		 * different entities, the paddle, a brick, or a wall.
		 * Test for each of these cases. */
		collided = checkPaddleCollision();
		collided = checkBrickCollision();
		collided = checkWallCollisions();

		return collided;
	}

	private boolean checkWallCollisions() {
		/* Need to have a variable to tell if it hit or not. */
		boolean hit = false;
		
		/* Check if ball hits bottom wall. Eventually
		 * this will need to return true to signify the
		 * end of a turn. */
		if (ball.getY() + BALL_RADIUS >= HEIGHT) {
			hit = true; //Signal that the ball goes through the bottom and player loses a turn.
			return hit;
		}
		
		/* Check if ball hits top of wall. */
		if (ball.getY() <= 0) {
			vy = -vy * E_LOSS;
			ball.move(vx, vy + ball.getHeight() / 2);
			return hit;
		}
		
		/* Check if ball hits left edge. */
		if (ball.getX() <= 0) {
			vx = -vx * E_LOSS;
			ball.move(vx + ball.getWidth() / 2, vy);
			return hit;
		}
		
		/* Check if ball hits right edge. */
		if (ball.getX() + BALL_RADIUS >= WIDTH) {
			vx = -vx * E_LOSS;
			ball.move(vx - ball.getWidth() / 2, vy);
			return hit;
		}
		
		return hit;
	}

	private boolean checkPaddleX(GOval b) {
		b = ball;
		boolean withinX;
		
		/* Get the boundaries of the ball. */
		double ballLeft = b.getX();
		double ballRight = ballLeft + b.getWidth();
		//double ballBottom = b.getY();
		//double ballTop = ballBottom + b.getHeight();
		
		/* Get the left and right boundaries of the paddle. */
		double padLeft = paddle.getX();
		double padRight = padLeft + PADDLE_WIDTH;
		
		withinX = (ballRight >= padLeft && ballLeft <= padRight);
		
		return withinX;
	}
	
	private boolean checkPaddleY(GOval b) {
		b = ball;
		boolean withinY;
		/* Get the boundaries of the ball. */
		//double ballLeft = ball.getX();
		//double ballRight = ballLeft + ball.getWidth();
		double ballBottom = ball.getY();
		double ballTop = ballBottom + ball.getHeight();
		
		/* Get the left and right boundaries of the paddle. */
		//double padLeft = paddle.getX();
		//double padRight = padLeft + PADDLE_WIDTH;
		
		withinY = (ballBottom <= HEIGHT - PADDLE_Y_OFFSET && ballBottom >= HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT || ballTop <= HEIGHT - PADDLE_Y_OFFSET && ballTop >= HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		
		return withinY;
	}
	
	private boolean checkPaddleCollision() {
		boolean withinX = this.checkPaddleX(ball);
		boolean withinY = this.checkPaddleY(ball);
		 
		/* If the ball is touching the paddle in both the X and Y dimension
		 * then reverse it's y velocity and give it a new random x velocity. 
		 * Here is also where the noise is supposed to play, but it's not for 
		 * some unknown reason. */
		if (withinX && withinY) {
			vy = -vy;
			vx = rgen.nextDouble(-vy, vy);
			//bounceNoise.play();
			withinX = false;
			withinY = false;
			return false;
		}

		return false;
	}

	private boolean checkBrickCollision() {
		/* First, find the brick that the ball collides with.*/
		crasher = getCollidingBrick();

		/* Next, determine what to do with the object.*/
		if( crasher == null ) {return false;}
		if( crasher == paddle ) {return false;}
		
		/* Not a label, paddle, or otherwise (i.e. it's a brick?). 
		 * This is where the real behavior of the 'game' lives...
		 * i.e. it's where what happens when the ball hits a brick
		 * lives. */
		else {
			GRect fullBrick = (GRect) crasher;
			if ( fullBrick.isFilled() ) {
				fullBrick.setFilled(false);
				vx = rgen.nextDouble(0.1, E_LOSS * vy);
				vy = -vy;
			}
			else if( !fullBrick.isFilled() ) {
				remove(crasher);
				vx = rgen.nextDouble(0.1, E_LOSS * vy);
				vy = -vy;
				
				/* Increment the 'winner' tracker. */
				numHits++;
			} 
		}

		return false;
	}

	private GObject getCollidingBrick() {
		/* This set of variables represent the different locations on each
		 * 'corner' of the ball. */
		GPoint topLeft = new GPoint(ball.getLocation());
		GPoint topRight = new GPoint(topLeft.getX() + BALL_DIAMETER,topLeft.getY());
		GPoint bottomLeft = new GPoint(topLeft.getX(), topLeft.getY() + BALL_DIAMETER);
		GPoint bottomRight = new GPoint(topLeft.getX() + BALL_DIAMETER, topLeft.getY() + BALL_DIAMETER);

		/* Local variable to represent the smacked brick. */
		GObject smackedBrick;

		/* Logic to return an element that coincides with the brick, and then
		 * return it if there is a collision. */
		if ( (smackedBrick = getElementAt(topLeft)) != null) {return smackedBrick;}
		else if ( (smackedBrick = getElementAt(topRight)) != null) {return smackedBrick;}
		else if ( (smackedBrick = getElementAt(bottomLeft)) != null) {return smackedBrick;}
		else if ( (smackedBrick = getElementAt(bottomRight)) != null) {return smackedBrick;}

		/* If no collision, return null. */
		return null;
	}

	private void removeBall(GOval b) {
		/* Remove the ball from the board. */
		remove(b);
		
		return;
	}

	private void gameOver() {
		/* Check to see if you won and make and add it to the canvas. */
		if(youWon == numHits) {
			vx = vy = 0;
			gameOver = new GLabel("You Won!!");
			gameOver.setFont("Times New Roman-50");
			gameOver.setColor(Color.GREEN);
			add(gameOver, ( WIDTH - gameOver.getWidth() ) / 2, HEIGHT / 2);
		}
		else {
			gameOver = new GLabel("Game Over");
			gameOver.setFont("Times New Roman-50");
			gameOver.setColor(Color.RED);
			add(gameOver, ( WIDTH - gameOver.getWidth() ) / 2, HEIGHT / 2);
		}

		/* After a final click exit the game. */
		waitForClick();
		exit();

		return;
	}

	/* Private (game) instance variables for the paddle, and brick positioning
	 * and station-keeping. */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private static GRect paddle;
	private static GOval ball;
	private static double vx, vy;
	private static GLabel gameOver;
	private static boolean collided = false;
	private static GObject crasher;
	private static int xPos = 0;
	private static int yPos = BRICK_Y_OFFSET;
	private static int youWon = NBRICKS_PER_ROW * NBRICK_ROWS;
	private static int numHits = 0;
	AudioClip bounceNoise = MediaTools.loadAudioClip("bounce.au");
}
