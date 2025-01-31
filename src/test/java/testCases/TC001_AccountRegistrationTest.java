package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups= {"Regression","Master"})
	public void verify_account_registration() {
		
		logger.info("Starting TC001_AccountRegistrationTest.... ");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicking on My Account.... ");
		hp.clickRegister();
		
		logger.info("Providing Customer Details.... ");
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		String pwd=randomAlphaNumber();
		regpage.setPassword(pwd);
		regpage.setConfirmPassword(pwd);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Verifying Confirmation message....");
		
		String confirmMessge=regpage.getConfirmationMsg();
		if(confirmMessge.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed..");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
	//	Assert.assertEquals(confirmMessge, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		logger.info("Finished TC001_AccountRegistrationTest.... ");
		
		
	}
	}
	
