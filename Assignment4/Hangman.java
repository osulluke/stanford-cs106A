/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.*;

public class Hangman extends ConsoleProgram {
	
	public void run() {

		/* Display welcome message, and initialize new wordlist*/
		welcomeToHangman();
		
		/* Part 1 - playing an interactive game*/
		while(playAgain()) {
			initializeGame();
			playGame();
		}

		/* Part 2 - implementing the graphics*/

		/* Part 3 - reading in a new list of many words*/
		
		return;
	}

	public void welcomeToHangman() {
		//Show welcome message.
		println("Welcome to Hangman!\nThe rules are just what you think!");

		//Create new lexicon from other class.
		wordList = new HangmanLexicon();

		//Test code that makes sure the other class is working.
		testLex();

		return;
	}

	public void initializeGame() {
		
		//Select a random word from the lexicon.
		secretWord = wordList.getWord((int) rgen.nextDouble(0, wordList.getWordCount()));
		
		//Create and initialize a character array of blanks that is the same length as the word.
		guessWord = new char[secretWord.length()];
		for (int i = 0; i<secretWord.length(); i++) {
			guessWord[i] = '-';
		}
		//Test code to show what the secret word is...
		println("The secret word is " + secretWord);
		
		return;
	}

	public void playGame() {
		int i = 0;
		
		//Continue to run the game while player has guesses left.
		while(i < numberGuesses) {
			guessesLeft(numberGuesses - i);
			println("Your word looks like this: " + String.valueOf(guessWord));

			//Get a new guess, and test it for validity.
			if(testGuess(getNewGuess())) {
				
				//Check for a win.
				String test = String.valueOf(guessWord);
				if(test.compareTo(secretWord) == 0) {
					gameOver("won");
					break;
				}
			}

			else {
				//Wrong guess, increment guess counter, and check for a loss.
				i++;
				if(i == numberGuesses) {
					gameOver("lost");
					break;
				}
			}
		}
		
		return;
	}

	public void gameOver(String s) {
		println("You " + s + "!");
		println("The secret word was " + secretWord + "!\n");
		
		return;
	}

	public char getNewGuess() {
		char guess = '-';
		String line;
		
		//Get a guess from the player, and convert it to the capital version.
		print("Enter your guess: ");
		line = readLine();
		
		if(line.length() > 1) {
			println("Ooops, pick only one letter!");
		}

		else {
			guess = convertGuess(line.charAt(0));
		}
		println("Your guess: " + guess );

		return guess;
	}

	public boolean testGuess(char c) {
		boolean letterTest = false;

		if (c == '-') {
			letterTest = true;
		}
		//Loop through each letter of the word, compare to your guess, and update accordingly.
		for(int i = 0; i<secretWord.length(); i++) {
			if (c == secretWord.charAt(i)) {
				guessWord[i] = c;
				//Return true if it's a good guess.
				letterTest = true;
			}
		}
		
		return letterTest;
	}

	public void guessesLeft(int i) {
		println("You have " + i + " guesses left.");
		
		return;
	}

	//This whole function is test code...should be replaced with a user choice.
	public boolean playAgain() {
		return rgen.nextBoolean(.93);
	}

	//This code will convert a lowercase or uppercase letter to uppercase.
	public char convertGuess(char g) {
		if(g >= 'A' && g <= 'Z' || g == '-') {
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

	//This code initializes the hangman canvas.
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
		
		return;
	}
	
	//This is test code, and will eventually be deleted.
	public void testLex() {

		println("There are " + wordList.getWordCount() + " words in the list, and are as follows: ");
		for(int i=0; i<wordList.getWordCount(); i++) {
			println(wordList.getWord(i));
		}
		
		return;
	}

	/* Private (game) instance variables:
    Includes:
    - numberGuesses: the default number of guesses the player will start with.
    - wordList: contains all words for the hangman game (not yet needed).
    - secretWord: the word that the program will select randomly.
    - guessWord: the word that the player will fill with guesses.
    - rgen: a random generator.
    - canvas: the graphical hangman display canvas.
	 */
	//private static String[] wordList = new String[10];
	private int numberGuesses = 8;
	private HangmanLexicon wordList = null;
	private String secretWord = null;
	private char[] guessWord;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private HangmanCanvas canvas;
}
