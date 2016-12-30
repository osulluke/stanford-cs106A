import acm.program.*;

public class removeOccurrences extends ConsoleProgram {
	
	public void run() {
		
		println("Enter a string: ");
		String str = readLine();
		String temp = "";
		char ch = '0';
		
		while(temp.length() != 1) {
			println("Enter single character to remove: ");
			temp = readLine();
			
			if( temp.length() == 1 ) {
				ch = (char) temp.charAt(0);
			}
		}

		println(removeAllOccurrences(str, ch));
	}
	
	public String removeAllOccurrences(String str, char ch) {
		
		String newStr = "";
		
		for (int i = 0; i<str.length(); i++) {
			if(str.charAt(i) != ch) {
				newStr = newStr.concat(str.substring(i, i+1)); 
			}
		}
		
		return newStr;
	}
}