package training_camp;

import static core.DriverFactory.getDriver;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import core.DSL;

/**
 * 
 * @author paulojunior
 * 
 * Test class that interact with frame elements.
 *
 */
public class FrameTest {
	
	private DSL dsl;
	
	@Before
	public void setUpWebdriver() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@Test
	public void frameGetTextTest() {
		dsl.enterFrame("frame1");
		dsl.clickButton("frameButton");
		String response = dsl.getTextAndAcceptAlert();
		
		Assert.assertEquals("Frame OK!", response);
		dsl.outFrame();
		getDriver().findElement(By.id("elementosForm:nome")).sendKeys(response);
	}
	
	@Test
	public void interactWithFrameFromJavascriptCode() {
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executeJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.enterFrame("frame2");
		dsl.clickButton("frameButton");
		String msg = dsl.getTextAndAcceptAlert();
		Assert.assertEquals("Frame OK!", msg);
	}

}
