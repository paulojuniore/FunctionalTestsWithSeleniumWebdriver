package core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	private static WebDriver driver;
	
	private DriverFactory() { }
	
	public static WebDriver getDriver() {
		if (driver == null) {
			switch (Properties.browser) {
				case FIREFOX: 
					driver = new FirefoxDriver(); 
					break;
				case CHROME: 
					System.setProperty("webdriver.chrome.driver", "src/main/resources/webdrivers/chromedriver");
					driver = new ChromeDriver(); 
					break;
				default: break;
			}
			driver.manage().window().setSize(new Dimension(1200, 768));
		}
		return driver;
	}
	
	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}
