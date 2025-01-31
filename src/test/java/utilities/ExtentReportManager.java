package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext context) 
	{
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss");
		Date dt=new Date();
		String currenttimestamp=df.format(dt);*/
		
		String timestamp=new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date()); //create time stamp
		
		repName="Test-Report-"+timestamp+".html";
		
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName); //specifies the current location
		
		sparkReporter.config().setDocumentTitle("Automation Report"); //Title of report
		sparkReporter.config().setReportName("Functionl Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("SubModule", "Customer");
		extent.setSystemInfo("Tester Name", System.getProperty("user.name"));
		//extent.setSystemInfo("OS", "Windows11");
		//extent.setSystemInfo("Browser Name", "Edge");
		
		String os=context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System",os);
		
		String browser=context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List <String> includegroups=context.getCurrentXmlTest().getIncludedGroups();
		if(!includegroups.isEmpty()) {
			extent.setSystemInfo("Groups", includegroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName()); //create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test Case Passed: "+result.getName()+"Got Succefully Executed"); //update status p/f/s
	}
	
	public void onTestFailure(ITestResult result) 
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Case Failed:"+result.getName());
		test.log(Status.FAIL, "Test Case Failed Cause:"+result.getThrowable());
		test.log(Status.INFO, result.getThrowable().getMessage());
		try {
			String imgPath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}catch(Exception ei)
		{
			ei.printStackTrace();
		}
    }

	public void onTestSkipped(ITestResult result) 
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Case Skipped:"+result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());
		test.log(Status.SKIP, "Test Case Skipped Cause:"+result.getSkipCausedBy());
    	}
	public void onFinish(ITestContext context) 
	{
		extent.flush();	  
		
		String pathofExtentReport=System.getProperty("user.dir")+".\\reports\\"+repName;
		File extentReport=new File(pathofExtentReport);
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
