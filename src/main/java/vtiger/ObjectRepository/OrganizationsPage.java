package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtility.WebDriverUtility;

public class OrganizationsPage extends WebDriverUtility{

	//declaration
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createNewOrgLookUpImg;
	
	//Initialization
	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public WebElement getCreateOrgLookUpImg() {
		return createNewOrgLookUpImg;
	}
	
	//Business Library
	/**
	 * This method will click on Create New Organization Look Up Image
	 */
	public void clickOnCreateNewOrg()
	{
		createNewOrgLookUpImg.click();
	}
	
	
}
