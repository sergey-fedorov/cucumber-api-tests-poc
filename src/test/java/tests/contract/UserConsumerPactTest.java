package tests.contract;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.PactTestExecutionContext;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.ConsumerPactTest;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import services.Endpoints;
import core.RequestSpecificationFactory;
import services.user.steps.UserSteps;

public class UserConsumerPactTest extends ConsumerPactTest {

    // Consumer side
    // 1. Create consumer test that describes all consumer expectations from a provider: fields presence, data types, specific matchers, sie, content-type, etc.
    // 2. Execute test against mocked provider server
    // 3. Create a pact file in the view of json

    String username = "string";
    String username404 = "anyName";

    @Pact(provider="userProvider", consumer="userConsumer")
    @Override
    protected RequestResponsePact createPact(PactDslWithProvider pactDslWithProvider) {
        return pactDslWithProvider
                .uponReceiving("Request for getting user details")
                    .path(Endpoints.User.USER.replace("{username}", username))
                    .method("GET")
                .willRespondWith()
                    .status(200)
                    .body(new PactDslJsonBody()
                            .id("id")
                            .stringType("username")
                            .stringType("firstName")
                            .stringType("lastName")
                            .stringType("email")
                            .stringType("password")
                            .stringType("phone")
                            .numberType("userStatus")
                    )

                .uponReceiving("Request for getting user details 404")
                    .path(Endpoints.User.USER.replace("{username}", username404))
                    .method("GET")
                .willRespondWith()
                    .status(404)
                    .body(new PactDslJsonBody()
                            .numberValue("code", 1)
                            .stringValue("type", "error")
                            .stringValue("message", "User not found")
                    )
                .toPact();
    }

    @PactVerification("userProvider")
    @Override
    protected void runTest(MockServer mockServer, PactTestExecutionContext pactTestExecutionContext) {
        UserSteps userSteps = new UserSteps();
        RequestSpecificationFactory.mock(mockServer.getUrl(), mockServer.getPort());
        userSteps.getUserDetails(username)
                .validateStatusCode(200);
        userSteps.getUserDetails(username404)
                .validateStatusCode(404);
    }

    @Override
    protected String providerName() {
        return "userProvider";
    }

    @Override
    protected String consumerName() {
        return "userConsumer";
    }
}
