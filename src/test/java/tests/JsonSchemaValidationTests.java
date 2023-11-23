package tests;

import core.BaseTest;
import core.StepsFactory;
import core.TestDataFactory;
import org.junit.After;
import org.junit.Test;
import services.pet.model.PetModel;
import services.pet.steps.PetSteps;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static java.net.HttpURLConnection.HTTP_OK;

public class JsonSchemaValidationTests extends BaseTest {

    PetSteps petSteps = StepsFactory.get(PetSteps.class);
    PetModel pet = TestDataFactory.getModel(PetModel.class);
    long petId = pet.getId();

    @After
    public void petCleanUp(){
        petSteps.deletePet(petId);
    }

    @Test
    public void petSchemaTest(){
        petSteps.createNewPet(pet)
                .validateStatusCode(HTTP_OK);
        petSteps.getPetDetails(petId)
                .validateResponse()
                .body(matchesJsonSchema(new File("src/test/resources/__files/pet_schema.json")));
    }

}
