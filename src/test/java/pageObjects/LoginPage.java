package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmailAddressWE;	
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPasswordWE;
	@FindBy(xpath="//input[@value='Login']") WebElement btnLoginWE;
	
	public void setEmailAddress(String email)
	{
		txtEmailAddressWE.sendKeys(email);
	}

	public void setPassword(String password)
	{
		txtPasswordWE.sendKeys(password);
	}

	public void clickLogin()
	{
		btnLoginWE.click();
	}
}
