package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
    private WebDriver driver;

    public WebDriver getDriver() {
    	
        if (driver == null) {
         initializeDriver(ConfigLoader.getProperty("browser")); 
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
        	driver.close();
            driver.quit();
            driver = null;
        }
    }
    
    public WebDriver initializeDriver(String browser) {
    	WebDriver driver;
    	
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
}
