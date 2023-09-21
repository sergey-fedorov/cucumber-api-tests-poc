package base;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

public class LoggerFilter implements Filter {

    Logger logger = LogManager.getLogger(LoggerFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
        Response response = filterContext.next(filterableRequestSpecification, filterableResponseSpecification);

        Headers requestHeaders = filterableRequestSpecification.getHeaders();
        String cookiesString = filterableRequestSpecification.getCookies().exist() ? filterableRequestSpecification.getCookies().toString() : "no cookies";
        String requestBodyString = filterableRequestSpecification.getBody() != null ? filterableRequestSpecification.getBody() : "no body";

        String logId = Utils.getRandomSixNumbersString();

        String requestLog = logId +
                "\n" +
                filterableRequestSpecification.getMethod() +
                " " +
                filterableRequestSpecification.getURI() +
                "\n" +
                requestHeaders +
                "\n" +
                cookiesString +
                "\n" +
                requestBodyString;
        logger.info(requestLog);

        String responseLog = logId +
                "\n" +
                response.getStatusLine() +
                " " +
                response.getStatusCode() +
                "\n" +
                response.getHeaders() +
                "\n" +
                response.getBody().prettyPrint();
        logger.info(responseLog);

        return response;
    }
}
