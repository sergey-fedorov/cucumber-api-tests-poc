package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import services.petstore.user.models.UserModel;
import services.petstore.user.steps.UserSteps;
import utils.Utils;

import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;


public class UserStepDefinitions extends UserSteps {

    public StepData stepData;

    public UserStepDefinitions(StepData stepData){
        this.stepData = stepData;
    }

    @After("@UserCleanUp")
    public void userCleanup() {
        try {
            deleteUser(stepData.userModel.getUsername());
        } catch (NullPointerException | IllegalArgumentException e){
            // TODO: Add logging
            System.out.println("No user to cleanup");
        }
    }

    @Given("User with username {string} does not exist")
    public void userWithNameDoesNotExist(String userName) {
        deleteUser(userName);
    }

    @When("^(I create a new user|User created) with details:$")
    public void createUserWithDetails(String str, DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap();

        long userId = Long.parseLong(Utils.getRandomSixNumbersString());
        stepData.setUserId(userId);

        stepData.userModel = new UserModel.UserModelBuilder()
                .setId(userId)
                .setUsername(dataMap.get("username"))
                .setFirstName(dataMap.get("firstName"))
                .setLastName(dataMap.get("lastName"))
                .setEmail(dataMap.get("email"))
                .setPassword(dataMap.get("password"))
                .setPhone(dataMap.get("phone"))
                .setUserStatus(Integer.parseInt(dataMap.get("userStatus")))
                .build();

        createNewUser(stepData.userModel);
    }

    @Then("Update all user details")
    public void updateAllUserDetails(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap();
        String oldUserName = stepData.userModel.getUsername();

        stepData.userModel = new UserModel.UserModelBuilder()
                .setId(stepData.userId)
                .setUsername(dataMap.get("username"))
                .setFirstName(dataMap.get("firstName"))
                .setLastName(dataMap.get("lastName"))
                .setEmail(dataMap.get("email"))
                .setPassword(dataMap.get("password"))
                .setPhone(dataMap.get("phone"))
                .setUserStatus(Integer.parseInt(dataMap.get("userStatus")))
                .build();

        updateUserDetails(oldUserName, stepData.userModel);
    }

    @Then("User created/updated")
    public void newUserCreated() {
        validateStatusCode(HTTP_OK);
        validateGenericResponseBody(HTTP_OK, "unknown", String.valueOf(stepData.userModel.getId()));
    }

    @When("I read details of user with username {string}")
    public void iReadUserDetails(String userName) {
        getUserDetails(userName);
    }

    @Then("User has correct details")
    public void userHasCorrectDetails() {
        validateUserDetails(stepData.userModel);
    }

    @When("I delete user with username {string}")
    public void iDeleteUser(String userName) {
        deleteUser(userName);
    }

    @Then("User deleted")
    public void userDeleted() {
        validateStatusCode(HTTP_OK);
        validateGenericResponseBody(HTTP_OK, "unknown", stepData.userModel.getUsername());
    }


    @Then("User is not created")
    public void userNotCreated() {
        validateStatusCode(HTTP_OK);
        validateGenericResponseBody(HTTP_OK, "unknown", "0");
    }
}
