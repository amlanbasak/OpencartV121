package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTest extends BaseClass{

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="Datadriven")
	public void testLoginDDT(String email, String password, String validity) throws InterruptedException
	{
		logger.info("***** Starting TC003_LoginDataDrivenTest *****");
		logger.info("Test Parameters are: " + "Email="+ email + " Password=" + password + " Validity=" + validity);

		//Home Page
		logger.info("*** Opening Home Page ***");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login Page
		logger.info("*** Opening Login Page ***");
		LoginPage lp = new LoginPage(driver);
		lp.setEmailAddress(email);
		lp.setPassword(password);
		Thread.sleep(1000);
		lp.clickLogin();
		
		//My Account Page
		logger.info("*** Opening My Account Page after login***");
		MyAccountPage myaccp = new MyAccountPage(driver);
		Boolean loginSuccess = myaccp.isMyAccountPageHeaderDisplayed();
		
		if(validity.equalsIgnoreCase("Valid"))
		{
			if(loginSuccess==true)
			{
				myaccp.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(validity.equalsIgnoreCase("Invalid"))
		{
			if(loginSuccess==true)
			{
				myaccp.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}

		
		logger.info("***** Finishing TC003_LoginDataDrivenTest *****\n");
	}
	
}
