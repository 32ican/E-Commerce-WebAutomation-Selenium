package utils;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import base.BaseClass;

public class WebDriverFactory {

	public static ThreadLocal<WebDriver> driverT = new ThreadLocal<>();

	
	
	public synchronized static WebDriver getDriver() {

		if (driverT.get() == null) {
			driverT.set(createDriver());
		}
		
		return driverT.get();
	}

	public static WebDriver createDriver() {
		WebDriver driver;
		BaseClass base = new BaseClass();
		String browser = base.getProperty("browser");
		
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
		
		return driver;

	}

	
	public synchronized static void cleanupDriver() {
		if (driverT.get() !=null) {
			driverT.get().quit();
			driverT.remove();
		}
		
	}

}
