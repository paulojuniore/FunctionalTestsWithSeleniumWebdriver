package training_camp;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import dsl.DSL;

/**
 * 
 * @author paulojunior
 * 
 * Test class that interact with frame elements.
 *
 */
public class FrameTest {
	
	private WebDriver driver;
	
	@Before
	public void setUpWebdriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/webdrivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 768));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@Test
	public void frameGetTextTest() {
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String response = alert.getText();
		
		Assert.assertEquals("Frame OK!", response);
		alert.accept();
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(response);
	}

}
