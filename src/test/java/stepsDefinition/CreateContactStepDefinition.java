package stepsDefinition;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.AccountPage;
import PageObjects.ContactPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import resources.Base;

public class CreateContactStepDefinition extends Base {
	
	WebDriver driver;
	WebDriverWait w;
	AccountPage ap;
	ContactPage cp;
	String accountName;
	String parentId;
	String childId;
	
	@Given("^driver is initialized2$")
	public void driver_is_initialized2() throws IOException {
		driver= InitializeDriver();
		w = new WebDriverWait(driver, 20);
		ap= new AccountPage(driver);
		cp= new ContactPage(driver);
	}

	@Given("^user is on account page")
	public void user_is_on_account_page() throws IOException {
		ap = new AccountPage(driver);
		cp = new ContactPage(driver);
		driver.get(getPersonalIdForUrl() + ap.URL());
		Login(driver);
	}

	@And("^name is extracted from an account$")
	public void name_is_extracted_from_an_account() {
		w.until(ExpectedConditions.urlContains("Recent"));
		accountName = ap.AccountName().getText();
	}

	@And("^user opens Contact tab in new tab$")
	public void user_opens_contact_tab_in_new_tab() {
		String keysChord = Keys.chord(Keys.CONTROL, Keys.RETURN);
		cp.ContactTab().sendKeys(keysChord);
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		parentId = it.next();
		childId = it.next();
		driver.switchTo().window(childId);
	}

	@When("^user enters lastname using previously extracted name$")
	public void user_enters_lastname_using_previously_extracted_name() {
		w.until(ExpectedConditions.visibilityOf(cp.ContactNewBttn()));
		cp.ContactNewBttn().click();
		w.until(ExpectedConditions.visibilityOf(cp.ContactSaveBttn()));
		cp.InputName().sendKeys(accountName);
		cp.TelNumber().click();
		cp.ContactSaveBttn().click();
	}

	@Then("^contact is created$")
	public void contact_is_created() {
		w.until(ExpectedConditions.urlContains("view"));
		Assert.assertTrue("Contact was not created properly.",
				driver.findElement(By.xpath("//span[text()='" + accountName + "']")).isDisplayed());
	}

	@And("^user goes back to first tab$")
	public void user_goes_back_to_first_tab() {
		driver.close();
		driver.switchTo().window(parentId);
		driver.close();
	}
}
