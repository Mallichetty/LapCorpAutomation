Feature: LabCorp Job Listing Verification

  @Regression
  Scenario Outline: Verify job details from the Careers page
    When User click on the "Careers" link
    And User search for the position using id "<JobID>"
    And User select the job listing for "<Position>"
    Then User should see the correct Job Title as "<JobTitle>"
    And User should see the Job Location as "<JobLocation>"
    And User should see the Job ID as "<JobID>"
    And User capture job details from the Job Search page
    And User should see the first sentence of the first paragraph under Position Overview as
      | Text                   |
      | <PositionOverviewText> |
    And User should see the second bullet point under qualification as "<Qualification>"
    And User should see the third requirement as "<Requirement>"
    And User should see that the first suggested automation tool to be familiar with contains "<AutomationTool>"
    When User click on "Apply Now"
    Then User should confirm the Job Title is "<JobTitle>" on new tab
    And User should confirm the same text from the previous tab matches on this page

    Examples:
      | Position               | JobTitle               | JobLocation      | JobID  | PositionOverviewText                                                                                                                                     | Qualification                                                                           | Requirement                                                | AutomationTool |
      | Automation Engineer    | Automation Engineer    | Bangalore, India | 257322 | As a Software Automation Engineer, you will play a critical role in ensuring the quality and reliability of our web applications developed using Angular | Proven experience in software test automation, particularly with Selenium and Cucumber. | Knowledge of Behavior-Driven Development (BDD) principles. | Selenium       |
