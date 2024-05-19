package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import utils.WebDriverFactory;
import org.testng.annotations.Listeners;
import org.apache.commons.io.FileUtils;

@Listeners(utils.Listeners.class)
public class BaseClass {

	private final String CONFIG_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties";
	private static String  screenshotPath;

	// public WebDriver driver = getDriver();
	public synchronized static WebDriver getDriver() {
		return WebDriverFactory.getDriver();
	}

	public String getProperty(String key) {

		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(CONFIG_PATH));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop.getProperty(key);

	}

	public void takeScreenShot(String name) {
		String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		String path = System.getProperty("user.dir") + getProperty("screenshotDir") + name + "_" + dateTime + ".png";

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
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
