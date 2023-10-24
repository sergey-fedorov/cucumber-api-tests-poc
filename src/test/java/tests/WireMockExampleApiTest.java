package tests;

import base.Endpoints;
import base.RequestSpecificationFactory;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.*;
import services.petstore.pet.model.PetStatus;
import services.petstore.pet.steps.PetSteps;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class WireMockExampleApiTest {

    static final String BASE_URI = "http://localhost/";
    static final int PORT = 8089;

    PetSteps petSteps = new PetSteps();

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT);


    @Test
    public void mockTest(){
        String petId = "2";

        stubFor(get(Endpoints.Pet.PET.replace("{petId}", petId))
                .willReturn(ok()
                                .withHeader("content-type", "application/json")
                                .withBodyFile("get_pet.json")
                        ));

        RequestSpecificationFactory.mock(BASE_URI, PORT);
        petSteps.getPetDetails(Long.valueOf(petId));
        String petStatus = petSteps.getJsonValueAsString("status");
        String petName = petSteps.getJsonValueAsString("name");

        RequestSpecificationFactory.unMock();
        petSteps.getPetsByStatus(PetStatus.valueOf(petStatus))
                .validatePetPresentsInList(petName);
    }

}
