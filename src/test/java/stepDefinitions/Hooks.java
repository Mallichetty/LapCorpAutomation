package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import resources.Base;
import resources.Log;

public class Hooks extends Base {

    private Scenario scenario;

    @Before
    public void setUp() {
        Log.info("Starting the test execution...");
        driver = browserInvocation();
        navigateToURL(prop.getProperty("URL"));
    }

    @After
    public void afterScenario(Scenario scenario) {
        this.scenario = scenario;
        if (scenario.isFailed()) {
            captureScreenshot();
        }
    }

    private void captureScreenshot() {
        String testCaseName = scenario.getName().replaceAll(" ", "_");
        String screenshotPath = new Base().screenCapture(testCaseName);
        System.out.println("Screenshot saved at: " + screenshotPath);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser after the test
            Log.info("Test execution completed. Browser closed.");
        }
    }
}
