package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GoogleTest {
	
	public static void main(String[] args) {
		//System.setProperty("webdriver.gecko.driver", "/home/paulojunior/Documentos/geckodriver");
		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://www.google.com");
		System.out.println(driver.getTitle());
	}

}
