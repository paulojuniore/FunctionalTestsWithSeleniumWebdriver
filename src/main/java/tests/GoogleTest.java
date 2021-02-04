package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class GoogleTest {
	
	@Test
	public void testConnection() {
		/* for chrome driver connection */
		System.setProperty("webdriver.chrome.driver", "src/main/resources/webdrivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		/* for firefox driver connection */
//		System.setProperty("webdriver.gecko.driver", "/home/paulojunior/Documentos/geckodriver");
//		WebDriver driver = new FirefoxDriver();
		
		/* for internet explorer driver connection */
//		WebDriver driver = new InternetExplorerDriver();
		
		/* set the size of browser tab */
		driver.manage().window().setSize(new Dimension(1200, 768));
		
//		driver.manage().window().maximize();
		
		driver.get("http://www.google.com");
		
		Assert.assertEquals("Google", driver.getTitle());
		
		/* close all browser tabs and kill the instance of the driver. */
		driver.quit();
	}

}
