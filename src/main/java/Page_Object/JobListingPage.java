package Page_Object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import resources.Base;
import resources.Log;

public class JobListingPage extends Base {

    WebDriver driver;

    private By jobListing = By.xpath("//*[@class='job-title']");
    private By jobTitle = By.className("job-title");
    private By jobLocation = By.xpath("//*[@class='job-other-info']/span[2]");
    private By jobId = By.xpath("//*[@class='job-other-info']/span[3]");
    private By descriptionIntroduction = By.xpath("//*[@class='jd-info au-target']/p[1]");
    private By qualficationBullet = By.xpath("(//p[text()='Qualifications:'])[1]/following::*[1]/li[2]/span[1]");
    private By thirdRequirement = By.xpath("(//p[text()='Preferred Qualifications:'])[1]/following::*[1]/li[2]/span[1]");
    private By suggestedAutomationTool = By.xpath("//p[text()='Key Responsibilities:']/following::p/span[1]");
    private By applyNowButton = By.xpath("//*[@class='job-bottom-action']/a");


    public JobListingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectJobListing(String title) {
        try {
            clickOneFromMultipleElements(jobListing, title);
        } catch (Exception e) {
            Log.error("Error selecting job listing: " + e.getMessage());
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
            String jobLocationText = getText(jobLocation);
            if (jobLocationText != null && jobLocationText.contains("Location")) {
                return jobLocationText.replace("Location", "").trim();
            }
            return jobLocationText.trim();
        } catch (Exception e) {
            Log.error("Error getting Job Location: " + e.getMessage());
            return "";
        }
    }

    public String getJobLocationForComparison() {
        try {
            String jobLocationText = getJobLocation();
            if (jobLocationText != null) {
                return jobLocationText.replace(",", "").trim();
            }
            return "";
        } catch (Exception e) {
            Log.error("Error getting Job Location: " + e.getMessage());
            return "";
        }
    }


    public String getJobId() {
        try {
            String jobIdText = getText(jobId);
            if (jobIdText != null && jobIdText.contains("Job ID :")) {
                return jobIdText.replace("Job ID :", "").trim();
            }
            return jobIdText.trim();
        } catch (Exception e) {
            Log.error("Error getting Job ID: " + e.getMessage());
            return "";
        }
    }

    public String getDescriptionIntroduction() {
        try {
            moveToElement(descriptionIntroduction);
            String para = getText(descriptionIntroduction);
            if (para != null && !para.isEmpty()) {
                String[] sentences = para.split("\\.", 2);
                if (sentences[0] != null && sentences[0].contains("Position Overview:")) {
                    return sentences[0].replace("Position Overview:", "").trim();
                }
                return sentences[0].trim();
            }
            return "";
        } catch (Exception e) {
            Log.error("Error getting Description/Introduction: " + e.getMessage());
            return "";
        }
    }

    public String getQualificationBullet() {
        try {
            moveToElement(qualficationBullet);
            return getText(qualficationBullet);
        } catch (Exception e) {
            Log.error("Error getting second Management Support bullet: " + e.getMessage());
            return "";
        }
    }

    public String getThirdRequirement() {
        try {
            moveToElement(thirdRequirement);
            return getText(thirdRequirement);
        } catch (Exception e) {
            Log.error("Error getting third requirement: " + e.getMessage());
            return "";
        }
    }

    public boolean isSuggestedAutomationToolPresent(String tool) {
        try {
            return getText(suggestedAutomationTool).contains(tool);
        } catch (Exception e) {
            Log.error("Error checking for suggested automation tool: " + e.getMessage());
            return false;
        }
    }

    public void clickApplyNow() {
        try {
            moveToElement(applyNowButton);
            clickElement(applyNowButton);
        } catch (Exception e) {
            Log.error("Error clicking Apply Now button: " + e.getMessage());
        }
    }
}
