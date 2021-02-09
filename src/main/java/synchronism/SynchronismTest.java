package synchronism;

import static core.DriverFactory.getDriver;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import core.DSL;
import core.DriverFactory;

public class SynchronismTest {
	
	private DSL dsl;

	@Before
	public void setUp(){
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finalize(){
		DriverFactory.killDriver();
	}
	
	// Espera fixa. Onde o teste é executado somente após o tempo definido.
	@Test
	public void fixedWaitTest() throws InterruptedException {
		dsl.clickButton("buttonDelay");
		Thread.sleep(5000);
		dsl.writeOnTextField("novoCampo", "Deu certo?");
	}
	
	// Espera implícita. O teste é executado assim que a página é completamente carregada. Não sendo obrigatória
	// a espera de todo o tempo de espera especificado.
	@Test
	public void implicitWaitTest() {
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		dsl.clickButton("buttonDelay");
		dsl.writeOnTextField("novoCampo", "Deu certo?");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	// Espera explícita. O teste é executado quando o elemento especificado é total renderizado dentro do intervalo
	// de tempo especificado.
	@Test
	public void explicitWaitTest() {
		dsl.clickButton("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.writeOnTextField("novoCampo", "Deu certo?");
	}

}
