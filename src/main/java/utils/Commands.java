package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.apache.commons.io.FileUtils;

@Listeners(utils.Listeners.class)
public class Commands {

	private static String  screenshotPath;

	
	public static void waitForVisibilty(WebElement ele, int duration) {
		WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public static void waitForInvisibilty(WebElement ele, int duration) {
		WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}


	public void takeScreenShot(String name) {
		String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		String path = System.getProperty("user.dir") + ConfigLoader.getProperty("screenshotDir") + name + "_" + dateTime + ".png";

		File srcFile = ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
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
