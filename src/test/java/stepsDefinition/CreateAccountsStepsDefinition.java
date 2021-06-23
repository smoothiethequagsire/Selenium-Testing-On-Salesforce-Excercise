package stepsDefinition;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import resources.Base;

public class CreateAccountsStepsDefinition extends Base {

	@Given("^user goes to account page$")
	public void user_goes_to_account_page() throws IOException {
		driver.get(environmentUrl+ap.URL());
		Login(driver);
	}

	@And("^user clicks on new button$")
	public void user_clicks_on_new_button() {
		w.until(ExpectedConditions.urlContains("Recent"));
		ap.BttnNewCC().click();
	}

	@When("^user enters data and saves$")
	public void user_enters_data_and_saves() throws IOException {
		CreateAccount(driver, w, 0, 0);
		w.until(ExpectedConditions.urlContains("Recent"));
		clickElement(driver, ap.BttnNewCC());
		w.until(ExpectedConditions.visibilityOf(ap.BttnCancelCC()));
		CreateAccount(driver, w, 0, 1);
	}

	@Then("^account gets created$")
	public void account_gets_created() {
		w.until(ExpectedConditions.urlContains("Recent"));
		Assert.assertTrue("Account did not get created.", ap.CreatedAccount().isDisplayed());
		driver.close();
	}

	// Scenario 2
	@When("^user clicks on save button$")
	public void user_clicks_on_save_button() {
		ap.SaveBttn().click();
	}

	@Then("^error message is displayed$")
	public void error_message_is_displayed() {
		Assert.assertTrue("Error message is not displayed on creating null account.", ap.ErrorMsg().isDisplayed());
		driver.close();
	}
}