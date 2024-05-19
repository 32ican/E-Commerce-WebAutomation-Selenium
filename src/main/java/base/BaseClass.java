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

//	private final String configFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties";
//	private static String  screenshotPath;
	
    private static final String CONFIG_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties";
    private static final ThreadLocal<Properties> properties = ThreadLocal.withInitial(() -> loadProperties(CONFIG_FILE_PATH));
    private static final ThreadLocal<String> SCREENSHOT_PATH = new ThreadLocal<>();

	// public WebDriver driver = getDriver();
	public static WebDriver getDriver() {

		return WebDriverFactory.getDriver();
	}

	public String getProperty(String key) {

//		Properties prop = new Properties();
//		try {
//			prop.load(new FileInputStream(CONFIG_FILE_PATH));
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return properties.get().getProperty(key);

	}
	

    private static Properties loadProperties(String filePath) {
        Properties prop = new Properties();
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
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
		return SCREENSHOT_PATH.get();
	}

	public void setScreenshotPath(String path) {
		SCREENSHOT_PATH.set(path);
	}
}
