package stepsDefinition;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.AccountPage;
import PageObjects.ContactPage;
import PageObjects.HomePage;
import PageObjects.ServicePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import resources.Base;


public class ServicesStepsDefinition extends Base {
	
	@Before
	public void initialize() throws IOException {
		driver= InitializeDriver();
		w = new WebDriverWait(driver, 20);
		ap= new AccountPage(driver);
		cp= new ContactPage(driver);
		hp= new HomePage(driver);
		sp= new ServicePage(driver);
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}

	
	@Given("^user is on Salesforce login page$")
	public void user_is_on_salesforce_login_page() throws IOException {
		driver.get(hp.URL());
	}
	
	@And("^user send login data and click login button$")
	public void user_send_login_data_and_click_login_button() throws IOException {
		Login(driver);
	}
	
	@And("^user is redirected to home page$")
	public void user_is_redirected_to_home_page() throws IOException {
		w.until(ExpectedConditions.urlContains("home"));
		getEnvironmentUrl();
        Assert.assertTrue(driver.getCurrentUrl().contains("/home"));
	}
	
	@When("^user click on the AppLauncher icon$")
    public void user_click_on_the_applauncher_icon() throws IOException {
		hp.AppLauncher().click();
    }
	
	 @And("^click on the Service option$")
	    public void click_on_the_service_option() {
		 w.until(ExpectedConditions.visibilityOf(hp.ServiceOption()));
			clickElement(driver, hp.ServiceOption());
	    }
	 
	 @Then("^user is redirected to service page$")
	    public void user_is_redirected_to_service_page() {
		 w.until(ExpectedConditions.visibilityOf(hp.ServiceTitle()));
		 Assert.assertTrue("User was not correctly redirected to Service Page.", driver.getCurrentUrl().contains(""));
	    }
	 
	 @And("^navigate tabs clicking new and cancel when possible$")
	 	public void navigate_tabs_clicking_new_and_cancel_when_possible() {
		 NavigateTabs(driver, w);
		 driver.close();
	 }
}