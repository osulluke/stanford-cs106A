import acm.util.*;
import acm.io.*;
import acm.program.*;
import java.io.*;

public class WordCount extends ConsoleProgram {

	public void run() {

		try {
			BufferedReader rd = new BufferedReader(new FileReader("lear.txt"));

			while(rd.readLine() != null) {
				;
			}
		}
		catch(IOException ex) {
			;
		}

		return;
	}

	public int countChars() {
		int numChars = 0;

		return numChars;
	}

	public int countWords() {
		int numWords = 0;

		return numWords;
	}

	public int countLines() {

		return 1;
	}

	public int characterCount = 0;
	public int wordCount = 0;
	public int lineCount = 0;
	private BufferedReader rd;
}
