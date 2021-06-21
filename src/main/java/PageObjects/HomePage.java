package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.Base;


public class HomePage extends Base {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	String url = "https://login.salesforce.com/";

	public String URL() {
		return url;
	}

	@FindBy(xpath = "//input[@id='username']")
	WebElement username;

	public WebElement Username() {
		return username;
	}

	@FindBy(xpath = "//input[@id='password']")
	WebElement password;

	public WebElement Password() {
		return password;
	}

	@FindBy(xpath = "//input[@id='Login']")
	WebElement login;
	
	public WebElement LoginBttn() {
		return login;
	}
	
	@FindBy (xpath="//div[@class='appLauncher slds-context-bar__icon-action']/button")
	WebElement appLauncher;
	
	public WebElement AppLauncher() {
		return driver.findElement(By.xpath("//div[@class='appLauncher slds-context-bar__icon-action']/button"));
	}
	
	@FindBy (xpath="//span/p[text()='Servicio']")
	WebElement serviceOption;
	
	public WebElement ServiceOption() {
		return serviceOption;
	}
	
	@FindBy (xpath= "//span[@title='Servicio']")
	WebElement serviceTitle;
	
	public WebElement ServiceTitle() {
		return serviceTitle;
	}
}
