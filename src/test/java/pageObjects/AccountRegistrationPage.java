package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		
	}
	

@FindBy(xpath="//input[@id='input-firstname']")
WebElement txt_Firstname;
@FindBy(xpath="//input[@id='input-lastname']")
WebElement txt_Lastname;
@FindBy(xpath="//input[@id='input-email']")
WebElement txt_Email;
@FindBy(xpath="//input[@id='input-telephone']")
WebElement txt_Telephone;
@FindBy(xpath="//input[@id='input-password']")
WebElement txt_Password;
@FindBy(xpath="//input[@id='input-confirm']")
WebElement txt_Confirmpassword;
@FindBy(xpath="//input[@name='agree']")
WebElement chkPolicy;
@FindBy(xpath="//input[@value='Continue']")
WebElement btn_Continue;

@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgConfirmation;

 public void setFirstName(String fname) {
	 txt_Firstname.sendKeys(fname);
}
 public void setLastName(String lname) {
	 txt_Lastname.sendKeys(lname);
}
 public void setEmail(String email) {
	 txt_Email.sendKeys(email);
}
 public void setTelephone(String tel) {
	 txt_Telephone.sendKeys(tel);
}
 public void setPassword(String pwd) {
	 txt_Password.sendKeys(pwd);
}
 public void setConfirmPassword(String cpwd) {
	  txt_Confirmpassword.sendKeys(cpwd);
}
 public void setPrivacyPolicy() {
	 chkPolicy.click();
}
 public void clickContinue() {
	 btn_Continue.click();
	 /*
	  sol2
	  btn_Continue.submit();
	  sol3
	  Actions act=new Actions(driver);
	  act.moveToElement(btn_Continue).click().perform();
	  
	  sol4
	  btn_Continue.sendKeys(Keys.RETURN);
	  
	  sol5
	  org.openqa.selenium.JavascriptExecutor js=(JavascriptExecutor)driver;
	  js.executeScript("arguments[0].click();", btn_Continue);
	  
	  sol6
	  WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
	  mywait.until(ExpectedConditions.elementToBeClickable(btn_Continue)).click();
	  */
}
 
 public String getConfirmationMsg() {
	 try {
		 return (msgConfirmation.getText());
	 } catch (Exception e) {
		 return e.getMessage();
	}
	 
	
	
}
}
