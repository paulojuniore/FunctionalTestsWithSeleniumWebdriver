package real_project.core_project;

import static core.DriverFactory.getDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
	
	public void writeOnTextField(String id_element, String text) {
		getDriver().findElement(By.id(id_element)).sendKeys(text);
	}
	
	public String getFieldValue(String id_element) {
		return getDriver().findElement(By.id(id_element)).getAttribute("value");
	}
	
	public void clickRadioButton(String id_radio) {
		getDriver().findElement(By.id(id_radio)).click();
	}
	
	public boolean isCheckedRadioButton(String id_radio_button) {
		return getDriver().findElement(By.id(id_radio_button)).isSelected();
	}
	
	public void selectSpinner(String id_spinner, String value) {
		WebElement element = getDriver().findElement(By.id(id_spinner));
		Select combo = new Select(element);
//		combo.selectByIndex(4);
//		combo.selectByValue("mestrado");
		combo.selectByVisibleText(value);
	}
	
	public String getSpinnerValue(String id_spinner) {
		WebElement element = getDriver().findElement(By.id(id_spinner));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public void clickButton(String id_button) {
		WebElement button = getDriver().findElement(By.id(id_button));
		button.click();
	}
	
	public String getButtonValue(String id_button) {
		WebElement button = getDriver().findElement(By.id(id_button));
		return button.getAttribute("value");
	}
	
	public void clickLink(String link_text) {
		getDriver().findElement(By.linkText(link_text)).click();
	}
	
	public String getLinkText(String id_element) {
		return getDriver().findElement(By.id(id_element)).getText();
	}
	
	public String getTagsText(String tag) {
//		System.out.println(driver.findElement(By.tagName("body")).getText());
		return getDriver().findElement(By.tagName(tag)).getText();
	}
	
	public String getClassnameText(String class_name) {
		return getDriver().findElement(By.className(class_name)).getText();
	}
	
	/********* Alerts ************/
	
	public String getTextAlert(){
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}
	
	public String getTextAndAcceptAlert(){
		Alert alert = getDriver().switchTo().alert();
		String value = alert.getText();
		alert.accept();
		return value;
		
	}
	
	public String getTextAndDismissAlert() {
		Alert alert = getDriver().switchTo().alert();
		String value = alert.getText();
		alert.dismiss();
		return value;
		
	}
	
	public void WriteAlert(String valor) {
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}
	
	/********* Frames e Janelas ************/
	
	public void enterFrame(String id) {
		getDriver().switchTo().frame(id);
	}
	
	public void outFrame(){
		getDriver().switchTo().defaultContent();
	}
	
	public void switchWindow(String id) {
		getDriver().switchTo().window(id);
	}
	
	/********* Interação com javascript *********/
	
	public void executeJS(String cmd, Object... params) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript(cmd, params);
	}

}
