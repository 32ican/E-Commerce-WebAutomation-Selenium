package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseClass;

public class ExtentManager {

	private static ExtentReports extentReport;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public static ExtentReports getReport() {
		if (extentReport == null) {
			setUpReport("Automation Project");
		}
		return extentReport;
	}

	public static ExtentReports setUpReport(String name) {
		extentReport = new ExtentReports();
		String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		String path = System.getProperty("user.dir") + "\\test-output\\reports\\" + name + "_" + dateTime + ".html";
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		extentReport.attachReporter(spark);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Extent Report Demo");

		return extentReport;
	}

	public static void flushReport() {
		extentReport.flush();
	}

	public static ExtentTest getTest() {
	
		return extentTest.get();
	}

	public static ExtentTest createTest(String name, String description) {
		ExtentTest test = extentReport.createTest(name);
		extentTest.set(test);
		return extentTest.get();

	}

	public static void log(String message) {
		ExtentTest test = getTest();
		if (test != null) {
			test.info(message);
		} else {
			System.err.println("ExtentTest is null. Cannot log message: " + message);
		}
	}

	public static void pass(String message) {
		ExtentTest test = getTest();
		if (test != null) {
			test.pass(message);
		} else {
			System.err.println("ExtentTest is null. Cannot log pass message: " + message);
		}
	}

	public static void fail(String message) {
		ExtentTest test = getTest();
		if (test != null) {
			test.fail(message);
		} else {
			System.err.println("ExtentTest is null. Cannot log fail message: " + message);
		}
	}

	public static void attachImage() {
		BaseClass base = new BaseClass();
		String screenshotPath = base.getScreenShotPath();
		ExtentTest test = getTest();
		if (test != null) {
			try {
				test.addScreenCaptureFromPath(screenshotPath);
				System.out.println("Screenshot added........");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("ExtentTest is null. Cannot attach image.");
		}
	}

}
