Feature: Validation of Product price on Search Results page, Product Details page & Total Summary page

  @Regression
  Scenario: Comparison of product price
    Given User Verify the Amazon dashboard title
    And User click on search bar
    Then User search for product "toys"
    And user clicks on the first product and add to cart
    Then user clicks on the second product and add to cart
    And Verify if user could see the prices matching on summary and details page