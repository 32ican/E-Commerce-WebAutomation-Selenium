package utils;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends Commands {

	@Before
	public void setUp() {
		String url = ConfigLoader.getProperty("url");
		WebDriverManager.getDriver().get(url);
	}

	@AfterStep
	public void addScreenshot(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			takeScreenShot(scenario.getName());
			ExtentManager.attachImage();
			System.out.println("********* Screenshot has been taken *********");
		}
	}

	@After
	public void tearDown() {
		WebDriverManager.cleanupDriver();
	}

}
