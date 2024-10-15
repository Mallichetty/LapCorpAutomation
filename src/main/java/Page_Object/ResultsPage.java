package Page_Object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import resources.Base;

public class ResultsPage extends Base {
    WebDriver driver;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    By firstProduct = By.xpath("//*[contains(@class,'rush-component')]/div[1]/div[2]/div");
    By secondProduct = By.xpath("//*[contains(@class,'rush-component')]/div[1]/div[3]/div");
    By firstProductPrice = By.xpath("(//*[@class='a-price'])[1]");
    By secondProductPrice = By.xpath("(//*[@class='a-price'])[2]");

    public boolean isFirstProductDisplayed() {
        return waitForElement(firstProduct).isDisplayed();
    }

    public boolean isSecondProducttDisplayed() {
        boolean value = false;
        try {
            value = waitForElement(secondProduct).isDisplayed();
        } catch (Exception e) {
            e.getMessage();
        }
        return value;
    }

    public String getfirstProductPrice() {
        return waitForElement(firstProductPrice).getText();
    }

    public String getSecondProductPrice() {
        return waitForElement(secondProductPrice).getText();
    }

    public void selectFirstProduct() {
        waitForElement(firstProduct).click();
    }

    public void selectSecondProduct() {
        waitForElement(secondProduct).click();
    }

    public String getCurrentUrl() {
        return getCurrentURL();
    }

    public void getPageURL(String url) {
        navigateToURL(url);
    }
}
