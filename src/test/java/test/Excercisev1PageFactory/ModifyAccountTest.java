package test.Excercisev1PageFactory;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.AccountPage;
import resources.Base;

public class ModifyAccountTest extends Base {

	WebDriver driver;
	AccountPage ap;

	@BeforeTest
	public void BuildDriver() throws IOException {
		driver = InitializeDriver();
		ap = new AccountPage(driver);
		w = new WebDriverWait(driver, 20);
		driver.get(ap.URL());
		Login(driver);
	}

	@Test
	public void ModifyAccount() throws InterruptedException {

		String rating = "Hot";
		String upOpp = "Yes";
		String type = "Technology Partner";

		ap.AccountTab();
		w.until(ExpectedConditions.visibilityOf(ap.AccountName()));
		ap.AccountName().click();
		w.until(ExpectedConditions.visibilityOf(ap.contactOpInAcc()));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ap.OptionsDrDw());
	
		for (WebElement el : ap.Options()) {
			w.until(ExpectedConditions.visibilityOf(el));
			if (el.getText().equalsIgnoreCase("modificar")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
				break;
			}
		}

		w.until(ExpectedConditions.visibilityOf(ap.RatingDropDw()));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ap.RatingDropDw());
		WebElement element = driver.findElement(By.xpath("//span[text()='" + rating + "']"));
		w.until(ExpectedConditions.visibilityOf(element));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

		w.until(ExpectedConditions.visibilityOf(ap.UpsOppDropDw()));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ap.UpsOppDropDw());
		element = driver.findElement(By.xpath("//span[text()='" + upOpp + "']"));
		w.until(ExpectedConditions.visibilityOf(element));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

		w.until(ExpectedConditions.visibilityOf(ap.TypeDropDw()));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ap.TypeDropDw());
		element = driver.findElement(By.xpath("//span[text()='" + type + "']"));
		w.until(ExpectedConditions.visibilityOf(element));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

		ap.InputEmployeesN().click();
		ap.SaveBttn().click();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ap.DetailsTab());

		Assert.assertEquals(ap.ActualRating().getText(), rating);
		Assert.assertEquals(ap.ActualType().getText(), type);
		Assert.assertEquals(ap.ActualUpSellOpp().getText(), upOpp);
	}
	
	@Test (dependsOnMethods= {"ModifyAccount"})
	public void InvalidModification() throws InterruptedException {
		
		String invalidEmployee= "1431655766";
		String invalidMessage= "Empleados: valor fuera del rango válido en campo numérico: 1431655766";
		
		w.until(ExpectedConditions.visibilityOf(ap.DetailsTab()));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ap.OptionsDrDw());
		
		for (WebElement el : ap.Options()) {
			if (el.getText().equalsIgnoreCase("modificar")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
				break;
			}
		}
		
		w.until(ExpectedConditions.visibilityOf(ap.InputEmployeesN()));
		ap.InputEmployeesN().sendKeys(invalidEmployee);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				ap.SectorDropDw());
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				ap.SaveBttn());
		Thread.sleep(3000);
		
		Assert.assertEquals(invalidMessage, ap.InvalidEmpNumMsg().getText());
	}
	
	@AfterTest
	public void Terminate() {
		driver.close();
	}

}
