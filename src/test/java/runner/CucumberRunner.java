package runner;

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
        RequestSpecificationFactory.setBaseUri("https://petstore.swagger.io/v2");
    }

}
