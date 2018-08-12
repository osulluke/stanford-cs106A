import acm.program.*;

public class Add2Integers extends ConsoleProgram {
	public void run() {
		int cont = 1;
		while(cont != -1) {
			println("This program adds two numbers.");
			int n1 = readInt("Enter n1: ");
			int n2 = readInt("Enter n2: ");
			int total = n1 + n2;
			println("The total is " + total + "."); 
			cont = readInt("Enter -1 to exit.");
		}
	}
}
