package synchronism;

import static core.DriverFactory.getDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import core.DSL;
import core.DriverFactory;

public class AjaxTest {
	
private WebDriver driver;
	
	private DSL dsl;

	@Before
	public void setUp() {
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL();
	}
	
	@After
	public void finalize(){
		DriverFactory.killDriver();
	}
	
	@Test
	public void ajaxTest() {
		dsl.writeOnTextField("j_idt263:name", "Teste");
		dsl.clickButton("j_idt263:j_idt267");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt263:display"), "Test"));
		Assert.assertEquals("Teste", dsl.getLinkText("j_idt263:display"));
	}

}
