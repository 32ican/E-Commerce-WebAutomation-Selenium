package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;


@CucumberOptions(
		features = "src/test/java/features", 
		glue = {"stepDefinitions", "base","utils"},
		monochrome = true,
		tags = "@OneTime",
		plugin = { "pretty", "html:target/cucumber-reports/cucumber-pretty.html", 
				"rerun:target/cucumber-reports/rerun.txt" }
 
)

@Listeners(utils.Listeners.class)
public class RunnerTestNG extends AbstractTestNGCucumberTests {
	

}
