package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	
WebDriver driver;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	String url = "-dev-ed.lightning.force.com/lightning/o/Account/list?filterName=Recent";
	public String URL() {
		return url;
	}
	
	@FindBy(xpath= "//a[@title='Cuentas']")
	WebElement cuentasTab;
	public WebElement AccountTab(){
		return cuentasTab;
	}

	@FindBy(xpath = "//a[@title='Nuevo']")
	WebElement newBttnCuentas;
	public WebElement BttnNewCC(){
		return newBttnCuentas;
	}
	
	@FindBy(xpath = "//button[text()='Cancelar']")
	WebElement cancelBttnCuentas;
	public WebElement BttnCancelCC(){
		return cancelBttnCuentas;
	}
	
	@FindBy(xpath = "//input[@name='Name']")
	WebElement inputName;
	public WebElement InputName(){
		return inputName;
	}
	
	@FindBy(xpath = "//label[text()='Valoración']")
	WebElement rating;
	public WebElement RatingDropDw(){
		return rating;
	}
	
	@FindBy(xpath = "//label[text()='Tipo']")
	WebElement type;
	public WebElement TypeDropDw(){
		return type;
	}
	
	@FindBy(xpath = "//label[text()='Propiedad']")
	WebElement property;
	public WebElement PropertyDropDw(){
		return property;
	}
	
	@FindBy(xpath = "//label[text()='Sector']")
	WebElement sector;
	public WebElement SectorDropDw(){
		return sector;
	}
	
	@FindBy(xpath = "//label[text()='Customer Priority']")
	WebElement custPriority;
	public WebElement CustPriorityDropDw(){
		return custPriority;
	}
	
	@FindBy(xpath = "//label[text()='SLA']")
	WebElement sla;
	public WebElement SLADropDw(){
		return sla;
	}
	
	@FindBy(xpath = "//label[text()='Upsell Opportunity']")
	WebElement upsOp;
	public WebElement UpsOppDropDw(){
		return upsOp;
	}
	
	@FindBy(xpath = "//label[text()='Active']")
	WebElement active;
	public WebElement ActiveDropDw(){
		return active;
	}
	
	@FindBy (xpath="//span[@class='slds-media__body']")
	List<WebElement> dropDwOptions;
	public List<WebElement> DropDwOptions(){
		return dropDwOptions;
	}
	
	@FindBy(xpath = "//label[text()='SLA Expiration Date']")
	WebElement slaExpDate;
	public WebElement SLAExpDate(){
		return slaExpDate;
	}
	
	@FindBy(xpath = "//button[@title='Mes siguiente']")
	WebElement nextMonthBttn;
	public WebElement NextMonthBttn(){
		return nextMonthBttn;
	}
	
	@FindBy(xpath = "//slot/div/div/h2")
	WebElement actualMonth;
	public WebElement ActualMonth(){
		return actualMonth;
	}
	
	@FindBy (xpath="//span[@class='slds-day']")
	List<WebElement> days;
	public List<WebElement> Days(){
		return days;
	}
	
	@FindBy(xpath = "//button[@name='SaveEdit']")
	WebElement saveBttn;
	public WebElement SaveBttn(){
		return saveBttn;
	}
	
	@FindBy(xpath = "//header/div/div/h2")
	WebElement errorMsg;
	public WebElement ErrorMsg(){
		return errorMsg;
	}
	
	@FindBy(xpath = "//table/tbody/tr[1]/th/span/a")
	WebElement accountName;
	public WebElement AccountName(){
		return accountName;
	}
	
	@FindBy(xpath= "//button[@class='slds-button slds-button_icon-border-filled']/span")
	WebElement optionsDropDw;
	public WebElement OptionsDrDw() {
		return optionsDropDw;
	}
	
	@FindBy (xpath= "//runtime_platform_actions-ribbon-menu-item/a/span[@class='slds-truncate']")
	List<WebElement> options;
	public List<WebElement> Options(){
		return options;
	}
	
	@FindBy (xpath= "//input[@name='NumberOfEmployees']")
	WebElement inputNOfEmployees;
	public WebElement InputEmployeesN(){
		return inputNOfEmployees;
	}
		
	@FindBy (xpath= "//a[text()='Detalles']")
	WebElement detailTab;
	public WebElement DetailsTab(){
		return detailTab;
	}
	
	@FindBy (xpath= "//span[text()='Valoración']/parent::div/following-sibling::div/span/slot/slot")
	WebElement actualRating;
	public WebElement ActualRating(){
		return actualRating;
	}
	
	@FindBy (xpath= "//span[text()='Tipo']/parent::div/following-sibling::div/span/slot/slot")
	WebElement actualType;
	public WebElement ActualType(){
		return actualType;
	}
	
	@FindBy (xpath= "//span[text()='Upsell Opportunity']/parent::div/following-sibling::div/span/slot/slot")
	WebElement actualUpSellOpp;
	public WebElement ActualUpSellOpp(){
		return actualUpSellOpp;
	}
	
	@FindBy (xpath= "//span[@title='Contactos']")
	WebElement contactOptionsInAccount;
	public WebElement contactOpInAcc(){
		return contactOptionsInAccount;
	}
	

	@FindBy (xpath="//lightning-input/div[@role='alert']")
	WebElement invalidEmployeeNumber;
	public WebElement InvalidEmpNumMsg(){
		return invalidEmployeeNumber;
	}
	
	@FindBy (xpath="//a[text()='Testing account #2']")
	WebElement testingAcc2;
	public WebElement CreatedAccount(){
		return testingAcc2;
	}
	
}
