package stepdefinitions;

import services.petstore.pet.model.PetModel;
import services.petstore.user.models.UserModel;

public class StepData {

    public Long userId;
    public UserModel userModel;

    public Long petId;
    public PetModel petModel;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

}
