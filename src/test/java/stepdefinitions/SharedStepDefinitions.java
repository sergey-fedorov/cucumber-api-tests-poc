package stepdefinitions;

import core.BaseApi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class SharedStepDefinitions extends BaseApi {

    @And("Error details returned {string}")
    public void errorDetailsReturned(String message) {
        validateGenericResponseBody(1, "error", message);
    }

    @Then("User/Pet not found")
    public void userNotFound() {
        validateStatusCode(HTTP_NOT_FOUND);
    }

}
