
public class MyCounter {
	/*Builds a 'counter' object initialized to startValue*/
	public MyCounter(int startValue) {
		counter = startValue;
	}
	/*Builds a 'counter' object and initializes to '1'*/
	public MyCounter() {
		counter = 1;
	}
	/*Delivers the current value of counter, and increments it by '1'*/
	public int nextValue() {
		int temp = counter;
		counter += 1;

		return temp;
	}
	/*Single instance variable to contain the counter number*/
	private int counter;
}
