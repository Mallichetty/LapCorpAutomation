package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import resources.Base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Hooks extends Base {

    @Before
    public void BeforeSteps() throws IOException {
        driver = browserInvocation();
        driver.get(prop.getProperty("URL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
    }

    @After
    public void AfterSteps() {
        driver.quit();
    }
}
