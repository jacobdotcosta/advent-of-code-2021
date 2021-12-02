package jacobdotcosta.day1;

public class IncreaseCounter {

  int value;
  int counter;

  public IncreaseCounter(int value) {
    this.value = value;
    this.counter = 0;
  }

  public static IncreaseCounter buildCounter(String s) {
    return new IncreaseCounter(Integer.parseInt(s));
  }

  public static IncreaseCounter buildCounter(Integer i) {
    return new IncreaseCounter(i);
  }

  public IncreaseCounter incrementCounter(int previousCounter) {
    this.counter = ++previousCounter;
    return this;
  }

  public IncreaseCounter updateCounter(int previousCounter) {
    this.counter = previousCounter;
    return this;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("IncreaseCounter{");
    sb.append("value=").append(value);
    sb.append(", counter=").append(counter);
    sb.append('}');
    return sb.toString();
  }
}
