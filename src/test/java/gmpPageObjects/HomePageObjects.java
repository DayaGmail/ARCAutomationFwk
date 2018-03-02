package gmpPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * @author Mindtree 
 */
public class HomePageObjects {

	@FindBy(xpath="//span//mat-icon[contains(text() ,'search') and @class = 'mat-icon material-icons']")
	public static WebElement searchLogo;
	
}
