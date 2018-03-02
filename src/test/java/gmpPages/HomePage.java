package gmpPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import gmpPageObjects.HomePageObjects;
import gmpUtils.BaseClass;
import gmpUtils.WebdriverComponents;

/**
 * 
 * @author Mindtree 
 *
 */
public class HomePage {
	WebDriver driver;
	WebdriverComponents driverComponents ;
	
    public HomePage(){
        this.driver = BaseClass.driver;
        this.driverComponents = new WebdriverComponents();
    	PageFactory.initElements(driver, HomePageObjects.class);
    }
	
    /**
     * Click on Search icon in homepage
     */
	public void clickOnSearchIcon(){
		driverComponents.explicitWait(HomePageObjects.searchLogo);
		HomePageObjects.searchLogo.click();
		driverComponents.reportStep("PASS", "Clicked on Search icon");
	}
}
