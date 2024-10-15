package Page_Object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import resources.Base;

public class SummaryPage extends Base {

    WebDriver driver;

    public SummaryPage(WebDriver driver) {
        this.driver = driver;
    }

    By pageTitle = By.xpath("//*[contains(@class,'sc-cart-header')]");
    By firstAddedToCartPrice = By.xpath("(//*[@class='sc-item-price-block'])[2]/child::*[1]");
    By secondAddedToCartPrice = By.xpath("(//*[@class='sc-item-price-block'])[1]/child::*[1]");
    By goToCartBtn = By.xpath("//*[@id='sw-gtc']");
    By totalPrice = By.xpath("//*[@id='sc-subtotal-amount-activecart']");

    public boolean isPageTitleDisplayed() {
        return waitForElement(pageTitle).isDisplayed();
    }

    public String getFirstAddedProductPrice() {
        return waitForElement(firstAddedToCartPrice).getText();
    }

    public String getSecondAddedProductPrice() {
        return waitForElement(secondAddedToCartPrice).getText();
    }

    public String getTotalPrice() {
        return waitForElement(totalPrice).getText();
    }

    public void clickGoToCartBtn()
    {
        waitForElement(goToCartBtn).click();
    }



}
