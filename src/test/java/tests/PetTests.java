package tests;

import org.junit.After;
import org.junit.Test;
import services.pet.model.Category;
import services.pet.model.PetModel;
import services.pet.model.PetStatus;
import services.pet.model.Tag;
import services.pet.steps.PetSteps;

import java.util.ArrayList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

public class PetTests {

    PetSteps petSteps = new PetSteps();

    Long petId = 111L;
    PetModel pet = PetModel.builder()
            .id(petId)
            .name("John")
            .status(PetStatus.available)
            .category(new Category(101, "dog"))
            .tags(new ArrayList<>(List.of(new Tag(102, "beagle"))))
            .photoUrls(new ArrayList<>(List.of("https://upload.wikimedia.org/wikipedia/commons/5/55/Beagle_600.jpg")))
            .build();

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
        petSteps.createNewPet(pet)
                .validateStatusCode(HTTP_OK);

        petSteps.updatePet(petId, "JohnNew", PetStatus.sold);
        petSteps.validateStatusCode(HTTP_OK);
        petSteps.validateGenericResponseBody(HTTP_OK, "unknown", String.valueOf(pet.getId()));

        pet.setName("JohnNew");
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
