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

		/* Playing an interactive game*/
		do {
			initializeGame();
			playGame();
		} while(playAgain());
		
		canvas.displayAlphabet("Thanks for Playing!!! Bye Bye!");
		
		return;
	}

	private void welcomeToHangman() {
		//Show welcome message.
		println("Welcome to Hangman!\nThe rules are just what you think!");

		//Create new lexicon from other class.
		wordList = new HangmanLexicon();

		return;
	}
	
	//This whole function is test code...should be replaced with a user choice.
	private boolean playAgain() {
		this.print("Would you like to play again? Enter 'N' or 'n' to quit: ");
		String playAgain = this.readLine();
		
		if (playAgain.length() != 1) {
			return true;
		}
		if (playAgain.charAt(0) == 'N' || playAgain.charAt(0) == 'n') {
			return false;
		}
		else {
			return true;
		}
		
	}

	private void initializeGame() {
		//Reset the game canvas.
		canvas.reset();

		//Select a random word from the lexicon.
		secretWord = wordList.getWord((int) rgen.nextDouble(0, wordList.getWordCount()));

		//Create and initialize a character array of blanks that is the same length as the word.
		guessWord = new char[secretWord.length()];
		alphabet = new char[26];
		for (int i = 0; i<secretWord.length(); i++) {
			guessWord[i] = '-';
		}
		for (int i = 0; i<alphabet.length; i++) {
			alphabet[i] = (char) (65+i);
		}
		canvas.displayAlphabet(String.valueOf(alphabet));
		canvas.displayWord(String.valueOf(guessWord));

		return;
	}

	private void playGame() {
		int i = 0;

		//Continue to run the game while player has guesses left.
		while(i < numberGuesses) {
			guessesLeft(numberGuesses - i);
			println("Your word looks like this: " + String.valueOf(guessWord));

			//GOOD GUESSES: Get a new guess, and test it for validity.
			if(testGuess(getNewGuess())) {
				//Update canvas word
				canvas.displayAlphabet(String.valueOf(alphabet));
				canvas.displayWord(String.valueOf(guessWord));
				
				
				//Check for a win.
				String test = String.valueOf(guessWord);
				if(test.compareTo(secretWord) == 0) {
					gameOver("won");
					break;
				}
			}

			//BAD GUESSES
			else {
				//Update display with wrong guess, and draw body part.
				canvas.displayAlphabet(String.valueOf(alphabet));
				canvas.displayWord(String.valueOf(guessWord));
				canvas.noteIncorrectGuess(tempGuess);
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

	private void gameOver(String s) {
		println("You " + s + "!");
		println("The secret word was " + secretWord + "!\n");

		return;
	}

	private char getNewGuess() {
		char guess = '-';
		String line;

		//Get a guess from the player, and convert it to the capital version.
		print("Enter your guess: ");
		line = readLine();

		if(line.length() > 1) {
			println("Ooops, pick only one letter!");
		}

		else if (line.length() == 0) {
			println("Ooops, you need to give at least one letter!");
		}

		else {
			guess = convertGuess(line.charAt(0));
			tempGuess = guess;
		}
		println("Your guess: " + guess );

		return guess;
	}

	private boolean testGuess(char c) {
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
		
		//Loop through the alphabet and replace selected letters with '-'.
		for(int i = 0; i<alphabet.length; i++) {
			if (c == alphabet[i]) {
				alphabet[i] = '-';
			}
		}

		return letterTest;
	}

	private void guessesLeft(int i) {
		println("You have " + i + " guesses left.");

		return;
	}

	//This code will convert a lowercase or uppercase letter to uppercase.
	private char convertGuess(char g) {
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
		this.add(canvas);

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
	
	private int numberGuesses = 8;
	private HangmanLexicon wordList = null;
	private String secretWord = null;
	private char[] guessWord;
	private char[] alphabet;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private HangmanCanvas canvas;
	private char tempGuess = '-';
}
