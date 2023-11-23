package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import services.pet.model.Category;
import services.pet.model.PetModel;
import services.pet.enums.PetStatus;
import services.pet.model.Tag;
import services.pet.steps.PetSteps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

public class PetStepDefinitions extends PetSteps {

    StepData stepData;

    public PetStepDefinitions(StepData stepData){
        this.stepData = stepData;
    }

    @After("@PetCleanUp")
    public void petCleanup() {
        try {
            deletePet(stepData.petModel.getId());
        } catch (NullPointerException | IllegalArgumentException e){
            // TODO: Add logging
            System.out.println("No pet to cleanup");
        }
    }


    @Given("Pet with id {long} does not exist")
    public void petWithIdDoesNotExist(Long petId) {
        stepData.setPetId(petId);

        deletePet(petId);
    }

    @Given("^(I create a new pet|Pet created) with details:$")
    public void iCreateANewPet(String str, DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap();

        long id = Long.parseLong(dataMap.get("id"));
        PetStatus status = PetStatus.valueOf(dataMap.get("status"));
        Category category = new Category(Long.parseLong(dataMap.get("categoryId")), dataMap.get("categoryName"));
        ArrayList<Tag> tags = new ArrayList<>(
                List.of(new Tag(Long.parseLong(dataMap.get("tagId")), dataMap.get("tagName"))));
        ArrayList<String> urls = new ArrayList<>(
                List.of(dataMap.get("photoUrl")));

        stepData.petModel = PetModel.builder()
                .id(id)
                .name(dataMap.get("name"))
                .status(status)
                .category(category)
                .tags(tags)
                .photoUrls(urls)
                .build();

        createNewPet(stepData.petModel);
    }


    @When("Pet created")
    public void iVerifyPetDetails() {
        validateStatusCode(HTTP_OK);
        Assert.assertEquals(getJsonValueAsString("id"), String.valueOf(stepData.petModel.getId()));
    }


    @When("I read pet details with id {long}")
    public void iReadPetDetailsWithId(Long petId) {
        getPetDetails(petId);

    }

    @Then("Pet has correct details")
    public void petHasCorrectDetails() {
        validatePetDetails(stepData.petModel);
    }

    @When("I update pet details with id {long}")
    public void updatePetDetailsWithId(Long petId, DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap();
        PetStatus petStatus = PetStatus.valueOf(dataMap.get("status"));
        String name = dataMap.get("name");

        updatePet(petId, name, petStatus);

        stepData.petModel.setName(name);
        stepData.petModel.setStatus(petStatus);
    }

    @Then("Pet updated/deleted")
    public void petUpdatedOrDeleted() {
        validateStatusCode(HTTP_OK);
        validateGenericResponseBody(HTTP_OK, "unknown", String.valueOf(stepData.petModel.getId()));
    }

    @When("I delete pet with id {long}")
    public void iDeletePetWithId(Long petId) {
        deletePet(petId);
    }

    @When("I get pets list filtered by status {string}")
    public void iGetPetsListFilteredByStatus(String status) {
        getPetsByStatus(PetStatus.valueOf(status));
    }

    @Then("Pet with name {string} presents in list")
    public void petWithNamePresentsInList(String petName) {
        validatePetPresentsInList(petName);
    }
}
