/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.*;

public class HangmanLexicon {
	/** Single constructor to finish the program. */
	public HangmanLexicon() {

		try {
			rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));			
		}
		catch(IOException ex) {
			throw new ErrorException(ex);//println("File not found!");
		}

		try {
			while(true) {
				tempWord = rd.readLine();
				if (tempWord == null) break;
				words.add(tempWord);
			}
			rd.close();
		}
		catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return words.size();
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return words.get(index);
	}

	private int numWords = 0;
	private BufferedReader rd;
	private String tempWord;
	ArrayList<String> words = new ArrayList<String>();
}
