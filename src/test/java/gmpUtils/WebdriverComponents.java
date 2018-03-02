package gmpUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.LogStatus;

public class WebdriverComponents {
	WebDriver driver;
    public static String url;
    
    public WebdriverComponents(){
        this.driver = BaseClass.driver;
    }
	
    /**
     * Open URL with user name and password specified in Properties file
     */
	public void navigateToURL(){
		try {
			
	        String env = BaseClass.readProperties( System.getProperty("user.dir") + "/src/test/resources/properties/config.properties", "env");
	        String un = BaseClass.readProperties( System.getProperty("user.dir") + "/src/test/resources/properties/config.properties", "un");
	        String pass = BaseClass.readProperties( System.getProperty("user.dir") + "/src/test/resources/properties/config.properties", "pass");

	        if (env == null)
	            env = "dev";
	        switch (env) {
	            case "dev":
	                url = "http://"+ un +":"+pass+"@rcowebtst006/Dev/GMP_APP/";
	                break;
	            case "qa":
	                url = "http://rcowebtst006/GMP/";
	                break;
	            case "prod":
	                url = "http://rcowebtst006/GMP/";
	                break;
	            default:
	                url = "http://rcowebtst006/GMP/";
	                break;
	        }
			driver.get(url);
	        driver.manage().window().setSize(new Dimension(1920, 760));
	        driver.manage().window().maximize();
	        reportStep("PASS", "Navigate to GMP Website");
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("FAIL", "Could not Navigate to GMP Website");
		}
	}
	
    /**
     * Method to check  if element is present within specified time
     * @param timeout
     * @param element
     * @return
     */
    public boolean waitForElementPresent(int timeout, WebElement element) {

        boolean elementPresent = false;
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);

        try {
            elementPresent = element.isDisplayed();
        } catch (Exception ex) {
            System.out.println("Exception occured while finding element, Element is not found");
            reportStep("FAIL", element+" <- this element is not found");
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return elementPresent;
    }

    /**
     * Wait for element 
     * @param locator webelement needs to be passed
     * @return
     */
	public boolean explicitWait(WebElement locator) {
		boolean elementPresent = false;
		try {
			 
			WebDriverWait wait = new WebDriverWait(driver, 20);
			if(wait.until(ExpectedConditions.visibilityOf(locator)) != null)
			{
				elementPresent = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("FAIL", "Element not Found after waiting for 20 seconds");
		}
		return elementPresent;
	}

	/**
	 * Switch to Parent window
	 * @param driver 
	 * @param parentWindow 
	 */
	public void switchToParentWindow(WebDriver driver, String parentWindow) {
		try {
			driver.switchTo().window(parentWindow);
		} catch (Throwable t) {
			t.printStackTrace();
			reportStep("FAIL", "Could not Navigate to parent window");
		}
	}

	public void switchToWindow(WebDriver driver) {
		try {
			Set<String> windows = driver.getWindowHandles();
			for (String window : windows) {
				driver.switchTo().window(window);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to handle alert box
	 */
	public void handleAlert() {

		try {

			Alert alert = driver.switchTo().alert();
			alert.accept();

		} catch (Exception ex) {

			System.out.println("Execption occured during alert handle");

		}
	}
	
	/**
	 * Method to Authenticate Popup
	 */
	public void authenticatePopup(String username , String password) {

		try {
			Alert alert = driver.switchTo().alert();
			WebDriverWait wait = new WebDriverWait(driver, 10);      
			wait.until(ExpectedConditions.alertIsPresent());     
			alert.authenticateUsing(new UserAndPassword(username, password));

		} catch (Exception ex) {

			System.out.println("Execption occured during alert handle");

		}
	}
	
	/**
	 * Method to send keys to text element
	 * 
	 * @param elementBy
	 * @param typeValue
	 * @throws InterruptedException
	 */
	public void sendKeys(WebElement element, String typeValue) throws Exception {

		explicitWait(element);
			clearElement(element);
			Thread.sleep(100);
			element.sendKeys(typeValue);
			reportStep("PASS", "entered '"+typeValue+"' in text box");
	
	}
	
	/**
	 * Method to clear text element
	 * 
	 * @param elementBy
	 */
	public void clearElement(WebElement element) {

		element.sendKeys(Keys.HOME);
		element.sendKeys("");
		element.clear();
	}
	
	/**
	 * Report  for a Step, Only for fail case it will take screenshot. Pass case there wont be screen shot
	 * @param result : enter Either PASS/FAIL (ignore case)
	 * @param step : Need to enter Step Details
	 */
	public void reportStep(String result, String step){
		
		if (result.equalsIgnoreCase("PASS")){
		ExtentTestManager.getTest().log(LogStatus.PASS, "click", step);
		}
		
		else if(result.equalsIgnoreCase("FAIL")){
		ExtentTestManager.getTest().log(LogStatus.FAIL, "click", step
                + ExtentManager.addScreenCapture((ExtentManager.createScreenshot(driver))));
		}
		
		else if(result.equalsIgnoreCase("INFO")){
			ExtentTestManager.getTest().log(LogStatus.INFO, "click", step
	                + ExtentManager.addScreenCapture((ExtentManager.createScreenshot(driver))));
			}
		
		else{
		ExtentTestManager.getTest().log(LogStatus.WARNING, "click", step
                + ExtentManager.addScreenCapture((ExtentManager.createScreenshot(driver))));
		}

	}
	
	/**
	 * Report  for a Step, take screenshot for PASS too
	 * @param result : enter Either PASS/FAIL (ignore case)
	 * @param step : Need to enter Step Details
	 */
	public void reportStepWithScreenShot(String result, String step){
		
		if (result.equalsIgnoreCase("PASS")){
		ExtentTestManager.getTest().log(LogStatus.PASS, "click", step
        	+ ExtentManager.addScreenCapture((ExtentManager.createScreenshot(driver))));
		}
		
		else if(result.equalsIgnoreCase("FAIL")){
		ExtentTestManager.getTest().log(LogStatus.FAIL, "click", step
                + ExtentManager.addScreenCapture((ExtentManager.createScreenshot(driver))));
		}
		
		else{
		ExtentTestManager.getTest().log(LogStatus.WARNING, "click", step
                + ExtentManager.addScreenCapture((ExtentManager.createScreenshot(driver))));
		}

	}
}

