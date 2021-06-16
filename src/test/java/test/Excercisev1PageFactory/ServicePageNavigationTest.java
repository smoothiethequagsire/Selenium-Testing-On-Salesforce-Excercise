package test.Excercisev1PageFactory;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.ServicePage;
import resources.Base;

public class ServicePageNavigationTest extends Base {

	WebDriver driver;
	ServicePage sp;

	@BeforeTest
	public void BuildDriver() throws IOException {
		driver = InitializeDriver();
		sp = new ServicePage(driver);
		w = new WebDriverWait(driver, 20);
		driver.get(sp.URL());
		Login(driver);
	}
	
	@Test
	public void NavigateTabs() throws InterruptedException {

		List<WebElement> tabs = sp.Tabs();
		
		for (int i = 0; i < tabs.size(); i++) {
			
			WebElement element = tabs.get(i);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			String tabName = element.getText();
			
			if (tabName.equalsIgnoreCase("cuentas") || tabName.equalsIgnoreCase("contactos")) {
				w.until(ExpectedConditions.urlContains("Recent"));
				w.until(ExpectedConditions.visibilityOf(sp.BttnNewCC()));
				sp.BttnNewCC().click();
				sp.BttnCancelCC().click();
				w.until(ExpectedConditions.urlContains("Recent"));
			}

			if (tabName.equalsIgnoreCase("casos")) {
				w.until(ExpectedConditions.urlContains("Recent"));
				w.until(ExpectedConditions.visibilityOf(sp.BttnNewCC()));
				sp.BttnNewCC().click();
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", sp.BttnCancelCasos());
				w.until(ExpectedConditions.urlContains("Recent"));
			}

			if (tabName.equalsIgnoreCase("informes")) {
				w.until(ExpectedConditions.urlContains("mru"));
				w.until(ExpectedConditions.visibilityOf(sp.BttnNewInfo()));
				sp.BttnNewInfo().click();
				driver.switchTo().frame(sp.InfoFrame());
				sp.BttnCancelCC().click();
				w.until(ExpectedConditions.urlContains("mru"));
				driver.switchTo().defaultContent();
			}

			if (tabName.equalsIgnoreCase("paneles")) {
				w.until(ExpectedConditions.urlContains("mru"));
				w.until(ExpectedConditions.visibilityOf(sp.BttnNewPanel()));
				sp.BttnNewPanel().click();
				driver.switchTo().frame(sp.PanelFrame());
				sp.BttnCancelCC().click();
				w.until(ExpectedConditions.urlContains("mru"));
				driver.switchTo().defaultContent();
			}
		}
	}
	
	@AfterTest
	public void Terminate() {
		driver.close();
	}
	
}
