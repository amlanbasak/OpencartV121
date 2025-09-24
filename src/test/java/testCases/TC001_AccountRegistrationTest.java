package testCases;

//import statements
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regresssion","Master"})
	public void testAccountRegistration() throws InterruptedException
	{
		logger.info("***** Starting the execution of TC001_AccountRegistrationTest *****");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();

		logger.info("*** Opening Account Registration Page ***");
		AccountRegistrationPage arp = new AccountRegistrationPage(driver);
		arp.setFirstName(randomString(4).toUpperCase());
		arp.setLastName(randomString(6).toUpperCase());
		arp.setEmail(randomString(5).toLowerCase()+"@gmail.com");	
		arp.setTelephone(randomNumber(10));
		
		String password = randomAlphaNumeric(4,'@',3);
		arp.setPassword(password);
		arp.setConfirmPassword(password);
		
		arp.agreePrivacyPolicy(); 
		Thread.sleep(1000);
		arp.clickContinue();
		logger.info("*** Clicked CONTINUE button ***");

		logger.info("*** Verifying the test results ***");
		String confirmationMessage = arp.getConfirmationMessage();
		Assert.assertEquals(confirmationMessage, "Your Account Has Been Created!");
		logger.info("***** Finishing the execution of TC001_AccountRegistrationTest *****\n");
	}
	
}
