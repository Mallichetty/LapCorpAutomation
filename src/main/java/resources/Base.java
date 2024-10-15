package resources;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class Base {
    public static WebDriver driver;
    public Properties prop;

    public WebDriver browserInvocation() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "//src//main//java//resources//data.properties");

        prop.load(fis);
        String browserName = prop.getProperty("Browser");
        System.out.println(browserName);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        if (browserName.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//chromedriver");

            driver = new ChromeDriver(options);
        } else if (browserName.equals("Firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//geckodriver.exe");
            driver = new FirefoxDriver();
        } else {
            System.setProperty("webdriver.ie.driver", "");
            driver = new InternetExplorerDriver();
        }
        return driver;
    }

    public WebElement waitForElement(By elel) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elel));
    }

    public void sendKeys(By ele, String text) {
        driver.findElement(ele).sendKeys(text);
    }

    public List<WebElement> getMultipleElements(By ele) {
        return driver.findElements(ele);
    }

    public void click(By ele) {
        driver.findElement(ele).click();
    }

    public void moveToElement(By ele) {
        Actions a = new Actions(driver);
        a.moveToElement(driver.findElement(ele)).build().perform();
    }

    public void sendKeys(String text) {
        Actions a = new Actions(driver);
        a.sendKeys(text).perform();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void navigateToURL(String url) {
        driver.get(url);

    }

    public void javaScriptClick(String selector) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(selector);
    }


    public String screenCapture(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
        FileUtils.copyFile(src, new File(destinationFile));
        return destinationFile;
    }
}