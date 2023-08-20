package runner;

import base.Endpoints;
import base.RequestSpecificationFactory;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/",
        glue = "stepdefinitions",
        plugin = {"pretty", "html:target/cucumber.html"})
public class CucumberRunner {

    @BeforeClass
    public static void init(){
        String baseUri = System.getProperty("baseUri") != null ?
                System.getProperty("baseUri") :
                Endpoints.BASE_URI;
        RequestSpecificationFactory.setBaseUri(baseUri);
    }

}
