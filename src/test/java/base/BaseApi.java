package base;

import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import services.petstore.common.GenericResponseModel;

public class BaseApi extends HttpRequest{

    protected <T> T getResponseAs(Class<T> asClass){
        return getResponse().as(asClass);
    }

    protected String getJsonValueAsString(String jsonField){
        return getResponse().jsonPath().get(jsonField).toString();
    }

    protected ValidatableResponse validateResponse(){
        return getResponse()
                .then()
                .assertThat();
    }

    protected ValidatableResponse validateStatusCode(int statusCode) {
        return validateResponse()
                .statusCode(statusCode);
    }

    protected void validateGenericResponseBody(Integer code, String type, String message) {
        GenericResponseModel genericResponseModel = getResponseAs(GenericResponseModel.class);
        GenericResponseModel expectedResponse = new GenericResponseModel(code, type, message);

        Assert.assertEquals(genericResponseModel, expectedResponse);
    }


}
