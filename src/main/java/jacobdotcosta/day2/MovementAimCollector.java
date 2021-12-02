package jacobdotcosta.day2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MovementAimCollector implements Collector<String, MovementAimCollector.MapStorage, Integer> {

  public MovementAimCollector() {
  }

  public static MovementAimCollector toMovementAim() {
    return new MovementAimCollector();
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
    private static final String FORWARD = "forward";
    private static final String DOWN = "down";
    private static final String UP = "up";
    private Integer horizontalPos;
    private Integer depth;
    private Integer aim;

    public MapStorage() {
      this.horizontalPos = 0;
      this.depth = 0;
      this.aim = 0;
    }

    protected void addMovement(final String movement) {
      final String[] movementInstructions = movement.split(" ");
      if (FORWARD.equalsIgnoreCase(movementInstructions[0])) {
        horizontalPos += Integer.parseInt(movementInstructions[1]);
        depth += Integer.parseInt(movementInstructions[1]) * aim;
      } else if (DOWN.equalsIgnoreCase(movementInstructions[0])) {
        aim += Integer.parseInt(movementInstructions[1]);
      } else if (UP.equalsIgnoreCase(movementInstructions[0])) {
        aim -= Integer.parseInt(movementInstructions[1]);
      }
    }

    public Integer getResult() {
      return horizontalPos * depth;
    }


  }
}
