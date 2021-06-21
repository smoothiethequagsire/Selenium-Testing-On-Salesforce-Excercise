package stepsDefinition;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import PageObjects.AccountPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import resources.Base;

public class ModifyAccountStepsDefinition extends Base {

	WebDriver driver;
	WebDriverWait w;
	AccountPage ap;
	String rating = "Hot";
	String upOpp = "Yes";
	String type = "Technology Partner";
	String invalidEmployee = "1431655766";
	String invalidMessage = "Empleados: valor fuera del rango válido en campo numérico: 1431655766";
	
	@Given("^driver is initialized3$")
	public void driver_is_initialized3() throws IOException {
		driver= InitializeDriver();
		w = new WebDriverWait(driver, 20);
		ap= new AccountPage(driver);
	}
	
	@Given("^user fell to account page$")
	public void user_fell_to_account_page() throws IOException {
		ap= new AccountPage(driver);
		driver.get(getPersonalIdForUrl() + ap.URL());
		Login(driver);
	}

	@And("^user clicks on modify account$")
	public void user_clicks_on_modify_account() {
		w.until(ExpectedConditions.visibilityOf(ap.AccountName()));
		clickElement(driver, ap.AccountName());
		w.until(ExpectedConditions.visibilityOf(ap.contactOpInAcc()));
		clickElement(driver, ap.OptionsDrDw());

		for (WebElement el : ap.Options()) {
			w.until(ExpectedConditions.visibilityOf(el));
			if (el.getText().equalsIgnoreCase("modificar")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
				break;
			}
		}
	}

	@When("^user enters new data and saves$")
	public void user_enters_new_data_and_saves() {
		ModifyAccount(driver, w, rating, upOpp, type);
	}

	@Then("^account gets correctly modified$")
	public void account_gets_correctly_modified() {
		clickElement(driver, ap.DetailsTab());

		Assert.assertEquals(ap.ActualRating().getText(), rating);
		Assert.assertEquals(ap.ActualType().getText(), type);
		Assert.assertEquals(ap.ActualUpSellOpp().getText(), upOpp);
		driver.close();
	}

	// Scenario2
	@When("^user enters new invalid employee number$")
	public void user_enters_new_invalid_employee_number() {
		InvalidAccountModify(driver, w, invalidEmployee);
	}

	@Then("^invalid employee message is displayed$")
	public void invalid_employee_message_is_displayed() {
		Assert.assertEquals(invalidMessage, ap.InvalidEmpNumMsg().getText());
		driver.close();
	}


}
