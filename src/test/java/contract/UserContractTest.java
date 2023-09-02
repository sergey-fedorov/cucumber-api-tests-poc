package contract;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.junitsupport.target.Target;
import au.com.dius.pact.provider.junitsupport.target.TestTarget;
import base.Endpoints;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(PactRunner.class)
@Provider("userProvider")
@PactFolder("src/test/resources/pacts")
//@PactFolder("target/pacts")
public class UserContractTest {

    @TestTarget
    public final Target target = new HttpTarget("https", Endpoints.BASE_HOST, 443);

    @State("Request for getting user details")
    @Test
    public void sampleState() {

    }

}
