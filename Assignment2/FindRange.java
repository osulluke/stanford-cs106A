/*
 * File: FindRange.java
 * Name:
 * Section Leader:
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {

	public void run() {
		int num = 0;
		
		println("Enter numbers: ");
		while(SENTINAL != 0) {
			print("? ");
			num = readInt();

			if ((num == max) && (max == min) && (min == 0)) {
				SENTINAL = 0;
				println("You entered the sentinal on the first try!");
				break;
			}

			else if (num == 0) {
				SENTINAL = 0;
				break;
			}

			else if ((max == min) && (min == 0)) {
				max = num;
				min = num;
			}

			else if (num <= min){
				min = num;
			}

			else if (num >= max) {
				max = num;
			}
		}

		printNumbers();

		return;
	}

	public void printNumbers() {
		println("smallest: " + min);
		println("largest: " + max);

		return;
	}

	private int max = 0;
	private int min = 0;
	private int SENTINAL = 1;
}
