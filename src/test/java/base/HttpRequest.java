package base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;


public class HttpRequest {

    private RequestSpecification getRequestSpecification(){
        return RestAssured.given().spec(RequestSpecificationFactory.getBaseRequestSpecification());
    }

    private static final ThreadLocal<Response> response = new ThreadLocal<>();

    private void setResponse(Response resp){
        response.set(resp);
    }

    public Response getResponse(){
        return response.get();
    }


    /* Custom HTTP methods */

    protected Response sendPost(String url, Object body) {
        RequestSpecification requestSpec = getRequestSpecification()
                .body(body);
        return doPost(url, requestSpec);
    }

    protected Response sendPostWithPathParamAndFormData(String url, Map<String, ?> pathParams, Map<String, String> formData) {
        RequestSpecification requestSpec = getRequestSpecification()
                .contentType(ContentType.URLENC)
                .pathParams(pathParams)
                .formParams(formData);
        return doPost(url, requestSpec);
    }

    protected Response sendGet(String url) {
        return doGet(url, getRequestSpecification());
    }

    protected Response sendGetWithPathParams(String url, String paramName, Object paramValue) {
        RequestSpecification requestSpec = getRequestSpecification()
                .pathParam(paramName, paramValue);
        return doGet(url, requestSpec);
    }

    protected Response sendGetWithQueryParams(String url, Map<String, Object> queryParams) {
        RequestSpecification requestSpec = getRequestSpecification()
                .queryParams(queryParams);
        return doGet(url, requestSpec);
    }

    protected Response sendPutWithPathParams(String url, Object body, String paramName, Object paramValue) {
        RequestSpecification requestSpec = getRequestSpecification()
                .body(body)
                .pathParam(paramName, paramValue);
        return doPut(url, requestSpec);
    }

    protected Response sendDelete(String url, String paramName, Object paramValue) {
        RequestSpecification requestSpec = getRequestSpecification()
                .pathParam(paramName, paramValue);
        return doDelete(url, requestSpec);
    }

    /* Base HTTP methods */

    private Response doGet(String url, RequestSpecification requestSpecification) {
        Response response = requestSpecification
                .get(url)
                .then()
                .extract().response();
        setResponse(response);
        return response;
    }

    private Response doPost(String url, RequestSpecification requestSpecification) {
        Response response = requestSpecification
                .post(url)
                .then()
                .extract().response();
        setResponse(response);
        return response;
    }

    private Response doPut(String url, RequestSpecification requestSpecification) {
        Response response = requestSpecification
                .put(url)
                .then()
                .extract().response();
        setResponse(response);
        return response;
    }

    private Response doDelete(String url, RequestSpecification requestSpecification) {
        Response response = requestSpecification
                .delete(url)
                .then()
                .extract().response();
        setResponse(response);
        return response;
    }

}
