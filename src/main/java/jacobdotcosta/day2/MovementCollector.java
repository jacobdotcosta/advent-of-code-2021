package jacobdotcosta.day2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MovementCollector implements Collector<String, MovementCollector.MapStorage, Integer> {

  public MovementCollector() {
  }

  public static MovementCollector toMovement() {
    return new MovementCollector();
  }

  @Override
  public Supplier<MapStorage> supplier() {
    return MapStorage::new;
  }

  @Override
  public BiConsumer<MapStorage, String> accumulator() {
    return (storage, val) -> storage.addMovement(val);
  }

  @Override
  public BinaryOperator<MapStorage> combiner() {
    return null;
  }

  @Override
  public Function<MapStorage, Integer> finisher() {
    return (storage) -> storage.getResult();
  }

  @Override
  public Set<Characteristics> characteristics() {
    return new HashSet<>(Arrays.asList(Characteristics.UNORDERED));
  }

  protected class MapStorage {
    private Integer horizontalPos;
    private Integer depth;
    private static final String FORWARD = "forward";
    private static final String DOWN = "down";
    private static final String UP = "up";

    public MapStorage() {
      this.horizontalPos = 0;
      this.depth = 0;
    }

    protected void addMovement(final String movement) {
      final String[] movementInstructions = movement.split(" ");
      if (FORWARD.equalsIgnoreCase(movementInstructions[0])) {
        horizontalPos += Integer.parseInt(movementInstructions[1]);
      } else if (DOWN.equalsIgnoreCase(movementInstructions[0])) {
        depth += Integer.parseInt(movementInstructions[1]);
      } else if (UP.equalsIgnoreCase(movementInstructions[0])) {
        depth -= Integer.parseInt(movementInstructions[1]);
      }
    }

    public Integer getResult() {
      return horizontalPos * depth;
    }


  }
}
