package tests.contract;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.junitsupport.target.Target;
import au.com.dius.pact.provider.junitsupport.target.TestTarget;
import services.Endpoints;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(PactRunner.class)
@Provider("userProvider")
@PactFolder("src/test/resources/pacts")
//@PactFolder("target/pacts")
public class UserContractTest {

    // Provider side
    // 1. Execute test against real provider server using pact json file

    @TestTarget
    public final Target target = new HttpTarget("https", Endpoints.BASE_HOST, 443);

    @Test
    public void getUserDetails() {

    }

}
