package jacobdotcosta;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class DayOneResourceTest {

    @Test
    public void testDayOnePartOneEndpoint() {
        given()
          .when()
                .body("199\n" +
                        "200\n" +
                        "208\n" +
                        "210\n" +
                        "200\n" +
                        "207\n" +
                        "240\n" +
                        "269\n" +
                        "260\n" +
                        "263").put("/day-1/1")
          .then()
             .statusCode(200)
             .body(is("7"));
    }

  @Test
  public void testDayOnePartTwoEndpoint() {
    given()
      .when()
      .body("199\n" +
              "200\n" +
              "208\n" +
              "210\n" +
              "200\n" +
              "207\n" +
              "240\n" +
              "269\n" +
              "260\n" +
              "263").put("/day-1/2")
      .then()
      .statusCode(200)
      .body(is("5"));
  }


}
