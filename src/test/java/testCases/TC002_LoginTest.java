package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups= {"Sanity","Master"})
	public void verify_login() {
		
		try
		{
		logger.info("***  Starting TC002_LoginTest.... ***");
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		logger.info("***  Starting Login Page.... ***");
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		logger.info("***  Starting MyAccount Page.... ***");
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean targetpage=myacc.isMyAccountPageExists();
		
		Assert.assertEquals(targetpage, true, "Login Failed");
		}
		 catch(Exception e)
		{
			 Assert.fail();
		}
		
		logger.info("***  Finished MyAccount Page.... ***");
	}
	
}
