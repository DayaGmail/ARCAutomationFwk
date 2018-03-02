package gmpUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

public class ExtentManager {
	private static ExtentReports instance;
	// extent report
		public static String extentReportPath = System.getProperty("user.dir") + "/Reports/";
		public static String screenshotPath = System.getProperty("user.dir") + "/Reports/Screenshots/"; 
	
	public static synchronized ExtentReports getInstance() {
		if (instance == null) {
			System.out.println(System.getProperty("user.dir"));
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_h_mm_ss_a");
			String time = sdf.format(date);
			instance = new ExtentReports(extentReportPath + "AutomationReport_" + time + ".html");
		}
		
		return instance;
	}
	
	/**
	 * Add the screenshot link in extent report
	 */
	public static String addScreenCapture(String imagePath) {
		
		String img1 = " <a class=\"right\" href=\"" + imagePath + "\" onclick=\"window.open(this.href);return false;\" >Screenshot</a> ";
		return img1;
	}

	/**
	 * Capture the screen shot
	 */
	public static String createScreenshot(org.openqa.selenium.WebDriver driver) {

		//UUID uuid = UUID.randomUUID();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_h_mm_ss_a");
		String formattedDate = sdf.format(date);
		
		try {

			/*
			 * Screenshot screenshot = new AShot().shootingStrategy(new
			 * ViewportPastingStrategy(500)) .takeScreenshot(driver);
			 * BufferedImage image = screenshot.getImage(); ImageIO.write(image,
			 * "png", new File(TC.screenshotPath + uuid + ".png"));
			 */

			File scrFile = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(screenshotPath + "Screenshot_"+formattedDate + ".png"));

		} catch (IOException e) {
			System.out.println("Error while generating screenshot:\n" + e.toString());
			//Logs.Ulog("Error while generating screenshot:\n" + e.toString());
		}
		return screenshotPath + "Screenshot_"+formattedDate + ".png";
	}
}
