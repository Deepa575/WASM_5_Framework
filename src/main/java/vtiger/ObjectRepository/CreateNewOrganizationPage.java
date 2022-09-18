package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtility.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility{

	//declaration
	@FindBy(name="accountname")
	private WebElement OrgNameEdt;
	
	@FindBy(name = "industry")
	private WebElement industryDropDown;
	
	@FindBy(name = "accounttype")
	private WebElement typeDropDown;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//Initialization
	public CreateNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Business Library
	/**
	 * This method will create organization with Org name
	 * @param OrgName
	 */
	public void createNewOrg(String orgName)
	{
		OrgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	
	/**
	 * This method will create organization with industry drop down
	 * @param OrgName
	 * @param IndustryType
	 */
	public void createNewOrg(String orgName, String industryType)
	{
		OrgNameEdt.sendKeys(orgName);
		handleDropDown(industryType, industryDropDown);
		saveBtn.click();
	}
	
	/**
	 * This method will create organization with type and industry drop down
	 * @param orgname
	 * @param industryType
	 * @param type
	 */
	public void createNewOrg(String orgName, String industryType, String type)
	{
		OrgNameEdt.sendKeys(orgName);
		handleDropDown(industryType, industryDropDown);
		handleDropDown(type, typeDropDown);
	}
}
