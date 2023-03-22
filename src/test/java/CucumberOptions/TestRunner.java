package CucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/Features",
		plugin="json:target/jsonReports/cucumber-report.json",
		glue = {"StepDefinitions"}
)		
public class TestRunner {

}
