package services.pet.steps;

import core.BaseApi;
import services.pet.model.PetModel;
import services.Endpoints;
import org.junit.Assert;
import core.exceptions.EntityNotFoundException;
import services.pet.enums.PetStatus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

public class PetSteps extends BaseApi {

    public PetSteps createNewPet(PetModel petRequestModel){
        httpRequest.sendPost(Endpoints.Pet.CREATE_PET, petRequestModel);
        return this;
    }

    public PetSteps getPetDetails(Long petId){
        httpRequest.sendGetWithPathParams(Endpoints.Pet.PET, "petId", petId);
        return this;
    }

    public PetSteps validatePetDetails(PetModel petRequest){
        validateStatusCode(HTTP_OK);
        PetModel petResponse = getResponseAs(PetModel.class);
        Assert.assertEquals(petResponse, petRequest);
        return this;
    }

    public PetSteps deletePet(Long petId){
        httpRequest.sendDelete(Endpoints.Pet.DELETE, "petId", petId);
        return this;
    }

    public PetSteps updatePet(Long petId, String name, PetStatus petStatus){
        Map<String, Long> pathParams = new HashMap<>();
        pathParams.put("petId", petId);

        Map<String, String> formData = new HashMap<>();
        formData.put("name", name);
        formData.put("status", String.valueOf(petStatus));

        httpRequest.sendPostWithPathParamAndFormData(Endpoints.Pet.DELETE, pathParams, formData);
        return this;
    }

    public PetSteps getPetsByStatus(PetStatus status){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("status", status);
        httpRequest.sendGetWithQueryParams(Endpoints.Pet.FIND_BY_STATUS, queryParams);
        return this;
    }

    public PetSteps validatePetPresentsInList(String petName){
        validateStatusCode(HTTP_OK);
        List<PetModel> petResponseList = Arrays.asList(getResponseAs(PetModel[].class));
        petResponseList.stream().filter(pet -> {
                    // pet name can be missing for some record
                    boolean result = false;
                    try {
                        result = pet.getName().equals(petName);
                    } catch (NullPointerException ignored){}
                    return result;
                })
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(petName));
        return this;
    }
}
