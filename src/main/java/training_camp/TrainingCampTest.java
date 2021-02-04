package training_camp;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TrainingCampTest {
	
	private WebDriver driver;
	
	@Before
	public void setUpWebdriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/webdrivers/chromedriver");
		driver = new ChromeDriver();
		
		driver.manage().window().setSize(new Dimension(1200, 768));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@Test
	public void textFieldTest() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Writing test");
		
		Assert.assertEquals("Writing test", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	}
	
	@Test
	public void textAreaTest() {
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("This is a test for a textArea field.");
		
		Assert.assertEquals("This is a test for a textArea field.", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	}
	
	@Test
	public void radioButtonTest() {
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	}
	
	@Test
	public void checkBoxTest() {
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
	}
	
	@Test
	public void comboTest() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
//		combo.selectByIndex(4);
//		combo.selectByValue("mestrado");
		combo.selectByVisibleText("Doutorado");
		
		Assert.assertEquals("Doutorado", combo.getFirstSelectedOption().getText());
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
	
}
