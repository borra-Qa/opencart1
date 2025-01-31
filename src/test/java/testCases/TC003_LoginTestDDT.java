package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginTestDDT extends BaseClass{
	

	@Test(dataProvider = "logindata",dataProviderClass = DataProviders.class,groups="DataDriven")
	public void verfy_LoginTestDDT(String email, String pwd,String exp)
	{
		try {
			
		logger.info("**** Starting TC003_LoginTestDDT.... ****");
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
/* Data is valid - login success -test passed
 * Data is valid -login failed -test fail
 * 
 * Data is invalid -login success-tests fail
 * Data is invalid -login failed --test pass
 * */
		
		logger.info("**** Starting Home Page....**** ");
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean targetpage=myacc.isMyAccountPageExists();
	
		logger.info("**** Varifying Valid case....**** ");
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetpage==true)
			{		
			myacc.clickLogout();
			Assert.assertTrue(true);
			}
			else
			Assert.assertTrue(false);
		}
		
		logger.info("**** Verifying Invalid case....**** ");
		if(exp.equalsIgnoreCase("Invalid")) 
		{
		if(targetpage==true)
		{
			myacc.clickLogout();
			Assert.assertTrue(false);
		}
		else
			Assert.assertTrue(true);
		}
		
	} catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("**** Finished TC003_LoginTestDDT....**** ");
	}
}


