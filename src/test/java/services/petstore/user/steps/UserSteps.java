package services.petstore.user.steps;

import base.BaseApi;
import base.Endpoints;
import org.junit.Assert;
import services.petstore.user.models.UserModel;

import static java.net.HttpURLConnection.HTTP_OK;

public class UserSteps extends BaseApi {

    
    public String createNewUser(UserModel userModel){
        httpRequest.sendPost(Endpoints.User.CREATE_USER, userModel);
        // TODO: null returned if field not found
        return getJsonValueAsString("message");
    }

    public UserSteps getUserDetails(String userName){
        httpRequest.sendGetWithPathParams(Endpoints.User.USER, "username", userName);
        return this;
    }

    public String updateUserDetails(String userName, UserModel userModel){
        httpRequest.sendPutWithPathParams(Endpoints.User.USER, userModel, "username", userName);
        return getJsonValueAsString("message");
    }

    public UserSteps deleteUser(String userName){
        httpRequest.sendDelete(Endpoints.User.USER, "username", userName);
        return this;
    }

    public UserSteps validateUserDetails(UserModel userModel){
        validateStatusCode(HTTP_OK);
        UserModel userResponse = getResponseAs(UserModel.class);
        Assert.assertEquals(userResponse, userModel);
        return this;
    }

}
