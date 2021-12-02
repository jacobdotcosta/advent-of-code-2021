package jacobdotcosta.day1;

import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static jacobdotcosta.day1.DepthCollector.toDepth3;

@Path("/day-1")
public class DayOneResource {

  @PUT
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.TEXT_PLAIN)
  @Path("1")
  public Uni<RestResponse<Integer>> partOne(final String body) {
    return Uni.createFrom()
              .item(RestResponse.ResponseBuilder.ok(body.lines()
                                                        .map(IncreaseCounter::buildCounter)
                                                        .reduce(
                                                          (cur, next) -> cur.value >= next.value ? next.updateCounter(
                                                            cur.counter) : next.incrementCounter(
                                                            cur.counter))
                                                        .get().counter).build());
  }

  @PUT
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.TEXT_PLAIN)
  @Path("2")
  public Uni<RestResponse<Integer>> partTwo(final String body) {
    return Uni.createFrom()
              .item(RestResponse.ResponseBuilder.ok(body.lines()
                                                        .map(Integer::parseInt)
                                                        .collect(toDepth3())
                                                        .stream()
                                                        .map(IncreaseCounter::buildCounter)
                                                        .reduce(
                                                          (cur, next) -> cur.value >= next.value ? next.updateCounter(
                                                            cur.counter) : next.incrementCounter(
                                                            cur.counter))
                                                        .get().counter).build());
  }
}
