package synchronism;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dsl.DSL;
import page_objects_pattern.TrainingCampPage;

public class SynchronismTest {
	
	private WebDriver driver;
	
	private DSL dsl;

	@Before
	public void inicializa(){
		System.setProperty("webdriver.chrome.driver", "src/main/resources/webdrivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 768));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza(){
		driver.quit();
	}
	
	// Espera fixa. Onde o teste é executado somente após o tempo definido.
	@Test
	public void fixedWaitTest() throws InterruptedException {
		dsl.clickButton("buttonDelay");
		Thread.sleep(5000);
		dsl.writeOnTextField("novoCampo", "Deu certo?");
	}
	
	@Test
	public void implicitWaitTest() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		dsl.clickButton("buttonDelay");
		dsl.writeOnTextField("novoCampo", "Deu certo?");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	@Test
	public void explicitWaitTest() {
		dsl.clickButton("buttonDelay");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.writeOnTextField("novoCampo", "Deu certo?");
	}

}
