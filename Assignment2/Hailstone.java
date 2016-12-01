/*
 * File: Hailstone.java
 * Name:
 * Section Leader:
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		print("Enter a number: ");
		num = readInt();

		while(num != 1) {
			if (num % 2 == 0) {
				println(num + " is even so I take half: " + (num = num / 2));
			}
			else {
				println(num + " is odd, so I make 3n+1: " + (num = num * 3 + 1));
			}
			count += 1;
		}
		println("The process took " + count + " to reach 1");
	}

	private int num;
	private int count = 0;
}
