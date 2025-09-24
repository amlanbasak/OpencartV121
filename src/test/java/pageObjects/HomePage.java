package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnkMyAccountWE;	
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkRegisterWE;
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement lnkLoginWE;
	
	public void clickMyAccount()
	{
		lnkMyAccountWE.click();
	}

	public void clickRegister()
	{
		lnkRegisterWE.click();
	}

	public void clickLogin()
	{
		lnkLoginWE.click();
	}
}
