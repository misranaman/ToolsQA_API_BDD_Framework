package stepDefinitions;

import apiEngine.Endpoints;
import cucumber.ScenarioContext;
import cucumber.TestContext;

public class BaseSteps {

    private Endpoints endPoints;
    private ScenarioContext scenarioContext;

    public BaseSteps(TestContext testContext) {

        System.out.println("----BaseSteps constructor call----");

        endPoints = testContext.getEndPoints();
        scenarioContext = testContext.getScenarioContext();
    }

    public Endpoints getEndPoints() {

        return endPoints;
    }

    public ScenarioContext getScenarioContext() {

        return scenarioContext;

    }
}
