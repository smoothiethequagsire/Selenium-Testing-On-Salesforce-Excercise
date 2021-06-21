package test.Excercisev1PageFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.AccountPage;
import PageObjects.ContactPage;
import resources.Base;

public class CreateContactTest extends Base {

	WebDriver driver;
	AccountPage ap;
	ContactPage cp;

	@BeforeTest
	public void BuildDriver() throws IOException {
		driver = InitializeDriver();
		ap = new AccountPage(driver);
		cp= new ContactPage(driver);
		w = new WebDriverWait(driver, 20);
		driver.get(getPersonalIdForUrl() + ap.URL());
		Login(driver);
	}

	@Test
	public void CreateContactRec() throws InterruptedException {

		w.until(ExpectedConditions.urlContains("Recent"));
		String accountName = ap.AccountName().getText();

		String keysChord = Keys.chord(Keys.CONTROL, Keys.RETURN);
		cp.ContactTab().sendKeys(keysChord);

		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		
		w.until(ExpectedConditions.visibilityOf(cp.ContactNewBttn()));
		cp.ContactNewBttn().click();
		w.until(ExpectedConditions.visibilityOf(cp.ContactSaveBttn()));
		cp.InputName().sendKeys(accountName);
		cp.TelNumber().click();
		cp.ContactSaveBttn().click();
		
		w.until(ExpectedConditions.urlContains("view"));
		driver.close();
		driver.switchTo().window(parentId);
	}
	
	@AfterTest
	public void Terminate() {
		driver.close();
	}

}
