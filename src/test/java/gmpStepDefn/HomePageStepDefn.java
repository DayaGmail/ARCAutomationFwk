package gmpStepDefn;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import gmpPages.HomePage;
import gmpUtils.ExtentManager;
import gmpUtils.ExtentTestManager;
import gmpUtils.WebdriverComponents;


public class HomePageStepDefn {


	WebdriverComponents webcomp = new WebdriverComponents();
	HomePage homepage = new HomePage();
	
	@Before
    public void beforeScenario(Scenario scenario) {
           ExtentTestManager.startTest(scenario.getName());
    }

	@Given("^I navigate to GMP$")
	public void i_navigate_to_GMP() throws Throwable {
		webcomp.navigateToURL();
	}
	
	@When("^I click on Search icon$")
	public void i_click_on_Search_icon() throws Throwable {
		homepage.clickOnSearchIcon();
	}
	
	@After
    public void afterScenario(Scenario scenario) {
           ExtentTestManager.endTest();
           ExtentManager.getInstance().flush();
    }

}
