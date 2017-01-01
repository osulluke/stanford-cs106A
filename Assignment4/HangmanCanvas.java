/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		removeAll();
		addScaffold();
		
		return;
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		
		addBodyPart(numParts++);
		
		return;
	}

	private void addScaffold() {
		vertBeam = new GLine(xCoord, yCoord, xCoord, yCoord + SCAFFOLD_HEIGHT);
		horzBeam = new GLine(xCoord, yCoord, xCoord + BEAM_LENGTH, yCoord);
		rope = new GLine(xCoord + BEAM_LENGTH, yCoord, xCoord + BEAM_LENGTH, yCoord + ROPE_LENGTH);
		
		add(vertBeam);
		add(horzBeam);
		add(rope);
		
		return;
	}
	
	private void addHead() {
		GOval head = new GOval(xCoord + BEAM_LENGTH - HEAD_RADIUS / 2, 
				yCoord + ROPE_LENGTH, HEAD_RADIUS, HEAD_RADIUS);
		add(head);
		
		return;
	}
	
	private void addBody() {
		GLine body = new GLine(xCoord + BEAM_LENGTH, 
				yCoord + ROPE_LENGTH + HEAD_RADIUS, 
				xCoord + BEAM_LENGTH, 
				yCoord + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH);
		add(body);
		
		return;
	}
	
	private void addArms() {
		GLine arms = new GLine(xCoord + BEAM_LENGTH - UPPER_ARM_LENGTH / 2, 
				yCoord + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
				xCoord + BEAM_LENGTH - UPPER_ARM_LENGTH / 2 + UPPER_ARM_LENGTH,
				yCoord + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		add(arms);
		
		return;
	}
	
	private void addBodyPart(int i) {
		if (i == 0) {
			addHead();
		}
		else if (i == 1) {
			addBody();
		}
		else if (i == 2) {
			addArms();
		}
		
		return;
	}
	
	/*My constants */
	private static GLine vertBeam;
	private static GLine horzBeam;
	private static GLine rope;
	private static int xCoord = 50;
	private static int yCoord = 15;
	private static int numParts = 0;
	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
