package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity", "Master"})
	public void testLogin() throws InterruptedException
	{
		logger.info("***** Starting TC002_LoginTest *****");
		
		//Home Page
		logger.info("*** Opening Home Page ***");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login Page
		logger.info("*** Opening Login Page ***");
		LoginPage lp = new LoginPage(driver);
		lp.setEmailAddress(systemProperties.getProperty("email"));
		lp.setPassword(systemProperties.getProperty("password"));
		Thread.sleep(2000);
		lp.clickLogin();
		
		//My Account Page
		logger.info("*** Opening My Account Page after login***");
		MyAccountPage myaccp = new MyAccountPage(driver);
		Boolean loginSuccess = myaccp.isMyAccountPageHeaderDisplayed();
		Assert.assertEquals(true, loginSuccess);
		
		logger.info("***** Finishing TC002_LoginTest *****\n");
	}

}
