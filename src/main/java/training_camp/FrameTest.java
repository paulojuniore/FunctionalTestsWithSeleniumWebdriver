package training_camp;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	private DSL dsl;
	
	@Before
	public void setUpWebdriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/webdrivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 768));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@Test
	public void frameGetTextTest() {
		dsl.enterFrame("frame1");
		dsl.clickButton("frameButton");
		String response = dsl.getTextAndAcceptAlert();
		
		Assert.assertEquals("Frame OK!", response);
		dsl.outFrame();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(response);
	}
	
	@Test
	public void interactWithFrameFromJavascriptCode() {
		WebElement frame = driver.findElement(By.id("frame2"));
		dsl.executeJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.enterFrame("frame2");
		dsl.clickButton("frameButton");
		String msg = dsl.getTextAndAcceptAlert();
		Assert.assertEquals("Frame OK!", msg);
	}

}
