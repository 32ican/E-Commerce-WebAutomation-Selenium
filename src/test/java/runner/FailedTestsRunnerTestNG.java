package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;


@CucumberOptions(
		features = "@target/cucumber-reports/rerun.txt", 
		glue = { "stepDefinitions", "base","utils" },
		tags = "@OneTime",
		monochrome = true,
		plugin = { "pretty", "html:target/cucumber-reports/cucumber-pretty.html"
				}
		
 
)

@Listeners(utils.Listeners.class)
public class FailedTestsRunnerTestNG extends AbstractTestNGCucumberTests {

        @Override
        @DataProvider(parallel = false)
        public Object[][] scenarios() {
                return super.scenarios();
        }

}
