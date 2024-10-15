package Page_Object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import resources.Base;
import resources.Log;

import java.util.List;

public class DashboardPage extends Base {

    WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    By pageTitle = By.xpath("//*[@id='nav-logo']");
    By searchBar = By.xpath("//*[@id='twotabsearchtextbox']");
    By searchBarResultTab = By.xpath("//*[@class='left-pane-results-container']/div");
    By cartButton = By.xpath("//*[@id='nav-cart')]");
    By ignoreCaptha = By.xpath("//*[text()='Try different image']");

    public boolean verifyPageTitle() {
        return waitForElement(pageTitle).isDisplayed();
    }

    public boolean issearchBarDisplayed() {
        return waitForElement(searchBar).isDisplayed();
    }

    public void clickSeachBar() {
        moveToElement(searchBar);
        waitForElement(searchBar).click();

    }

    public void enterTextInSearchbar(String product) {
        try {
            sendKeys(product);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }

    public void selectProduct(String item) {
        try {
            waitForElement(searchBarResultTab);
            List<WebElement> productsList = getMultipleElements(searchBarResultTab);
            for (WebElement product : productsList) {
                if (product.getText().equalsIgnoreCase(item)) {
                    product.click();
                    break;
                }
            }
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }

    public void clickCartBtn() {
        try {
            waitForElement(cartButton).click();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public void skipCatcha() {
        waitForElement(ignoreCaptha).click();
    }

}
