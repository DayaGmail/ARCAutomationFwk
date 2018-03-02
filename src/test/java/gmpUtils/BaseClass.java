package gmpUtils;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.net.MalformedURLException;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;



public class BaseClass {
    public static WebDriver driver;
    private static String CHROME_MAC_DRIVER = "/src/test/resources/drivers/chromedriver";
    private static String CHROME_WINDOWS_DRIVER = "/src/test/resources/drivers/chromedriver";
    private static String CHROME_LINUX_DRIVER = "/src/test/resources/drivers/chromedriver";

    DesiredCapabilities dc = new DesiredCapabilities();
    
    @Before
    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */
    public void openBrowser() throws MalformedURLException {

        dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        //dc.setJavascriptEnabled(true);
        String gridUrl = "https://turbo-seleniumgrid.hesos.net/wd/hub";

        String browser= BaseClass.readProperties( System.getProperty("user.dir") + "/src/test/resources/properties/config.properties", "browser");

        if (browser == null) {
            browser = "chrome";
        }
        if (browser.equals("chrome")) {

            if (System.getProperty("os.name").contains("Mac")) {

                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + CHROME_MAC_DRIVER);

            } else if (System.getProperty("os.name").contains("Linux")) {
                System.setProperty("webdriver.chrome.driver",CHROME_LINUX_DRIVER);
            }
            else {
                System.setProperty("webdriver.chrome.driver", CHROME_WINDOWS_DRIVER);

            }

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--ignore-certificate-errors");
            driver = new ChromeDriver(options);

           // driver = new ChromeDriver(dc);

        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver(dc);


        } else if (browser.equals("ie")) {
            driver = new InternetExplorerDriver(dc);

        } else if (browser.equals("gridChrome")) {
            dc.setBrowserName("chrome");
            dc.setPlatform(Platform.LINUX);
            driver = new RemoteWebDriver(new URL(gridUrl), dc);

        } else if (browser.equals("safari")) {
            driver = new SafariDriver(dc);

         }
        System.out.println("Opening Browser...." + browser);
        driver.manage().deleteAllCookies();
    }

    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + driver.getCurrentUrl());
                //byte[] screenshot = getScreenshotAs(OutputType.BYTES);
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
        driver.quit();
    }
    
    /**
     * 
     * @param filepath : Path of properties file
     * @param key : What is the key you are searching for
     * @return
     */
	public static String readProperties(String filepath,String key)
	{
		String value=null;
		File f=new File(filepath);
		FileInputStream fis;
		
		try
		{
			fis=new FileInputStream(f);
			Properties prop=new Properties();
			prop.load(fis);
			value=prop.getProperty(key);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return value;
	}

}