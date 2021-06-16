package test.Excercisev1PageFactory;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import PageObjects.HomePage;
import resources.Base;



public class HomePageTest extends Base{
	
	WebDriver driver;
	HomePage hp;
	
	@BeforeTest
	public void BuildDriver() throws IOException {
		driver = InitializeDriver();
		hp= new HomePage(driver);
		w = new WebDriverWait(driver, 20);
		driver.get(hp.URL());
	}
	
	
	@Test
	public void NavigateToService() throws IOException {

		Login(driver);
		hp.AppLauncher().click();
		w.until(ExpectedConditions.visibilityOf(hp.ServiceOption()));
		clickElement(driver, hp.ServiceOption());
		w.until(ExpectedConditions.visibilityOf(hp.ServiceTitle()));
	}
	
	@AfterTest
	public void Terminate() {
		driver.close();
	}
}
