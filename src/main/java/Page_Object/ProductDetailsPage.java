package Page_Object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import resources.Base;

public class ProductDetailsPage extends Base {
    WebDriver driver;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    By productPrice = By.xpath("(//*[@class='a-box-inner'])[1]/div/div[3]");
    By addToCartButton = By.xpath("//*[@id='add-to-cart-button']");

    public String getProductPrice() {
        String price = "";
        try {
            price = waitForElement(productPrice).getText();
        } catch (Exception e)
        {
            e.getMessage();
        }
        return price;
    }

    public boolean isclickBtnDisplayed() {
        return waitForElement(addToCartButton).isDisplayed();
    }

    public void clickButton() {
        try {
            waitForElement(addToCartButton).click();
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
