/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

    public void run() {
      /*Display welcome message, and initialize new wordlist.
      */
      welcomeToHangman();
		/* Part 1 - playing an interactive game
     */
     //Here is the main game loop.
     while(playAgain()) {
       initialzeGame();

     }

    /* Part 2 - implementing the graphics
     */

    /* Part 3 - reading in a new list of many words
    */
	}

  public void welcomeToHangman() {
    println("Welcome to Hangman!\nThe rules are just what you think!");
    wordList = new HangmanLexicon();
    testLex();
    return;
  }

  public void initialzeGame() {
    secretWord = wordList.getWord((int) rgen.nextDouble(0, wordList.getWordCount()));
    println("The secretWord is " + secretWord);
  }

  public char getNewGuess() {

    return 'a';
  }

  public boolean playAgain() {
    return rgen.nextBoolean(.93);
  }

  public void testLex() {

	  println("There are " + wordList.getWordCount() + " words in the list, and are as follows: ");
	  for(int i=0; i<wordList.getWordCount(); i++) {
		  println(wordList.getWord(i));
	  }
	  return;
  }
  /* Private (game) instance variables:
    Includes:
    - wordList: contains all words for the hangman game (not yet needed).
    */
  //private static String[] wordList = new String[10];
  private HangmanLexicon wordList = null;
  private String secretWord = null;
  private RandomGenerator rgen = RandomGenerator.getInstance();
}
