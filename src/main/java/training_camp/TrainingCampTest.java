package training_camp;

import static core.DriverFactory.getDriver;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import core.DSL;
import core.DriverFactory;

/**
 * 
 * @author paulojunior
 * 
 * Test class that interact with basic element of a web formulary.
 *
 */
public class TrainingCampTest {
	
	private DSL dsl;
	
	@Before
	public void setUpWebdriver() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finalize() {
		DriverFactory.killDriver();
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
		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
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
		WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
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
	
	@Test
	public void usingJavascriptTest() {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("alert('Testando javascript via selenium')");
//		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
//		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");

		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
		dsl.executeJS("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
}
