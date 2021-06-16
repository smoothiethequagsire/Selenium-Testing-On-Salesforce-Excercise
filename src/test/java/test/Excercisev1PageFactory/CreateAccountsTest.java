package test.Excercisev1PageFactory;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.AccountPage;
import resources.Base;

public class CreateAccountsTest extends Base {

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

	@Test(dataProvider = "GetData")
	public void CreateRecords(String name, String valoration, String types, String property, String sector,
			String cPriority, String sla, String uOpportunity, String active, String month, String day)
			throws InterruptedException {
		
		w.until(ExpectedConditions.urlContains("Recent"));
		ap.BttnNewCC().click();
		w.until(ExpectedConditions.visibilityOf(ap.BttnCancelCC()));

		ap.InputName().sendKeys(name);

		ap.RatingDropDw().click();
		List<WebElement> Els = ap.DropDwOptions();
		for (WebElement el : Els) {
			if (el.getText().equalsIgnoreCase(valoration)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
			}
		}
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ap.TypeDropDw());
		Els = driver.findElements(By.xpath("//span[@class='slds-media__body']"));
		for (WebElement el : Els) {
			if (el.getText().equalsIgnoreCase(types)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
			}
		}

		ap.PropertyDropDw().click();
		Els = driver.findElements(By.xpath("//span[@class='slds-media__body']"));
		for (WebElement el : Els) {
			if (el.getText().equalsIgnoreCase(property)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
			}
		}

		ap.SectorDropDw().click();
		Els = driver.findElements(By.xpath("//span[@class='slds-media__body']"));
		for (WebElement el : Els) {
			if (el.getText().equalsIgnoreCase(sector)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
			}
		}

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ap.CustPriorityDropDw());
		Els = driver.findElements(By.xpath("//span[@class='slds-media__body']"));
		for (WebElement el : Els) {
			if (el.getText().equalsIgnoreCase(cPriority)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
			}
		}

		ap.SLADropDw().click();
		Els = driver.findElements(By.xpath("//span[@class='slds-media__body']"));
		for (WebElement el : Els) {
			if (el.getText().equalsIgnoreCase(sla)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
			}
		}

		ap.UpsOppDropDw().click();
		Els = driver.findElements(By.xpath("//span[@class='slds-media__body']"));
		for (WebElement el : Els) {
			if (el.getText().equalsIgnoreCase(uOpportunity)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
			}
		}

		ap.ActiveDropDw().click();
		Els = driver.findElements(By.xpath("//span[@class='slds-media__body']"));
		for (WebElement el : Els) {
			if (el.getText().equalsIgnoreCase(active)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
			}
		}

		ap.SLAExpDate().click();
		w.until(ExpectedConditions.visibilityOf(ap.NextMonthBttn()));
		String actualMonth = ap.ActualMonth().getText();

		while (!(actualMonth.equalsIgnoreCase(month))) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", ap.NextMonthBttn());
			actualMonth = driver.findElement(By.xpath("//slot/div/div/h2")).getText();
		}

		for (WebElement d : ap.Days()) {
			if (d.getText().equalsIgnoreCase(day)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", d);
				break;
			}
		}

		ap.SaveBttn().click();
		w.until(ExpectedConditions.urlContains("view"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ap.AccountTab());
	}

	@DataProvider
	public Object[][] GetData() throws IOException {
		Object[][] data = new Object[2][11];

		data[0][0] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 0, 0);
		data[0][1] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 0, 1);
		data[0][2] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 0, 2);
		data[0][3] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 0, 3);
		data[0][4] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 0, 4);
		data[0][5] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 0, 5);
		data[0][6] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 0, 6);
		data[0][7] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 0, 7);
		data[0][8] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 0, 8);
		data[0][9] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 0, 9);
		data[0][10] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 0, 10);

		data[1][0] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 1, 0);
		data[1][1] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 1, 1);
		data[1][2] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 1, 2);
		data[1][3] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 1, 3);
		data[1][4] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 1, 4);
		data[1][5] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 1, 5);
		data[1][6] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 1, 6);
		data[1][7] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 1, 7);
		data[1][8] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 1, 8);
		data[1][9] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 1, 9);
		data[1][10] = GetCellData(
				"C:\\Users\\Leandro\\eclipse-workspace\\Excercisev1PageFactory\\src\\main\\java\\resources\\CreateAccountData.xlsx",
				0, 1, 10);

		return data;
	}
	
	@Test(dependsOnMethods= {"CreateRecords"})
	public void CreateNullAccount() {
		
		w.until(ExpectedConditions.urlContains("Recent"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ap.BttnNewCC());
		ap.SaveBttn().click();
		Assert.assertTrue(ap.ErrorMsg().isDisplayed(), "Error message is not displayed on creating null account.");
		ap.BttnCancelCC().click();	
		w.until(ExpectedConditions.urlContains("Recent"));
	}
	
	@AfterTest
	public void Terminate() {
		driver.close();
	}
	
}
