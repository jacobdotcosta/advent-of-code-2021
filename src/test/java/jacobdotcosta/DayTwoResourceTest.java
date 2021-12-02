package jacobdotcosta;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class DayTwoResourceTest {

  @Test
  public void testPartOneEndpoint() {
    given().when()
           .body("forward 5\n" + "down 5\n" + "forward 8\n" + "up 3\n" + "down 8\n" + "forward 2")
           .put("/day-2/1")
           .then()
           .statusCode(200)
           .body(is("150"));
  }

}
