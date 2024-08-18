package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.ConfigLoader;
import org.testng.annotations.Listeners;
import org.apache.commons.io.FileUtils;

@Listeners(utils.Listeners.class)
public class BaseClass {

	protected static WebDriver driver;
	private static String  screenshotPath;

	// public WebDriver driver = getDriver();
	public static void getDriver(String browser) {
			 intailizeDriver(browser);
		
	}

	private static void intailizeDriver(String browser) {
		
		switch (browser.toLowerCase()) {
		case "chrome":
			 driver = new ChromeDriver();
			 break;

		case "firefox":
			 driver = new FirefoxDriver();
			 break;

		case "edge":
			 driver = new EdgeDriver();
			 break;

		default:
			throw new IllegalArgumentException("Unsupported browser" + browser);
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
	}


	public void takeScreenShot(String name) {
		String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		String path = System.getProperty("user.dir") + ConfigLoader.getProperty("screenshotDir") + name + "_" + dateTime + ".png";

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(path);
		
		try {
			FileUtils.copyFile(srcFile, destFile);
			setScreenshotPath(path);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getScreenShotPath() {
		return screenshotPath;
	}

	public void setScreenshotPath(String path) {
		screenshotPath = path;
	}
}
