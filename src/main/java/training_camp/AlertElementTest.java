package training_camp;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 
 * @author paulojunior
 * 
 * Test class that interact with alert elements.
 *
 */
public class AlertElementTest {
	
	private WebDriver driver;
	
	@Before
	public void setUpWebdriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/webdrivers/chromedriver");
		driver = new ChromeDriver();
		
		driver.manage().window().setSize(new Dimension(1200, 768));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void finalize() {
		driver.quit();
	}
	
	@Test
	public void alertTest() {
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		
		String response = alert.getText();
		Assert.assertEquals("Alert Simples", response);
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(response);
	}
	
	@Test
	public void confirmAlertTest() {
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alert.getText());
		alert.accept();
		Assert.assertEquals("Confirmado", alert.getText());
		alert.accept();
	}
	
	@Test
	public void cancelAlertTest() {
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alert.getText());
		alert.dismiss();
		Assert.assertEquals("Negado", alert.getText());
		alert.accept();
	}
	
	@Test
	public void promptAlertTest() {
		driver.findElement(By.id("prompt")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alert.getText());
		
		// write value in the alert input
		alert.sendKeys("10");
		// ok
		alert.accept();
		Assert.assertEquals("Era 10?", alert.getText());
		// ok
		alert.accept();
		Assert.assertEquals(":D", alert.getText());
		// ok | close alert
		alert.accept();
	}

}
