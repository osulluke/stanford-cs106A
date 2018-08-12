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
		numParts = 0;
		badGuesses = new char[8];
		removeAll();
		addScaffold();
		resetGuesses();
		addLabels();
		
		return;
	}
	
	public void resetGuesses() {
		for(int i = 0; i < badGuesses.length; i++) {
			badGuesses[i] = '-';
		}
	}
	
	public void addLabels() {
		String f = "SansSerif-bold-16";
		String s = "SansSerif-bold-24";
		
		label.setFont(s);
		badLabel.setFont(s);
		alphaLabel.setFont(f);
		
		add(label);
		add(badLabel);
		badLabel.setLabel(String.valueOf(badGuesses));
		add(alphaLabel);
		
		return;
	}

	/**
	 * Updates the word on the screen to correspond to the current
	 * state of the game.  The argument string shows what letters have
	 * been guessed so far; unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		label.setLabel(word);

		return;
	}
	
	public void displayAlphabet(String word) {
		alphaLabel.setLabel(word);
		
		return;
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the
	 * user.  Calling this method causes the next body part to appear
	 * on the scaffold and adds the letter to the list of incorrect
	 * guesses that appears at the bottom of the window.
	 */
	public void noteIncorrectGuess(char letter) {

		addBodyPart(numParts);
		badGuesses[numParts] = letter;
		badLabel.setLabel(String.valueOf(badGuesses));
		numParts++;

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

	private void addHands() {
		GLine rHand;
		GLine lHand;

		rHand = new GLine(xCoord + BEAM_LENGTH - UPPER_ARM_LENGTH / 2, 
				yCoord + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
				xCoord + BEAM_LENGTH - UPPER_ARM_LENGTH / 2,
				yCoord + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);

		lHand = new GLine(xCoord + BEAM_LENGTH - UPPER_ARM_LENGTH / 2 + UPPER_ARM_LENGTH, 
				yCoord + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
				xCoord + BEAM_LENGTH - UPPER_ARM_LENGTH / 2 + UPPER_ARM_LENGTH,
				yCoord + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);

		add(rHand);
		add(lHand);

		return;
	}

	private void addHips() {
		GLine hips = new GLine(xCoord + BEAM_LENGTH - HIP_WIDTH / 2,
				yCoord + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH,
				xCoord + BEAM_LENGTH + HIP_WIDTH / 2,
				yCoord + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH);
		add(hips);

		return;
	}

	private void addRleg() {
		GLine rLeg = new GLine(xCoord + BEAM_LENGTH - HIP_WIDTH / 2,
				yCoord + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH,
				xCoord + BEAM_LENGTH - HIP_WIDTH / 2,
				yCoord + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		add(rLeg);

		return;
	}

	private void addLleg() {
		GLine lLeg = new GLine(xCoord + BEAM_LENGTH + HIP_WIDTH / 2,
				yCoord + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH,
				xCoord + BEAM_LENGTH + HIP_WIDTH / 2,
				yCoord + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		add(lLeg);

		return;
	}

	private void addFeet() {
		GLine rFoot;
		GLine lFoot;

		rFoot = new GLine(xCoord + BEAM_LENGTH - HIP_WIDTH / 2,
				yCoord + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
				xCoord + BEAM_LENGTH - HIP_WIDTH / 2 - FOOT_LENGTH,
				yCoord + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);

		lFoot = new GLine(xCoord + BEAM_LENGTH + HIP_WIDTH / 2,
				yCoord + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
				xCoord + BEAM_LENGTH + HIP_WIDTH / 2 + FOOT_LENGTH,
				yCoord + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);

		add(rFoot);
		add(lFoot);

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
		else if (i == 3) {
			addHands();
		}
		else if (i == 4) {
			addHips();
		}
		else if (i == 5) {
			addRleg();
		}
		else if (i == 6) {
			addLleg();
		}
		else {
			addFeet();
		}

		return;
	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 42;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 18;

	/*My constants */
	private static GLine vertBeam;
	private static GLine horzBeam;
	private static GLine rope;
	private static int xCoord = 50;
	private static int yCoord = 15;
	private static int numParts = 0;
	private static int xLabel = xCoord;
	private static int yLabel = yCoord + SCAFFOLD_HEIGHT + 20;
	
	/*Make labels for the game*/
	private static GLabel label = new GLabel("", xLabel, yLabel);
	private static GLabel badLabel = new GLabel("", xLabel, yLabel + label.getHeight() + 50);
	private static GLabel alphaLabel = new GLabel("", xLabel, yLabel + label.getHeight() + badLabel.getHeight() + 80);
	
	/*Make an array to track the incorrect guesses*/
	private static char[] badGuesses;
}
