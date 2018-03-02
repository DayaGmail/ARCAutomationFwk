package gmpPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.DataTable;
import gmpPageObjects.SearchPageObjects;
import gmpUtils.BaseClass;
import gmpUtils.WebdriverComponents;

public class SearchPage {
	WebDriver driver;
	WebdriverComponents driverComponents;
	
    public SearchPage(){
        this.driver = BaseClass.driver;
        this.driverComponents = new WebdriverComponents();
        PageFactory.initElements(driver, SearchPageObjects.class);
    }
	
    /**
     * Verify if Search page is Opened
     */
	public void verifySearchPage() {
		driverComponents.explicitWait(SearchPageObjects.searchCriteria);
		SearchPageObjects.searchCriteria.isDisplayed();
		driverComponents.reportStep("PASS", "Search page Verified");
	}
	
	/**
	 * Verify if all the Elements mentioned in Feature file is Present
	 * @param datatable : List of elements from the Feature file
	 * @throws InterruptedException
	 */
	public void verifyElementsInSearchPage(DataTable datatable) throws InterruptedException{
		
		Thread.sleep(3000);
		 List<String> listofelements = datatable.asList(String.class);
		    for (String AllSearchItems : listofelements) {
		    	try {
			    	WebElement searchElement = driver.findElement(By.xpath("//input[ @placeholder ='"+AllSearchItems+"']"));
			    	if(driverComponents.explicitWait(searchElement)){
			    		searchElement.isDisplayed();
				    	driverComponents.reportStep("PASS", "Verified that '"+AllSearchItems+"' is present");	
			    	}
			    	
				} catch (Exception e) {
					driverComponents.reportStep("FAIL", "Verified that '"+AllSearchItems+"' is not present");
					e.printStackTrace();
				}
		    }
	}

	public void searchUsingData(DataTable datatable) throws InterruptedException {
		
		Thread.sleep(3000);
		List<List<String>> data = datatable.asLists(String.class);
		    for (List<String> searchData : data) {
		    	try {
			    	WebElement searchElement = driver.findElement(By.xpath("//input[ @placeholder ='"+searchData.get(0)+"']"));
			    	if(driverComponents.explicitWait(searchElement)){
			    		searchElement.isDisplayed();
			    		driverComponents.sendKeys(searchElement, searchData.get(1));
				    	driverComponents.reportStep("PASS", "Entered text '"+searchData.get(1)+"' in Text box '"+searchData.get(0) +"'");	
			    	}
			    	
				} catch (Exception e) {
					driverComponents.reportStep("FAIL", "Could not Enter text '"+searchData.get(1)+"' in Text box '"+searchData.get(0) +"'");
					e.printStackTrace();
				}
		    }
		    
		   SearchPageObjects.searchButton.click();	
	}
	
	public void verifySearchResults(String searchResultNumber ,DataTable firstrow) throws InterruptedException {
		
		WebElement searchElement= null;
		
		//First Row Validation
		Thread.sleep(3000);
		 List<String> items = firstrow.asList(String.class);
		 for (int i = 0; i < items.size(); i++) {
				 try {

			    		if(i == 0 | i == 7| i == 9) {
							 driverComponents.reportStep("INFO", " Gift id, Appeal, Gift status is not entered");
						 }
			    		else{
						 	if (i == 1 | i == 6){
						 		searchElement = driver.findElement(By.xpath("//mat-row[1]/mat-cell/span[contains(text(), '"+items.get(i)+"')]"));
						 	}
						 	else if(i == 2 | i == 3 | i == 5 | i == 8){
						 		searchElement = driver.findElement(By.xpath("//mat-row[1]/mat-cell[contains(text(), '"+items.get(i)+"')]"));
						 	}
						 	if(driverComponents.explicitWait(searchElement)){
					    		searchElement.isDisplayed();
						    	driverComponents.reportStep("PASS", "Verified that '"+items.get(i)+"' is present in first Row");	
					    	}
			    		}
				    	
					} 
				 catch (Exception e) {
						driverComponents.reportStep("FAIL", "Verified that '"+items.get(i)+"' is not present in first row");
						e.printStackTrace();
						}
		}
		 
		 //Verify that Search results is Got by search Result
		 try {
			 driverComponents.explicitWait(driver.findElement(By.xpath("//span/mat-panel-title/div/span[contains(text(), '"+searchResultNumber+"')]")));
			 SearchPageObjects.searchCriteria.isDisplayed();
			 driverComponents.reportStepWithScreenShot("PASS", "Search Results is "+searchResultNumber+"");
			
		} catch (Exception e) {
			driverComponents.reportStepWithScreenShot("FAIL", "Search Results "+searchResultNumber+" is not found");
		}
	}

}
