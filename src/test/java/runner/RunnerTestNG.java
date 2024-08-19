package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@CucumberOptions(
		features = "src/test/java/features", 
		glue = {"stepDefinitions", "base","utils"},
		monochrome = true,
	//	tags = "@E2E or @OneTime",
		plugin = { "pretty", "html:target/cucumber-reports/cucumber-pretty.html",
				 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"rerun:target/cucumber-reports/rerun.txt" }
 
)

@Listeners(utils.Listeners.class)

public class RunnerTestNG extends AbstractTestNGCucumberTests {
	 static {
	        System.setProperty("dataproviderthreadcount", "2"); // Set to your desired number of threads
	    }
	 
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
            return super.scenarios();
    }
    
}
