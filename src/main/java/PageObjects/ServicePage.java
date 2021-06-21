package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ServicePage {

	WebDriver driver;

	public ServicePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	String url= "-dev-ed.lightning.force.com/lightning/page/home";

	public String URL() {
		return url;
	}

	@FindBy(xpath = "//div[@role='list']/one-app-nav-bar-item-root/a")
	List<WebElement> tabs;
	public List<WebElement> Tabs(){
		return tabs;
	}
	
	@FindBy(xpath = "//a[@title='Nuevo']")
	WebElement newBttnCuentasContactos;
	public WebElement BttnNewCC(){
		return newBttnCuentasContactos;
	}
	
	@FindBy(xpath = "//button[text()='Cancelar']")
	WebElement cancelBttnCuentasContactosInf;
	public WebElement BttnCancelCC(){
		return cancelBttnCuentasContactosInf;
	}
	
	
	@FindBy(xpath = "//button[@title='Cancelar']")
	WebElement cancelBttnCasos;
	public WebElement BttnCancelCasos(){
		return cancelBttnCasos;
	}
	
	@FindBy(xpath = "//a[@title='Nuevo informe']")
	WebElement bttnNewInfo;
	public WebElement BttnNewInfo(){
		return bttnNewInfo;
	}
	
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']/iframe")
	WebElement infoFrame;
	public WebElement InfoFrame(){
		return infoFrame;
	}
	
	@FindBy(xpath = "//a[@title='Nuevo panel']")
	WebElement bttnNewPanel;
	public WebElement BttnNewPanel(){
		return bttnNewPanel;
	}
	
	@FindBy(xpath = "//div[@class='dashboardContainer']/iframe")
	WebElement panelFrame;
	public WebElement PanelFrame(){
		return panelFrame;
	}
}

