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
	private static final double Y_VEL = 3.0;
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* Add the mouse listeners for the paddle to move*/
		addMouseListeners();
		/* Add all the rows of bricks to the world.*/
		setUpBricks();

		/*
		 * Make the paddle and then apply the fixPaddle()
		 * function to fill it in and add it to the middle
		 * of the board. */
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		fixPaddle(paddle);

		/* Begin gameplay; you have three lives/turns, after
		 * which, gameplay ends. */
		for (int lives = 0; lives < NTURNS; lives++) {
			ball = makeBall();
			add(ball);
			play();
		}

		gameOver();
		/* 'label' code below is purely for testing and
		 * should be removed after the game is complete. */
		label = new GLabel("Mouse pos: ");
		label.setFont("Times New Roman-36");
		//add(label, 0, 250);

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

			brick.setFilled(rgen.nextBoolean(1.0));
			/* Add a row of bricks. */
			add(brick , xPos + i*BRICK_WIDTH + (i+1)*BRICK_SEP, yPos);
		}

		return;
	}

	private void fixPaddle(GRect p) {

		/* Make the paddle and add to the game. */
		//GRect paddle1 = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		p.setFillColor(Color.BLACK);
		p.setFilled(true);

		/* Add paddle to game board. */
		add(p, WIDTH / 2, HEIGHT - PADDLE_Y_OFFSET);

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

		/* label code below is for testing only, and should be removed
		 * after the game is complete. */
		//label.setLabel("Mouse: (" + e.getX() + ", " + e.getY() + ")");
		return;
	}

	public void mouseClicked(MouseEvent e) {

		clickWait = new GLabel("Mouse click.");
		clickWait.setFont("Times New Roman-36");
		add(clickWait, 0, 650);

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

		waitForClick();

		vx = rgen.nextDouble(1.0, 3.0);
		vy = Y_VEL;
		if (rgen.nextBoolean(0.5)) {
			vx = -vx;
		}

		while(!checkCollision()) {
			ball.move(vx, vy);
			pause(25);
		}
		removeBall(ball);
		return;
	}

	private boolean checkCollision() {

		collided = checkWallCollisions();
		collided = checkPaddleCollision();
		collided = checkBrickCollision();

		return collided;
	}

	private boolean checkWallCollisions() {

		/* Check if ball hits bottom wall. Eventually
		 * this will need to return true to signify the
		 * end of a turn. */
		if (ball.getY() - BALL_RADIUS >= HEIGHT) {
			vy = -vy;
			return true;
		}
		/* Check if ball hits top of wall. */
		if (ball.getY() <= 0) {
			vy = -vy;
			return false;
		}
		/* Check if ball hits left edge. */
		if (ball.getX() <= 0) {
			vx = -vx;
			return false;
		}
		/* Check if ball hits right edge. */
		if (ball.getX() + BALL_RADIUS >= WIDTH) {
			vx = -vx;
			return false;
		}

		return false;
	}

	private boolean checkPaddleCollision() {
		double ballX = ball.getX();
		double ballY = ball.getY();
		double padLeft = paddle.getX();
		double padRight = padLeft + PADDLE_WIDTH;

		boolean withinX = (ballX >= padLeft && ballX <= padRight);
		boolean withinY = (ballY <= HEIGHT - PADDLE_Y_OFFSET && ballY >= HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);

		if (withinX && withinY) {
			vy = -vy;
			vx = rgen.nextDouble(0.0, vy);
			bounceNoise.play();
			withinX = false;
			withinY = false;
			return false;
		}

		return false;
	}

	private boolean checkBrickCollision() {
		/* First, find the brick that the ball collides with.*/
		crasher = getCollidingBrick();

		/* Next, determine what to do with that brick.*/
		if( crasher == null ) {return false;}
		else { remove(crasher); }

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
		remove(b);
		return;
	}

	private void gameOver() {
		gameOver = new GLabel("Game Over");
		gameOver.setFont("Times New Roman-24");
		add(gameOver, WIDTH / 2, HEIGHT / 2);
		waitForClick();
		remove(gameOver);

		return;
	}

	/* Private (game) instance variables for the paddle, and brick positioning
	 * and station-keeping. */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private static GRect paddle;
	private static GOval ball;
	private static double vx, vy;
	private static GLabel label;
	private static GLabel clickWait;
	private static GLabel gameOver;
	private static boolean collided = false;
	private static GObject crasher;
	private static int xPos = 0;
	private static int yPos = BRICK_Y_OFFSET;
	private AudioClip bounceNoise = MediaTools.loadAudioClip("bounce.au");
}
