package training_camp;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import dsl.DSL;

/**
 * 
 * @author paulojunior
 * 
 * Test class that interact with basic element of a web formulary.
 *
 */
public class TrainingCampTest {
	
	private DSL dsl;
	
	private WebDriver driver;
	
	@Before
	public void setUpWebdriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/webdrivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 768));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finalize() {
		driver.quit();
	}
	
	@Test
	public void textFieldTest() {
		dsl.writeOnTextField("elementosForm:nome", "Writing test");
		Assert.assertEquals("Writing test", dsl.getFieldValue("elementosForm:nome"));
	}
	
	@Test
	public void textAreaTest() {
		dsl.writeOnTextField("elementosForm:sugestoes", "This is a test for a textArea field.");
		Assert.assertEquals("This is a test for a textArea field.", dsl.getFieldValue("elementosForm:sugestoes"));
	}
	
	@Test
	public void radioButtonTest() {
		dsl.clickRadioButton("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isCheckedRadioButton("elementosForm:sexo:0"));
	}
	
	@Test
	public void checkBoxTest() {
		dsl.clickRadioButton("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isCheckedRadioButton("elementosForm:comidaFavorita:2"));
	}
	
	@Test
	public void comboTest() {
		dsl.selectSpinner("elementosForm:escolaridade", "Doutorado");
		Assert.assertEquals("Doutorado", dsl.getSpinnerValue("elementosForm:escolaridade"));
	}
	
	@Test
	public void checkValuesOfTheComboTest() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		
		// check amount of options in the combo.
		Assert.assertEquals(8, options.size());
		
		boolean found = false;
		
		for (WebElement option : options) {
			if (option.getText().equals("Especializacao")) {
				found = true;
				break;
			}
		}
		
		// checks if the "Especializacao" option is in combo options.
		Assert.assertTrue(found);
	}
	
	@Test
	public void multipleChoiceComboTest() {
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		
		dsl.selectSpinner("elementosForm:esportes", "Natacao");
		dsl.selectSpinner("elementosForm:esportes", "Corrida");
		dsl.selectSpinner("elementosForm:esportes", "Karate");
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		
		combo.deselectByVisibleText("Natacao");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
	}
	
	@Test
	public void buttonClickTest() {
		dsl.clickButton("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.getButtonValue("buttonSimple"));
	}
	
	@Test
	public void linkTest() {
		dsl.clickLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.getLinkText("resultado"));
	}
	
	@Test
	public void searchForTextTest() {
		String result = dsl.getTagsText("h3");
		Assert.assertEquals("Campo de Treinamento", result);
		result = dsl.getClassnameText("facilAchar");
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", result);
	}
	
}
