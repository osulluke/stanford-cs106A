//import acm.util.*;
import acm.program.*;
//import java.awt.*;

public class addCommas extends ConsoleProgram {
	
	public void run() {
		while(true) {
			String digits = readLine("Enter a numeric string: ");
			if (digits.length() == 0) break;
			println(addCommasToNumericString(digits));
		}
		return;
	}

	private String addCommasToNumericString(String digits) {
		String newStr = "";
		int len = digits.length();
		
		for (int i = 0; i < len; i++) {
			if((len - i) % 3 == 0 && i != 0) {
				newStr = newStr.concat(",");
			}
			newStr = newStr.concat( digits.substring(i, i+1) ); 
		}
		
		return newStr;
	}
}
