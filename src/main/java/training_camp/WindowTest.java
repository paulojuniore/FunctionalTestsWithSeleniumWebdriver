package training_camp;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 
 * @author paulojunior
 * 
 * Test class that interact with external windows.
 *
 */
public class WindowTest {
	
	private WebDriver driver;
	
	@Before
	public void setUpWebdriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/webdrivers/chromedriver");
		driver = new ChromeDriver();
		
		driver.manage().window().setSize(new Dimension(1200, 768));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@Test
	public void windowTest() {
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo!");
		driver.close();
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
		
		Assert.assertEquals("E agora?", driver.findElement(By.tagName("textarea")).getAttribute("value"));
	}

}
