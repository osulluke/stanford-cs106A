import acm.program.*;

public class UseCounter extends ConsoleProgram {

  public void run() {
    setFont("Times New Roman-20");

    MyCounter count1 = new MyCounter(); //starts at 1
    MyCounter count2 = new MyCounter(1000); //starts at 1000

    println("Five values for count1:");
    countFiveTimes(count1);

    println("Five values for count2:");
    countFiveTimes(count2);

    println("Another five values for count1:");
    countFiveTimes(count1);
    
    println("Another five values for count2:");
    countFiveTimes(count2);

    return;
  }

  private void countFiveTimes(MyCounter counter) {
    for (int i = 0; i<5; i++) {
      println(counter.nextValue());
    }
  }
}
