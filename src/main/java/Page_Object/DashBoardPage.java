package Page_Object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import resources.Base;
import resources.Log;


public class DashBoardPage extends Base {

    WebDriver driver;
    // Locators
    private By cookieLink = By.xpath("//*[@id='onetrust-reject-all-handler']");
    private By careersLink = By.linkText("Careers");

    public DashBoardPage(WebDriver driver) {
        this.driver = driver;
    }

    private void handleCookies() {
        try {
            waitForElement(cookieLink,20);
            if (isElementDisplayed(cookieLink)) {
                clickElement(cookieLink);
                Log.info("Cookies dismissed successfully.");
            }
        } catch (Exception e) {
            Log.error("Cookies not displayed or error handling cookies: " + e.getMessage());
        }
    }

    public void clickCareersLink() {
        try {
            handleCookies();
            clickElement(careersLink);
        } catch (Exception e) {
            Log.error("Error clicking Careers link: " + e.getMessage());
        }
    }
}
