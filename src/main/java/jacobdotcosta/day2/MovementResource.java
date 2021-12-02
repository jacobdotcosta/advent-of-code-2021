package jacobdotcosta.day2;

import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static jacobdotcosta.day2.MovementAimCollector.toMovementAim;
import static jacobdotcosta.day2.MovementCollector.toMovement;

@Path("/day-2")
public class MovementResource {

  @PUT
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.TEXT_PLAIN)
  @Path("1")
  public Uni<RestResponse<Integer>> partOne(final String body) {
    return Uni.createFrom()
              .item(RestResponse.ResponseBuilder.ok(body.lines().collect(toMovement())).build());
  }

  @PUT
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.TEXT_PLAIN)
  @Path("2")
  public Uni<RestResponse<Integer>> partTwo(final String body) {
    return Uni.createFrom()
              .item(RestResponse.ResponseBuilder.ok(body.lines().collect(toMovementAim())).build());
  }
}
