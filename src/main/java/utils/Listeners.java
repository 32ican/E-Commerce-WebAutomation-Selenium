package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseClass;

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
		try {
			System.out.println("Test failed: " + result.getName());
			base.takeScreenShot(result.getMethod().getMethodName());
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
