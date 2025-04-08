package Page_Object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import resources.Base;
import resources.Log;

public class ApplyPage extends Base {

    WebDriver driver;

    private By newPageJobTitle = By.cssSelector(".css-18mbozw");
    private By clickApplyManually = By.xpath("(//*[@class='css-1s1r74k'])[3]");
    private By clickBackToJobPosting = By.xpath("//*[text()='Back to Job Posting']");
    private By jobTitle = By.xpath("//h2[1]");
    private By jobLocation = By.xpath("(//*[@class='css-129m7dg'])[1]");
    private By jobId = By.xpath("(//*[@class='css-129m7dg'])[5]");

    public ApplyPage(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToNewWindow() {
        try {
            switchToNewWindow();
        } catch (Exception e) {

            Log.error("Error switching to new window: " + e.getMessage());
        }
    }

    public String getJobTitleOnNewPage() {
        try {
            return getText(newPageJobTitle);
        } catch (Exception e) {
            Log.error("Error getting Job Title: " + e.getMessage());
            return "";
        }
    }

    public void navigateToJobPostingPage() {
        try {
            clickElement(clickApplyManually);
            clickElement(clickBackToJobPosting);
        } catch (Exception e) {
            Log.error("Error clicking button: " + e.getMessage());
        }
    }

    public String getJobTitle() {
        try {
            return getText(jobTitle);
        } catch (Exception e) {
            Log.error("Error getting Job Title: " + e.getMessage());
            return "";
        }
    }

    public String getJobLocation() {
        try {
            return getText(jobLocation);
        } catch (Exception e) {
            Log.error("Error getting Job Location: " + e.getMessage());
            return "";
        }
    }

    public String getJobId() {
        try {
            return getText(jobId);
        } catch (Exception e) {
            Log.error("Error getting Job ID: " + e.getMessage());
            return "";
        }
    }

}
