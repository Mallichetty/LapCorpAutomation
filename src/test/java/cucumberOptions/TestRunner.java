package cucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/featureFiles",
        glue = {"stepDefinitions"},
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "pretty",
                "html:target/cucumber-reports/cucumber.html"},
        monochrome = true
)
public class TestRunner {
}