/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	private int cont = 1;
	
	public void run() {
		while(cont > 0) {
			pyth();
		}
		exit();
		return;
	}
	
	private void pyth() {
		println("Enter values to compute Pythagorean Theorem:");
		
		print("a: ");
		int a = readInt();
		if(a <= 0) {
			this.cont = -1;
			return;
		}
		
		print("b: ");
		int b = readInt();
		if(b <= 0) {
			this.cont = -1;
			return;
		}
		
		double c = Math.sqrt(a*a + b*b);
		println("c: " + c);
		
		return;
	}
}
