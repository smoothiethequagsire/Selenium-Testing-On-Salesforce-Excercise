package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

	WebDriver driver;

	public ContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@title='Contactos']")
	WebElement contactosTab;
	public WebElement ContactTab() {
		return contactosTab;
	}
	
	@FindBy(xpath = "//a[@title='Nuevo']")
	WebElement bttnNewContact;
	public WebElement ContactNewBttn() {
		return bttnNewContact;
	}
	
	@FindBy(xpath = "//button[@name='SaveEdit']")
	WebElement bttnSaveContact;
	public WebElement ContactSaveBttn() {
		return bttnSaveContact;
	}
	
	@FindBy(xpath = "//input[@placeholder='Apellidos']")
	WebElement inputName;
	public WebElement InputName() {
		return inputName;
	}
	
	@FindBy(xpath = "//label[text()='Teléfono']")
	WebElement telNumber;
	public WebElement TelNumber() {
		return telNumber;
	}
	
}
