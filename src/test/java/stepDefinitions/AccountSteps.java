package stepDefinitions;

import apiEngine.Endpoints;
import apiEngine.model.requests.AuthorizationRequest;
import cucumber.TestContext;
import io.cucumber.java.en.Given;

public class AccountSteps extends BaseSteps {

    private static final String USERNAME = "QAAPIUSER2";
    private static final String PASSWORD = "QaTest@123";

    public AccountSteps(TestContext testContext) {

        super(testContext);

        System.out.println("----AccountSteps constructor call----");

    }

    @Given("I am an authorized user")
    public void iAmAnAuthorizedUser() {

        AuthorizationRequest authReq = new AuthorizationRequest(USERNAME, PASSWORD);

        getEndPoints().authenticateUser(authReq);

        //System.out.println("iAmAnAuthorizedUser");

    }

}
