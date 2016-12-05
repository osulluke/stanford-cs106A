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

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		setUpBricks();
		addPaddle();
		
		for (int lives = 0; lives < NTURNS; lives++) {
			;
		}
		
		return;
	}
	
	private void setUpBricks() {
		
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
		/*
		 * Add two rows of that color brick.
		 */
		for (int i = 0; i < NBRICKS_PER_ROW; i++) {
			
			/* Make a new brick.*/
			GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			brick.setFillColor(c);
			brick.setColor(c);
			brick.setFilled(true);
			/* Add a row of bricks*/
			add(brick , xPos + i*BRICK_WIDTH + (i+1)*BRICK_SEP, yPos);
		}
		
		return;
	}
	
	private void addPaddle() {
		
		/* Add the mouse listeners for the paddle to move*/
		addMouseListeners();
		
		/* Make the paddle and add to the game. */
		GRect paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFillColor(Color.BLACK);
		paddle.setFilled(true);
		
		/* Add paddle to game board. */
		add(paddle, 45, HEIGHT - PADDLE_Y_OFFSET);
		
		return;
	}

	
	private static int xPos = 0;
	private static int yPos = BRICK_Y_OFFSET;
}
