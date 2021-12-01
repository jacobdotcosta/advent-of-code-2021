package jacobdotcosta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class DepthCollector implements Collector<Integer, DepthCollector.Depth3Storage, List<Integer>> {

  private static final Logger LOGGER = LoggerFactory.getLogger(DepthCollector.class);

  private List<Integer> resultList;
  private Depth3Storage depth3Storage;

  public DepthCollector() {
    this.depth3Storage = new Depth3Storage();
  }

  public DepthCollector(int capacity) {
    this.depth3Storage = new Depth3Storage(capacity);
  }

  public static DepthCollector toDepth3() {
    return new DepthCollector();
  }

  @Override
  public Supplier<DepthCollector.Depth3Storage> supplier() {
    return DepthCollector.Depth3Storage::new;
  }

  @Override
  public BiConsumer<DepthCollector.Depth3Storage, Integer> accumulator() {
    return (storage, val) -> storage.addElement(val);
  }

  @Override
  public BinaryOperator<DepthCollector.Depth3Storage> combiner() {
    return null;
  }

  @Override
  public Function<DepthCollector.Depth3Storage, List<Integer>> finisher() {

    return (storage) -> {
      LOGGER.info("storage: {}", storage);
      return storage.finalList;
    };
  }

  @Override
  public Set<Characteristics> characteristics() {
    return new HashSet<>(Arrays.asList(Characteristics.UNORDERED));
  }

  protected class Depth3Storage {
    private List<Integer> listOne;
    private List<Integer> listTwo;
    private List<Integer> listThree;
    private List<Integer> finalList;
    private int counter;

    public Depth3Storage() {
      this.finalList = new ArrayList();
      this.listOne = new ArrayList(3);
      this.listTwo = new ArrayList(3);
      this.listThree = new ArrayList(3);
      this.counter = 0;
    }

    public Depth3Storage(int capacity) {
      this.finalList = new ArrayList();
      this.listOne = new ArrayList(capacity);
      this.listTwo = new ArrayList(capacity);
      this.listThree = new ArrayList(capacity);
    }

    protected void addElement(int val) {
      this.listOne.add(val);
      if (this.listOne.size() >= 3) {
        this.finalList.add(this.listOne.stream().reduce(0, Integer::sum));
        listOne = new ArrayList<>(3);
      }
      if (this.counter >= 1) {
        this.listTwo.add(val);
        if (this.listTwo.size() >= 3) {
          this.finalList.add(this.listTwo.stream().reduce(0, Integer::sum));
          listTwo = new ArrayList<>(3);
        }
      }
      if (this.counter >= 2) {
        this.listThree.add(val);
        if (this.listThree.size() >= 3) {
          this.finalList.add(this.listThree.stream().reduce(0, Integer::sum));
          listThree = new ArrayList<>(3);
        }
      }
      this.counter++;
    }

    public List<Integer> getFinalList() {
      return finalList;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Depth3Storage.class.getSimpleName() + "[", "]").add(
                                                                                     "listOne=" + listOne)
                                                                                   .add(
                                                                                     "listTwo=" + listTwo)
                                                                                   .add(
                                                                                     "listThree=" + listThree)
                                                                                   .add(
                                                                                     "finalList=" + finalList)
                                                                                   .toString();
    }
  }
}
