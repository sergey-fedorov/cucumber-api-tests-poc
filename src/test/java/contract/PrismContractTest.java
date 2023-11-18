package contract;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;


public class PrismContractTest {

    // Prism mock server should be up and running.
    // prism mock https://petstore.swagger.io/v2/swagger.json -h 0.0.0.0
    // https://docs.stoplight.io/docs/prism

    RequestSpecification requestSpecification = RestAssured.given()
            .baseUri("http://35.159.16.199")
//            .baseUri("http://0.0.0.0")
            .port(4010)
            .filter(new RequestLoggingFilter())
            .filter(new ResponseLoggingFilter());

    @Test
    public void createOrderSuccessByStatuses(){
        Map<String, Object> newOrderJson = new HashMap<>();
        newOrderJson.put("id", 9068609183951369000L);
        newOrderJson.put("petId", 9068609183951369001L);
        newOrderJson.put("quantity", 2);
        newOrderJson.put("shipDate", "2023-09-06T13:44:13.900+0000");
        newOrderJson.put("status", "placed");
        newOrderJson.put("complete", true);

        Response response = requestSpecification
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(newOrderJson)
                .post("/store/order")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertFalse("Validation error: " + response.getHeader("sl-violations"),
                response.getHeaders().hasHeaderWithName("sl-violations"));

        newOrderJson.put("status", "approved");
        response = requestSpecification
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(newOrderJson)
                .post("/store/order")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertFalse("Validation error: " + response.getHeader("sl-violations"),
                response.getHeaders().hasHeaderWithName("sl-violations"));

        newOrderJson.put("status", "delivered");
        response = requestSpecification
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(newOrderJson)
                .post("/store/order")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertFalse("Validation error: " + response.getHeader("sl-violations"),
                response.getHeaders().hasHeaderWithName("sl-violations"));
    }

    @Test
    public void createOrderNegative(){
        Map<String, Object> newOrderJson = new HashMap<>();
        newOrderJson.put("id", 9068609183951369000L);
        newOrderJson.put("petId", 9068609183951369001L);
        newOrderJson.put("quantity", 2);
        newOrderJson.put("shipDate", "2023-09-06T13:44:13.900+0000");
        newOrderJson.put("status", "any");
        newOrderJson.put("complete", true);

        Response response = requestSpecification
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(newOrderJson)
                .post("/store/order")
                .then()
                .statusCode(400)
                .extract()
                .response();
        Assert.assertTrue(response.getHeader("sl-violations")
                .contains("Request body property status must be equal to one of the allowed values: placed, approved, delivered"));


        newOrderJson.put("shipDate", "2023");
        newOrderJson.put("status", "placed");
        response = requestSpecification
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(newOrderJson)
                .post("/store/order")
                .then()
                .statusCode(400)
                .extract()
                .response();
        Assert.assertTrue(response.getHeader("sl-violations")
                .contains("Request body property shipDate must match format \\\"date-time\\\""));
    }

    @Test
    public void getUserDetailsSuccess(){
        Response response = requestSpecification
                .header("accept", "application/json")
                .get("/user/username")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        Assert.assertFalse("Validation error: " + response.getHeader("sl-violations"),
                response.getHeaders().hasHeaderWithName("sl-violations"));
    }

}
