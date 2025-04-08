Selenium with Cucumber
BDD framework for automation using Selenium Cucumber

**The framework has following features :**

* Modular Design
* Maven based framework
* Log4j enabled for logging
* Report Generation (cucumber-reporting & Allure Report)
* Base class to handle driver methods and browser configuration such as (Chrome, Firefox etc)
* Centralized Configuration (Using Properties file)
* POM
* Hooks for browser initialisation (using tag @before, @after)

**Add the feature File :**
Add the feature file under test/java/featurefiles

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


Create the Runner
Add the test runner file under test/java/cucumberOptions

	import org.junit.runner.RunWith;
	import io.cucumber.junit.Cucumber;
	import io.cucumber.junit.CucumberOptions;

	@RunWith(Cucumber.class)
	@CucumberOptions(
	
			features = "src/test/java/featureFiles",
        glue = {"stepDefinitions"},
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "pretty",
                "html:target/cucumber-reports/cucumber.html"},
        monochrome = true
	
	)
	public class TestRunner {
	}

**To see this whole thing running simply checkout this project and run this command:**

mvn clean test

****To generate the Allure report, switch to root project and run this command:**

allure serve

