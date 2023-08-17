package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HeaderConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;

public abstract class RequestSpecificationFactory {

    private static String baseUri;

    public static void setBaseUri(String baseUri) {
        RequestSpecificationFactory.baseUri = baseUri;
    }

    public static RequestSpecification getBaseRequestSpecification(){
        return new RequestSpecBuilder()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .setConfig(getRestAssuredConfig())
                .setContentType(ContentType.JSON)
                .setBaseUri(baseUri)
                .addHeader("accept", "application/json")
                .build();
    }

    private static RestAssuredConfig getRestAssuredConfig(){
        return RestAssuredConfig.config()
                .headerConfig(HeaderConfig.headerConfig()
                        .overwriteHeadersWithName("Content-Type"))
                .objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON));
    }

}
