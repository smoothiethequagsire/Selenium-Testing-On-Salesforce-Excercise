package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.HomePage;

public class Base {

	WebDriver driver;
	public Properties prop;
	public WebDriverWait w;
	public ChromeOptions opt;

	public WebDriver InitializeDriver() throws IOException {

		opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");

		Properties prop = new Properties();
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
	
}
