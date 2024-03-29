package tests;

import services.Endpoints;
import core.RequestSpecificationFactory;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.*;
import services.pet.enums.PetStatus;
import services.pet.steps.PetSteps;
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
                // test fails because dummy pet from mock doesn't exist on real db
                .validatePetPresentsInList(petName);
    }

}
