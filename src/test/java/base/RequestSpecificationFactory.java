package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HeaderConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;

public abstract class RequestSpecificationFactory {

    private static String baseUri = Endpoints.BASE_URI;
    private static final int DEFAULT_PORT = 443;

    private static RequestSpecification requestSpecification;

    public static void setBaseUri(String baseUri) {
        RequestSpecificationFactory.baseUri = baseUri;
    }

    public static RequestSpecification getBaseRequestSpecification(){
        if (requestSpecification == null){
            requestSpecification =  new RequestSpecBuilder()
                    .addFilter(new LoggerFilter())
                    .setConfig(getRestAssuredConfig())
                    .setContentType(ContentType.JSON)
                    .setBaseUri(baseUri)
                    .addHeader("accept", "application/json")
                    .build();
        }
        return requestSpecification;
    }

    private static RestAssuredConfig getRestAssuredConfig(){
        return RestAssuredConfig.config()
                .headerConfig(HeaderConfig.headerConfig()
                        .overwriteHeadersWithName("Content-Type"))
                .objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON));
    }

    public static void mock(String baseUri, int port){
        getBaseRequestSpecification().port(port).baseUri(baseUri);
    }

    public static void unMock(){
        getBaseRequestSpecification().port(DEFAULT_PORT).baseUri(baseUri);
    }

}
