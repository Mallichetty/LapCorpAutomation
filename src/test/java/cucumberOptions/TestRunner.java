package cucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

		features = { "src/test/java/featureFiles/priceValidation.feature" }, glue = { "stepDefinitions" },
		plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}

)

public class TestRunner {
}