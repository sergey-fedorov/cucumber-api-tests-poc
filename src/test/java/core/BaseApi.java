package core;

import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import static org.hamcrest.Matchers.*;

public class BaseApi {

    protected HttpRequest httpRequest;

    public BaseApi(){
        httpRequest = new HttpRequest();
    }


    public <T> T getResponseAs(Class<T> asClass){
        return httpRequest.getResponse().as(asClass);
    }

    public String getJsonValueAsString(String jsonField){
        return validateResponse()
                .body("$", hasKey(jsonField))
                .extract().response().jsonPath()
                .get(jsonField).toString();
    }

    public ValidatableResponse validateResponse(){
        return httpRequest.getResponse()
                .then()
                .assertThat();
    }

    public ValidatableResponse validateStatusCode(int statusCode) {
        return validateResponse()
                .statusCode(statusCode);
    }

    public void validateGenericResponseBody(int code, String type, String message) {
        Assert.assertEquals("Wrong code", code, Integer.parseInt(getJsonValueAsString("code")));
        Assert.assertEquals("Wrong type", type, getJsonValueAsString("type"));
        Assert.assertEquals("Wrong message", message, getJsonValueAsString("message"));
    }


}
