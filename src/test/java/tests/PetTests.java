package tests;

import core.BaseTest;
import core.StepsFactory;
import core.TestDataFactory;
import org.junit.After;
import org.junit.Test;
import services.pet.model.PetModel;
import services.pet.enums.PetStatus;
import services.pet.steps.PetSteps;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

public class PetTests extends BaseTest {

    PetSteps petSteps = StepsFactory.get(PetSteps.class);
    PetModel pet = TestDataFactory.getModel(PetModel.class);
    long petId = pet.getId();

    @After
    public void petCleanUp(){
        petSteps.deletePet(petId);
    }

    @Test
    public void verifyPetCreatedWithCorrectDetails(){
        petSteps.createNewPet(pet)
                .validateStatusCode(HTTP_OK);
        petSteps.getPetDetails(petId)
                .validatePetDetails(pet);
    }

    @Test
    public void verifyPetUpdatedWithCorrectDetails(){
        String newPetName = faker.dog().name();

        petSteps.createNewPet(pet)
                .validateStatusCode(HTTP_OK);

        petSteps.updatePet(petId, newPetName, PetStatus.sold);
        petSteps.validateStatusCode(HTTP_OK);
        petSteps.validateGenericResponseBody(HTTP_OK, "unknown", String.valueOf(pet.getId()));

        pet.setName(newPetName);
        pet.setStatus(PetStatus.sold);
        petSteps.getPetDetails(petId)
                .validatePetDetails(pet);
    }

    @Test
    public void verifyPetDeleted(){
        petSteps.createNewPet(pet)
                .validateStatusCode(HTTP_OK);

        petSteps.deletePet(petId);
        petSteps.validateStatusCode(HTTP_OK);
        petSteps.validateGenericResponseBody(HTTP_OK, "unknown", String.valueOf(pet.getId()));

        petSteps.getPetDetails(petId);
        petSteps.validateStatusCode(HTTP_NOT_FOUND);
        petSteps.validateGenericResponseBody(1, "error", "Pet not found");

        petSteps.deletePet(petId)
                .validateStatusCode(HTTP_NOT_FOUND);
    }

    @Test
    public void verifyPetPresentsInListByStatus(){
        pet.setStatus(PetStatus.sold);
        petSteps.createNewPet(pet)
                .validateStatusCode(HTTP_OK);
        petSteps.getPetsByStatus(PetStatus.sold)
                .validatePetPresentsInList(pet.getName());
    }

}
