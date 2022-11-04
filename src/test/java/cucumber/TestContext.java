package cucumber;

import apiEngine.Endpoints;
import dataProvider.ConfigReader;
import enums.Context;

public class TestContext {

    private Endpoints endPoints;
    private ScenarioContext scenarioContext;

    public TestContext() {

        System.out.println("----TestContext constructor call----");

        endPoints = new Endpoints(ConfigReader.getInstance().getBaseUrl());
        scenarioContext = new ScenarioContext();
        scenarioContext.setContext(Context.USER_ID, ConfigReader.getInstance().getUserID());

    }

    public Endpoints getEndPoints() {

        return endPoints;
    }

    public ScenarioContext getScenarioContext() {

        return scenarioContext;

    }


}
