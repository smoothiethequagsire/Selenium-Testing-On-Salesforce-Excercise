package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.AccountPage;
import PageObjects.ContactPage;
import PageObjects.HomePage;
import PageObjects.ServicePage;

public class Base {
	
	public ChromeOptions opt;
	public Properties prop;
	public static WebDriverWait w;
	public static WebDriver driver;
	public static HomePage hp;
	public static AccountPage ap;
	public static ServicePage sp;
	public static ContactPage cp;
	public static String environmentUrl;
	
	public WebDriver InitializeDriver() throws IOException {
		
		opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"src/main/java/resources/data.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Leandro\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver(opt);
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		return driver;
	}
	

	public String GetCellData(String doc, int sheetIndx, int rowIndx, int cellIndx) throws IOException {
		FileInputStream file = new FileInputStream(doc);
		@SuppressWarnings("resource")
		XSSFWorkbook wkb = new XSSFWorkbook(file);
		XSSFSheet sheet = wkb.getSheetAt(sheetIndx);

		Iterator<Row> rows = sheet.iterator();
		Row wantedRow = rows.next();
		int i = 0;
		while (i < rowIndx) {
			wantedRow = rows.next();
			i++;
		}

		Iterator<Cell> cells = wantedRow.cellIterator();
		Cell wantedCell = cells.next();
		int j = 0;
		while (j < cellIndx) {
			wantedCell = cells.next();
			j++;
		}

		CellType dataType = wantedCell.getCellType();
		String data = "";
		if (dataType == CellType.STRING) {
			data = wantedCell.getStringCellValue();
		}
		if (dataType == CellType.NUMERIC) {
			data = NumberToTextConverter.toText(wantedCell.getNumericCellValue());
		}
		
		wkb.close();
		return data;
	}

	public void Login(WebDriver driver) throws IOException {
		HomePage hp= new HomePage(driver);
		
		String username= GetCellData(
				"src/main/java/resources/LoginData.xlsx",
				0, 0, 0);
		String password= GetCellData(
				"src/main/java/resources/LoginData.xlsx",
				0, 0, 1);
		
		hp.Username().sendKeys(username);
		hp.Password().sendKeys(password);
		hp.LoginBttn().click();
	}
	
