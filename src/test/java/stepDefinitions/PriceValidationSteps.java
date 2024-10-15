package stepDefinitions;

import Page_Object.DashboardPage;
import Page_Object.ProductDetailsPage;
import Page_Object.ResultsPage;
import Page_Object.SummaryPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class PriceValidationSteps {

    WebDriver driver;
    DashboardPage dashboardPage;
    ResultsPage resultsPage;
    ProductDetailsPage productDetailsPage;
    SummaryPage summaryPage;
    String firstProductPriceOnResultsPage;
    String firstProductPriceOnDetailsPage;
    String secondProductPriceOnResultsPage;
    String secondProductPriceOnDetailsPage;
    String firstAddedProductToCartPrice;
    String secondAddedProductToCartPrice;
    String totalPriceOnSummaryPage;

    public PriceValidationSteps() {
        dashboardPage = new DashboardPage(driver);
        resultsPage = new ResultsPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        summaryPage = new SummaryPage(driver);
    }

    @Given("User Verify the Amazon dashboard title")
    public void userVerifyTheAmazonDashboardTitle() {
        dashboardPage.skipCatcha();
        Assert.assertTrue("Amazon Page is not displayed", dashboardPage.verifyPageTitle());
    }

    @And("User click on search bar")
    public void userClickOnSearchBar() {
        Assert.assertTrue("Search Bar is not displayed", dashboardPage.issearchBarDisplayed());
        dashboardPage.clickSeachBar();
    }

    @Then("User search for product {string}")
    public void userSearchForProduct(String product) {
        dashboardPage.enterTextInSearchbar(product);
        dashboardPage.selectProduct(product);
    }

    @And("user clicks on the first product and add to cart")
    public void userClicksOnTheFirstProductAndAddToCart() {
        String url = resultsPage.getCurrentUrl();
        Assert.assertTrue("First product is not displayed", resultsPage.isFirstProductDisplayed());
        firstProductPriceOnResultsPage = resultsPage.getfirstProductPrice();
        System.out.println("firstProductPriceOnResultsPage :" + firstProductPriceOnResultsPage);
        resultsPage.selectFirstProduct();
        firstProductPriceOnDetailsPage = productDetailsPage.getProductPrice();
        System.out.println("firstProductPriceOnDetailsPage :" + firstProductPriceOnDetailsPage);
        Assert.assertTrue("Button is not displayed", productDetailsPage.isclickBtnDisplayed());
        productDetailsPage.clickButton();
        Assert.assertEquals("Price is mismatching on details & results page", firstProductPriceOnResultsPage, firstProductPriceOnDetailsPage);
        resultsPage.getPageURL(url);
    }

    @Then("user clicks on the second product and add to cart")
    public void userClicksOnTheSecondProductAndAddToCart() {
        Assert.assertTrue("Second product is not displayed", resultsPage.isSecondProducttDisplayed());
        secondProductPriceOnResultsPage = resultsPage.getSecondProductPrice();
        System.out.println("secondProductPriceOnResultsPage :" + secondProductPriceOnResultsPage);
        resultsPage.selectSecondProduct();
        secondProductPriceOnDetailsPage = productDetailsPage.getProductPrice();
        System.out.println("secondProductPriceOnDetailsPage :" + secondProductPriceOnDetailsPage);
        Assert.assertEquals("Price is mismatching on details & results page", secondProductPriceOnResultsPage, secondProductPriceOnDetailsPage);
        productDetailsPage.clickButton();
    }

    @And("Verify if user could see the prices matching on summary and details page")
    public void verifyIfUserCouldSeeThePricesMatchingOnSummaryAndDetailsPage() {
        summaryPage.clickGoToCartBtn();
        Assert.assertTrue("Second product is not displayed", summaryPage.isPageTitleDisplayed());
        firstAddedProductToCartPrice = summaryPage.getFirstAddedProductPrice();
        Assert.assertEquals("Price is mismatching on summary & details page", firstProductPriceOnDetailsPage, firstAddedProductToCartPrice.replace(".","\n"));
        secondAddedProductToCartPrice = summaryPage.getSecondAddedProductPrice();
        Assert.assertEquals("Price is mismatching on summary & details page", secondProductPriceOnDetailsPage, secondAddedProductToCartPrice.replace(".","\n"));
        totalPriceOnSummaryPage = summaryPage.getTotalPrice();
        Assert.assertEquals("Sum of the Added products is not matching", Integer.parseInt(totalPriceOnSummaryPage), (Integer.parseInt(firstAddedProductToCartPrice) + Integer.parseInt(secondAddedProductToCartPrice)));
    }
}
