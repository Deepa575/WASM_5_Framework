package vtiger.ContactsTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtility.BaseClass;
import vtiger.GenericUtility.ExcelFileUtility;
import vtiger.GenericUtility.JavaUtility;
import vtiger.GenericUtility.PropertyFileUtility;
import vtiger.GenericUtility.WebDriverUtility;
import vtiger.ObjectRepository.ContactsInfoPage;
import vtiger.ObjectRepository.ContactsPage;
import vtiger.ObjectRepository.CreateNewContactPage;
import vtiger.ObjectRepository.CreateNewOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationsPage;

/**
 * 
 * @author Deepa
 *
 */
@Listeners(vtiger.GenericUtility.ListenersImplementation.class)
public class CreateContactWithOrgTest extends BaseClass{
		@Test(groups = {"SmokeSuite","RegressionSuite"})
		public void CreateContactWithOrgTest() throws IOException {
		
        WebDriver driver = null;
			
		String ORGNAME = eUtil.readDataFromExcel("Contact", 4, 3)+jUtil.getRandomNumber();
	    String LASTNAME = eUtil.readDataFromExcel("Contact", 4, 2);
	    
	    //Launch browser
	    if(BROWSER.equalsIgnoreCase("CHROME"))
	    {
	    	WebDriverManager.chromedriver().setup();
	    	driver = new ChromeDriver();
	    }
	    else if(BROWSER.equalsIgnoreCase("FIREFOX"))
	    {
	    	WebDriverManager.firefoxdriver().setup();
	    	driver = new FirefoxDriver();
	    }
	    else
	    {
	    	System.out.println("invalid browesr name");
	    }
	    
	    wUtil.maximizeWindow(driver);
	    wUtil.waitForElementsToLoadInDOM(driver);
	    driver.get(URL);
	    
	    //login To APP
	    LoginPage lp = new LoginPage(driver);
	    lp.loginToApp(USERNAME, PASSWORD);
	    
	    //Navigate to Organizations link
	    HomePage hp = new HomePage(driver);
	    hp.clickOnOrgLink();
	    Reporter.log("Click on Organizations Link",true); //low level reporting
	    
	    //click on create organization look up image
	    OrganizationsPage op = new OrganizationsPage(driver);
	    op.clickOnCreateNewOrgImg();
	    Reporter.log("Click on create Organizations look up image",true);
	    
	    // Create Organization with mandatory fields and save
	    CreateNewOrganizationPage cop = new CreateNewOrganizationPage(driver);
	    cop.createNewOrg(ORGNAME);
	    Reporter.log("new organization created",true);
	    
	    //Validate for Organization
	    OrganizationInfoPage oip = new OrganizationInfoPage(driver);
	    String OrgHeader = oip.getOrgHeader();
	    System.out.println(OrgHeader); //true
	    Assert.assertEquals(orgHeader.contains(ORGNAME), true);
	    
	    //Navigate to Contacts link
	    hp.clickOnContactsLnk();
	    Reporter.log("Click on contacts Link",true);
	    
	    //Click on create contact look up image
	    ContactsPage cp = new ContactsPage(driver);
	    cp.clickOnCreateContactImg();
	    Reporter.log("Click on create contacts Lookup image",true);
	    
	    //Create contact with Organization and save
	    CreateNewContactPage cnp = new CreateNewContactPage(driver);
	    cnp.createNewContact(LASTNAME, ORGNAME, driver);
	    Reporter.log("new contact created with organization ",true);
	    
	    //Validate  for contacts
	    ContactsInfoPage cip = new ContactsInfoPage(driver);
	    String contactHeader = cip.getContactHeader();
	    System.out.println(contactHeader);
	    Assert.assertTrue(contactHeader.contains(LASTNAME)); //true
	    
	    //Sign out
	    hp.signOutOfApp(driver);
	    System.out.println("sign out successfull");
	    
	}
}