	public void clickElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}
	
	public void CreateAccount(WebDriver driver, WebDriverWait w, int sheetIndex, int sheetRow) throws IOException {
		
		Object[][] data= new Object[1][11];
		
		for (int i=0; i < 11; i++) {
			data[0][i]= GetCellData(
					"src/main/java/resources/CreateAccountData.xlsx",
					sheetIndex, sheetRow, i);
		}
		
		AccountPage ap= new AccountPage(driver);
		
		ap.InputName().sendKeys(data[0][0].toString());
		
		ap.RatingDropDw().click();
		List<WebElement> Els = ap.DropDwOptions();
		for (WebElement el : Els) {
			if (el.getText().equalsIgnoreCase(data[0][1].toString())) {
				clickElement(driver, el);
			}
		}

		clickElement(driver, ap.TypeDropDw());
		Els = driver.findElements(By.xpath("//span[@class='slds-media__body']"));
		for (WebElement el : Els) {
			if (el.getText().equalsIgnoreCase(data[0][2].toString())) {
				clickElement(driver, el);
			}
		}

		ap.PropertyDropDw().click();
		Els = driver.findElements(By.xpath("//span[@class='slds-media__body']"));
		for (WebElement el : Els) {
			if (el.getText().equalsIgnoreCase(data[0][3].toString())) {
				clickElement(driver, el);
			}
		}

		ap.SectorDropDw().click();
		Els = driver.findElements(By.xpath("//span[@class='slds-media__body']"));
		for (WebElement el : Els) {
			if (el.getText().equalsIgnoreCase(data[0][4].toString())) {
				clickElement(driver, el);
			}
		}
		
		clickElement(driver, ap.CustPriorityDropDw());
		Els = driver.findElements(By.xpath("//span[@class='slds-media__body']"));
		for (WebElement el : Els) {
			if (el.getText().equalsIgnoreCase(data[0][5].toString())) {
				clickElement(driver, el);
			}
		}

		ap.SLADropDw().click();
		Els = driver.findElements(By.xpath("//span[@class='slds-media__body']"));
		for (WebElement el : Els) {
			if (el.getText().equalsIgnoreCase(data[0][6].toString())) {
				clickElement(driver, el);
			}
		}

		ap.UpsOppDropDw().click();
		Els = driver.findElements(By.xpath("//span[@class='slds-media__body']"));
		for (WebElement el : Els) {
			if (el.getText().equalsIgnoreCase(data[0][7].toString())) {
				clickElement(driver, el);
			}
		}

		ap.ActiveDropDw().click();
		Els = driver.findElements(By.xpath("//span[@class='slds-media__body']"));
		for (WebElement el : Els) {
			if (el.getText().equalsIgnoreCase(data[0][8].toString())) {
				clickElement(driver, el);
			}
		}

		ap.SLAExpDate().click();
		w.until(ExpectedConditions.visibilityOf(ap.NextMonthBttn()));
		String actualMonth = ap.ActualMonth().getText();

		while (!(actualMonth.equalsIgnoreCase(data[0][9].toString()))) {
			clickElement(driver, ap.NextMonthBttn());
			actualMonth = driver.findElement(By.xpath("//slot/div/div/h2")).getText();
		}
		
		for (WebElement d : ap.Days()) {
			if (d.getText().equalsIgnoreCase(String.valueOf(data[0][10]))) {
				clickElement(driver, d);
				break;
			}
		}
		
		ap.SaveBttn().click();
		w.until(ExpectedConditions.urlContains("view"));
		clickElement(driver, ap.AccountTab());
		} 
	
	public void NavigateTabs(WebDriver driver, WebDriverWait w) {
		
		ServicePage sp= new ServicePage(driver);
		
		List<WebElement> tabs = sp.Tabs();
		
		for (int i = 0; i < tabs.size(); i++) {
			
			WebElement element = tabs.get(i);
			clickElement(driver, element);
			String tabName = element.getText();
			
			
			if (tabName.equalsIgnoreCase("cuentas") || tabName.equalsIgnoreCase("contactos")) {
				w.until(ExpectedConditions.urlContains("Recent"));
				w.until(ExpectedConditions.visibilityOf(sp.BttnNewCC()));
				sp.BttnNewCC().click();
				w.until(ExpectedConditions.visibilityOf(sp.BttnCancelCC()));
				sp.BttnCancelCC().click();
				w.until(ExpectedConditions.urlContains("Recent"));
			}

			if (tabName.equalsIgnoreCase("casos")) {
				w.until(ExpectedConditions.urlContains("Recent"));
				w.until(ExpectedConditions.visibilityOf(sp.BttnNewCC()));
				clickElement(driver, sp.BttnNewCC());
				clickElement(driver, sp.BttnCancelCasos());
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
	
	public void ModifyAccount(WebDriver driver, WebDriverWait w, String rating, String upOpp, String type) {
		
		AccountPage ap= new AccountPage(driver); 
		
		w.until(ExpectedConditions.visibilityOf(ap.RatingDropDw()));
		clickElement(driver, ap.RatingDropDw());
		WebElement element = driver.findElement(By.xpath("//span[text()='" + rating + "']"));
		w.until(ExpectedConditions.visibilityOf(element));
		clickElement(driver, element);

		w.until(ExpectedConditions.visibilityOf(ap.UpsOppDropDw()));
		clickElement(driver, ap.UpsOppDropDw());
		element = driver.findElement(By.xpath("//span[text()='" + upOpp + "']"));
		w.until(ExpectedConditions.visibilityOf(element));
		clickElement(driver, element);

		w.until(ExpectedConditions.visibilityOf(ap.TypeDropDw()));
		clickElement(driver, ap.TypeDropDw());
		element = driver.findElement(By.xpath("//span[text()='" + type + "']"));
		w.until(ExpectedConditions.visibilityOf(element));
		clickElement(driver, element);

		ap.InputEmployeesN().click();
		ap.SaveBttn().click();
	}
	
	public void InvalidAccountModify(WebDriver driver, WebDriverWait w, String invalidEmployee) {
		
		AccountPage ap= new AccountPage(driver);
		
		w.until(ExpectedConditions.visibilityOf(ap.InputEmployeesN()));
		ap.InputEmployeesN().sendKeys(invalidEmployee);
		clickElement(driver, ap.SectorDropDw());
		clickElement(driver, ap.SaveBttn());
	}
	
	public void getEnvironmentUrl() throws IOException {
		String Url= driver.getCurrentUrl();
		String[] UrlParts= Url.split("-", 2);
		environmentUrl= UrlParts[0];
		System.out.println(environmentUrl);
	}
}
