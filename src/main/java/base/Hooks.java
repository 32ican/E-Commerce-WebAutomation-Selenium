package base;


import java.io.IOException;


import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigLoader;
import utils.ExtentManager;


public class Hooks extends BaseClass {

	@Before
	public void setUp() {
		String url = ConfigLoader.getProperty("url");
		getDriver(ConfigLoader.getProperty("browser"));
		driver.get(url);
	}


    @AfterStep
    public void addScreenshot (Scenario scenario) throws IOException {
        if (scenario.isFailed()){
        	takeScreenShot(scenario.getName());
        	ExtentManager.attachImage();
        	System.out.println("********* Screenshot has been taken *********");
        }
    }
    
	
    
	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
