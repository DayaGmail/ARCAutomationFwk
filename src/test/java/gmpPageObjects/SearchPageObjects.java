package gmpPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPageObjects {
	
	@FindBy(xpath="//input[ @placeholder ='Gift Amount (From)']")
	public static WebElement giftAmountFromTextBox;
	
	@FindBy(xpath="//input[ @placeholder ='Gift Amount (To)']")
	public static WebElement giftAmountToTextBox;
	
	@FindBy(xpath="//div[@class = 'searchhdg'and contains(text() , 'Search Criteria')]")
	public static WebElement searchCriteria;
	
	@FindBy(xpath="//div[@class = 'searchhdg'and contains(text() , 'Search Criteria')]/mat-icon[@class='mat-icon material-icons iconexp']")
	public static WebElement searchCriteriaExpanded;
	
	@FindBy(xpath="//div[@class = 'searchhdg'and contains(text() , 'Search Criteria')]/mat-icon[@class='mat-icon material-icons iconclps']")
	public static WebElement searchCriteriaColapsed;
	
	@FindBy(xpath="//span/mat-panel-title/div/text()[2]")
	public static WebElement searchResults;
	
	@FindBy(xpath="//span/mat-panel-title/div/span[contains(text(), '9')]")
	public static WebElement searchResultsNumber;
	
	@FindBy(xpath="//span[contains(text(), ' SEARCH')]")
	public static WebElement searchButton;
	
	@FindBy(xpath="(//div[contains(text() , 'No results found')])[1]")
	public static WebElement noResultsFound;
	
}

