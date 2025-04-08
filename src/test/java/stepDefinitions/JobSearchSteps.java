package stepDefinitions;

import Page_Object.ApplyPage;
import Page_Object.DashBoardPage;
import Page_Object.JobListingPage;
import Page_Object.JobSearchPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import resources.Base;

import java.util.List;

public class JobSearchSteps extends Base {
    DashBoardPage dashBoardPage;
    JobSearchPage jobSearchPage;
    JobListingPage jobListingPage;
    ApplyPage applyNowPage;

    private String previousJobTitle;
    private String previousJobLocation;
    private String previousJobId;

    public JobSearchSteps() {
        dashBoardPage = new DashBoardPage(driver);
        jobSearchPage = new JobSearchPage(driver);
        jobListingPage = new JobListingPage(driver);
        applyNowPage = new ApplyPage(driver);
    }

    @When("User click on the {string} link")
    public void clickOnLink(String linkText) {
        if (linkText.equals("Careers")) {
            dashBoardPage.clickCareersLink();
            Assert.assertTrue("Careers link was not clicked successfully", driver.getCurrentUrl().contains("careers"));
        }
    }

    @And("User search for the position using id {string}")
    public void searchForPosition(String position) {
        jobSearchPage.searchForPosition(position);
    }

    @And("User select the job listing for {string}")
    public void selectJobListing(String jobTitle) {
        jobListingPage.selectJobListing(jobTitle);
    }

    @Then("User should see the correct Job Title as {string}")
    public void verifyJobTitle(String expectedTitle) {
        Assert.assertEquals(expectedTitle, jobListingPage.getJobTitle());
    }

    @And("User should see the Job Location as {string}")
    public void verifyJobLocation(String expectedLocation) {
        Assert.assertEquals(expectedLocation, jobListingPage.getJobLocation());
    }

    @And("User should see the Job ID as {string}")
    public void verifyJobID(String expectedJobId) {
        Assert.assertEquals(expectedJobId, jobListingPage.getJobId());
    }

    @Then("^User capture job details from the Job Search page$")
    public void captureJobDetails() {
        previousJobTitle = jobListingPage.getJobTitle();
        previousJobLocation = jobListingPage.getJobLocationForComparison();
        previousJobId = jobListingPage.getJobId();
    }

    @And("User should see the first sentence of the first paragraph under Position Overview as")
    public void verifyDescriptionIntroduction(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        String expectedText = data.get(1).get(0);
        Assert.assertEquals(expectedText, jobListingPage.getDescriptionIntroduction());
    }

    @And("User should see the second bullet point under qualification as {string}")
    public void verifyManagementSupportBullet(String expectedText) {
        Assert.assertEquals(expectedText, jobListingPage.getQualificationBullet());
    }

    @And("User should see the third requirement as {string}")
    public void verifyThirdRequirement(String expectedRequirement) {
        Assert.assertEquals(expectedRequirement, jobListingPage.getThirdRequirement());
    }

    @And("User should see that the first suggested automation tool to be familiar with contains {string}")
    public void verifyAutomationTool(String expectedTool) {
        Assert.assertTrue(jobListingPage.isSuggestedAutomationToolPresent(expectedTool));
    }

    @When("User click on {string}")
    public void clickOnButton(String buttonText) {
        jobListingPage.clickApplyNow();

    }

    @Then("User should confirm the Job Title is {string} on new tab")
    public void confirmJobTitleOnApplyPage(String expectedTitle) {
        jobSearchPage.switchToNewWindow();
        Assert.assertEquals(expectedTitle, applyNowPage.getJobTitleOnNewPage());
    }

    @And("User should confirm the same text from the previous tab matches on this page")
    public void confirmMatchingText() {
        applyNowPage.navigateToJobPostingPage();
        Assert.assertEquals(previousJobTitle, applyNowPage.getJobTitle());
        Assert.assertEquals(previousJobLocation, applyNowPage.getJobLocation());
        Assert.assertEquals(previousJobId, applyNowPage.getJobId());
    }
}
