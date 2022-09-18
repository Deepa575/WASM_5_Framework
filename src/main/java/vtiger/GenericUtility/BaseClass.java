package vtiger.GenericUtility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;

/**
 * This class consists of all basic configuration annotations of testNG
 * to perform common functionalities.
 * @author Deepa
 *
 */
public class BaseClass {
	
	//Create object of all the Utilities
	public DatabaseUtility dbUtil = new DatabaseUtility();
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public PropertyFileUtility pUtil = new PropertyFileUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	public JavaUtility jUtil = new JavaUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
	
	@BeforeSuite(groups = {"SmokeSuite","RegressionSuite"})
	public void bsConfig() throws SQLException
	{
		//dbUtil.connectToDB();
		Reporter.log("--- Database connection successfull ---",true);
	}
	
	//@Parameters("BROWSER")//it will read value from suite xml file use when u want to do Compatibility testing only
	//@BeforeTest //use when u want to do parallel execution only
	@BeforeClass(groups = {"SmokeSuite","RegressionSuite"})
	public void bcConfig(/*String BROWSER*/) throws Throwable
	{
		//read all the data required for the test script
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		
		if (BROWSER.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			Reporter.log(BROWSER+" --- browser launched---", true);
		}
		else if(BROWSER.equalsIgnoreCase("FIREFOX"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			Reporter.log(BROWSER+" --- browser launched---", true);
		}
		else
		{
			System.out.println("invalid browser name");
		}
		
		sdriver=driver;
		wUtil.maximizeWindow(driver);
		wUtil.waitForElementsToLoadInDOM(driver);
		driver.get(URL);
		
	}
	
	
	@BeforeMethod(groups = {"SmokeSuite","RegressionSuite"})
	public void bmConfig() throws IOException
	{
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		Reporter.log("--- login successfull ---",true);
	}
	
	@AfterMethod(groups = {"SmokeSuite","RegressionSuite"})
	public void amConfig()
	{
		HomePage hp = new HomePage(driver);
		hp.signOutOfApp(driver);
		Reporter.log("--- logout successfull ---", true);
	}
	
	@AfterTest
	//@AfterClass(groups = {"SmokeSuite","RegressionSuite"})
	public void acConfig()
	{
		driver.close();
		Reporter.log("--- browser closed ---", true);
	}
	
	@AfterSuite(groups = {"SmokeSuite","RegressionSuite"})
	public void asConfig() throws SQLException
	{
		//dbUtil.closeDB();
		Reporter.log("--- db closed ---", true);
	}
	
}
