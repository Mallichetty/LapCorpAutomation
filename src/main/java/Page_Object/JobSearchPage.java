package Page_Object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.Base;
import resources.Log;

import java.time.Duration;
import java.util.List;


public class JobSearchPage extends Base {

    WebDriver driver;
    // Locators
    private By searchBox = By.id("typehead");
    private By searchButton = By.xpath("//*[@id='ph-search-backdrop']");


    public JobSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForPosition(String positionId) {
        try {
            enterText(searchBox, positionId);
            Log.info("Entered position ID: " + positionId);

            clickElement(searchButton);
            Log.info("Clicked on the search button for position ID: " + positionId);
        } catch (Exception e) {
            Log.error("Error searching for position ID: " + positionId + ". " + e.getMessage());
        }
    }


//    public void searchForPosition(String position) {
//        try {
//            enterText(searchBox, position);
//            try {
//                List<WebElement> elements = driver.findElements(searchButton);
//                boolean isClicked = false;
//                JavascriptExecutor js = (JavascriptExecutor) driver;
//
//                System.out.println(elements.size());
//                for (WebElement element : elements) {
//                    System.out.println(element.getText());
//                    if (element.getText().equalsIgnoreCase(position)) {
//                        js.executeScript("arguments[0].click();", element);
//                        isClicked = true;
//                        System.out.println("Clicked on element with title: " + position);
//                        break;
//                    }
//                }
//
//                if (!isClicked) {
//                    System.err.println("Element with title '" + position + "' not found.");
//                }
//            } catch (Exception e) {
//                System.err.println("Error clicking on element with title '" + position + "': " + e.getMessage());
//            }
//
//        } catch (Exception e) {
//            Log.error("Error searching for position: " + e.getMessage());
//        }
//    }


}
