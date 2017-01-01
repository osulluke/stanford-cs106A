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
    /*Display welcome message, and initialize new wordlist.*/
    welcomeToHangman();
		/* Part 1 - playing an interactive game
     */
     //Here is the main game loop.
    while(playAgain()) {
       initializeGame();
       playGame();
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

  public void initializeGame() {
    secretWord = wordList.getWord((int) rgen.nextDouble(0, wordList.getWordCount()));
    guessWord = new char[secretWord.length()];
    println("The secret word is " + secretWord);
    return;
  }

  public void playGame() {
	  int i = 0;
	  while(i < numberGuesses) {
		  guessesLeft(numberGuesses - i);
		  testGuess(getNewGuess());
		  i++;
	  }
  }

  public char getNewGuess() {
      char guess;

      print("Enter your guess: ");
	    guess = convertGuess(readLine().charAt(0));
	    println("You guessed " + guess );
      println("Your word looks like this: " + guessWord.toString());

      return guess;
  }

  public void testGuess(char c) {
	  for(int i = 0; i<secretWord.length(); i++) {
		  if (c == secretWord.charAt(i)) {
			  guessWord[i] = c;
		  }
	  }
      return;
  }

  public void guessesLeft(int i) {
	  println("You have " + i + " guesses left.");
	  return;
  }

  public boolean playAgain() {
    return rgen.nextBoolean(.93);
  }

  public char convertGuess(char g) {
	  if(g >= 'A' && g <= 'Z') {
		  return g;
	  }
	  else if (g >= 'a' && g <= 'z') {
		  g = (char) (g - 'a' + 'A');
	  }
	  else {
		  println("Ooops! Not a valid guess :/");
	  }
	  return g;
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
  private int numberGuesses = 8;
  private HangmanLexicon wordList = null;
  private String secretWord = null;
  private char[] guessWord;
  private RandomGenerator rgen = RandomGenerator.getInstance();
}
