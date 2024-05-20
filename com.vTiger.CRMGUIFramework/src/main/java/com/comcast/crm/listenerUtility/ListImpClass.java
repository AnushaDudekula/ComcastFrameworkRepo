package com.comcast.crm.listenerUtility;

import java.io.File;


import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.BaseTest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListImpClass implements ITestListener ,ISuiteListener{
     public ExtentReports report;
     public static ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		System.out.println("report configuration");
		String time=new Date().toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark=new ExtentSparkReporter("./Advance/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);
		//add ENV information & create test
	    report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
		
	}

	@Override   
	public void onFinish(ISuite suite) {
		System.out.println("report backup");
		report.flush();

	}

	@Override
	public void onTestStart(ITestResult result) {
	System.out.println("TestName========="+result.getMethod().getMethodName()+"==========start");
	 test=report.createTest(result.getMethod().getMethodName());
	 UtilityClassObject.setTest(test);
	 test.log(Status.INFO, result.getMethod().getMethodName()+"===Started====");
	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("TestName========="+result.getMethod().getMethodName()+"=============end");
		test.log(Status.INFO, result.getMethod().getMethodName()+"===COMPLETED====");
	}
	

	@Override
	public void onTestFailure(ITestResult result) {
	String testName=result.getMethod().getMethodName();
	TakesScreenshot edriver=(TakesScreenshot)(BaseClass.sdriver);
	String srcfile=edriver.getScreenshotAs(OutputType.BASE64);
	String time=new Date().toString().replace(" ", "_").replace(":", "_");
	test.addScreenCaptureFromBase64String(srcfile,testName+"=="+time);
	test.log(Status.INFO, result.getMethod().getMethodName()+"===FAILED====");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}
	
	
}