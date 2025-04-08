package resources;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Base {
    public static WebDriver driver;
    public static Properties prop;
    protected WebDriverWait wait;

    public static WebDriver browserInvocation() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//data.properties");
            prop.load(fis);
        } catch (IOException e) {
            Log.error("Error loading properties file: " + e.getMessage());
        }

        String browserName = prop.getProperty("Browser");
        Log.error("Selected Browser: " + browserName);

        if (browserName.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//geckodriver.exe");
            driver = new FirefoxDriver();
        } else {
            System.setProperty("webdriver.ie.driver", "");
            driver = new InternetExplorerDriver();
        }

        driver.manage().window().maximize();
        return driver;
    }

    // Generic Method to Find Element with Wait
    protected WebElement waitForElement(By by, int timeoutInSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, timeoutInSeconds);
            return customWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            Log.info("Element with locator " + by + " not found within " + timeoutInSeconds + " seconds.");
            return null;
        }
    }

    // Move to an Element
    public void moveToElement(By ele) {
        WebElement element = waitForElement(ele, 10);
        if (element != null) {
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
        }
    }

    // Generic Method to Click an Element
    protected void clickElement(By by) {
        WebElement element = waitForElement(by, 10);
        if (element != null) {
            element.click();
        }
    }

    // Generic Method to Get Multiple Elements
    public List<WebElement> getMultipleElements(By locator) {
        try {
            return driver.findElements(locator);
        } catch (Exception e) {
            System.out.println("Error finding elements: " + e.getMessage());
            return null;
        }
    }

    // Generic Method to Get Multiple Elements and click One element
    public void clickOneFromMultipleElements(By by, String title) {
        try {
            List<WebElement> elements = getMultipleElements(by);
            boolean isClicked = false;
            JavascriptExecutor js = (JavascriptExecutor) driver;

            for (WebElement element : elements) {
                if (element.getText().trim().equalsIgnoreCase(title)) {
                    js.executeScript("arguments[0].click();", element);
                    isClicked = true;
                    System.out.println("Clicked on element with title: " + title);
                    break;
                }
            }

            if (!isClicked) {
                System.err.println("Element with title '" + title + "' not found.");
            }
        } catch (Exception e) {
            System.err.println("Error clicking on element with title '" + title + "': " + e.getMessage());
        }
    }


    // Generic Method to Enter Text
    protected void enterText(By by, String text) {
        WebElement element = waitForElement(by, 10);
        if (element != null) {
            element.clear();
            element.sendKeys(text);
        }
    }

    // Generic Method to Verify Text of an Element
    protected boolean verifyElementText(By by, String expectedText) {
        WebElement element = waitForElement(by, 10);
        return element != null && element.getText().trim().equals(expectedText);
    }

    // Generic Method to Verify if an Element is Displayed
    protected boolean isElementDisplayed(By by) {
        try {
            WebElement element = driver.findElement(by);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Generic Method to Get Text
    public String getText(By locator) {
        try {
            WebElement element = waitForElement(locator, 10);
            return element.getText().trim();
        } catch (Exception e) {
            Log.error("Error: Element not found or not visible for locator: " + locator);
            return "";
        }
    }

    // Generic Method to Switch to a New Window
    public void switchToNewWindow() {
        try {
            String parentWindow = driver.getWindowHandle(); // Get the current window handle
            Set<String> allWindows = driver.getWindowHandles(); // Get all window handles

            boolean windowSwitched = false; // Flag to check if switching was successful

            for (String windowHandle : allWindows) {
                if (!windowHandle.equals(parentWindow)) {
                    driver.switchTo().window(windowHandle); // Switch to the new window
                    windowSwitched = true;
                    System.out.println("Switched to new window: " + driver.getTitle());
                    break; // Exit loop after switching to the first new window
                }
            }

            if (!windowSwitched) {
                Log.error("No new window found to switch to.");
            }
        } catch (Exception e) {
            Log.error("Error while switching to new window: " + e.getMessage());
        }
    }

    // Generic Method to Select from Dropdown
    protected void selectFromDropdown(By by, String visibleText) {
        WebElement element = waitForElement(by, 10);
        if (element != null) {
            Select dropdown = new Select(element);
            dropdown.selectByVisibleText(visibleText);
        }
    }

    // Generic method to Get Current URL
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    // Generic method to Navigate to a URL
    public void navigateToURL(String url) {
        driver.get(url);

    }

    // JavaScript Click
    public void javaScriptClick(String selector) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.cssSelector(selector));
        js.executeScript("arguments[0].click();", element);
    }

    // Capture Screenshot
    public String screenCapture(String testCaseName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
        try {
            FileUtils.copyFile(src, new File(destinationFile));
        } catch (IOException e) {
            Log.error("Screenshot capture failed: " + e.getMessage());
        }
        return destinationFile;
    }
}