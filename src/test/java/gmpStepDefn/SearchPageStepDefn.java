package gmpStepDefn;


import org.openqa.selenium.WebDriver;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import gmpPages.SearchPage;
import gmpUtils.WebdriverComponents;


public class SearchPageStepDefn {
	public static WebDriver driver;

	SearchPage searchPage = new SearchPage();
	WebdriverComponents webcomp = new WebdriverComponents();

	@Then("^I Should View Search Page$")
	public void i_Should_View_Search_Page() throws Throwable {
	searchPage.verifySearchPage();
	}

	@Then("^I Should see all the below links$")
	public void i_Should_see_all_the_below_links(DataTable datatable) throws Throwable {
		searchPage.verifyElementsInSearchPage(datatable);
	}
	
	@Then("^I search using below Inputs:$")
	public void i_search_using_below_Inputs(DataTable datatable) throws Throwable {
		searchPage.searchUsingData(datatable);
	}
	
	@Then("^I should view Search Results as\"([^\"]*)\" and should have these below results in First row$")
	public void i_should_view_Search_Results_as_and_should_have_these_below_results_in_First_row(String arg1, DataTable datatable) throws Throwable {
		searchPage.verifySearchResults(arg1, datatable);
	}

}
