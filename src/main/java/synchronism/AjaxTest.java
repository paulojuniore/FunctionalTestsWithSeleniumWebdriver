package synchronism;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import dsl.DSL;

public class AjaxTest {
	
private WebDriver driver;
	
	private DSL dsl;

	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "src/main/resources/webdrivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 768));
		driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL(driver);
	}
	
	@After
	public void finalize(){
		driver.quit();
	}
	
	@Test
	public void AjaxTest() {
		dsl.writeOnTextField("j_idt263:name", "Teste");
		dsl.clickButton("j_idt263:j_idt267");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt263:display"), "Test"));
		Assert.assertEquals("Teste", dsl.getLinkText("j_idt263:display"));
	}

}
