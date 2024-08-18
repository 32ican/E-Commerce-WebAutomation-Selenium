package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseClass;
import base.Hooks;
import io.cucumber.java.Scenario;

public class Listeners implements ITestListener {

	private BaseClass base = new BaseClass();

	public synchronized void onStart(ITestContext context) {
		ExtentManager.getReport();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		ExtentManager.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		
		ExtentManager.getTest().fail(result.getThrowable());
		System.out.println("I'm onTestFailure methot...........*");
		try {
			System.out.println("Test failed: " + result.getName());
			base.takeScreenShot(result.getMethod().getMethodName());
			//System.out.println("*********I am take screenshot method and have been called from the listener clas.............");
			ExtentManager.attachImage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		ExtentManager.getTest().pass("Test passed: " + result.getName());
		
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		ExtentManager.flushReport();
	}

}
