package vtiger.GenericUtility;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
/**
 * This class contains all the generic methods related to web driver actions
 * @author Deepa
 *
 */
public class WebDriverUtility {
	
	/**
	 * This method is used to maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method will wait for 20 seconds for the entire DOM structure to load
	 * @param driver
	 */
	public void waitForElementsToLoadInDOM(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	/**
	 * This method will wait for particular element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementToLoad(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will wait for particular element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will wait for the particular element to perform click operation
	 * if the element is not interactive it will wait for 1 second 
	 * @param element
	 * throws InterruptedException
	 */
	public void customWaitAndClickOnElement(WebElement element) throws InterruptedException
	{
		int count = 0;
		while(count<10)
		{
			try
			{
				element.click();
				break;
			}
			catch(Exception e)
			{
				Thread.sleep(1000);
				count++;
			}
		}
	}
	
	/**
	 * This method will handle drop down by select class using index
	 * @param element
	 * @param index
	 */
	public void handleDropDown(WebElement element, int index)
	{
		Select s= new Select(element);
		s.selectByIndex(index);
	}
	
	/**
	 * This method will handle the drop down by select class using visible text
	 * @param element
	 * @param visibleText
	 */
	public void handleDropDown(WebElement element, String visibleText)
	{
		Select s= new Select(element);
		s.selectByVisibleText(visibleText);
	}
	
	/**
	 * This method will handle the drop down by select class using value
	 * @param value
	 * @param element
	 */
	public void handleDropDown(String value, WebElement element)
	{
		Select s= new Select(element);
		s.selectByValue(value);
	}
	
	/**
	 * This method will perform double click over a page
	 * @param driver
	 */
	public void doubleClickOn(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.doubleClick().perform();
	}
	
	/**
	 * This method will perform double click over a particular element
	 * @param driver
	 * @param element
	 */
	public void doubleClickOn(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	/**
	 * This method will perform mouse hover action on a particular element
	 * @param driver
	 * @param element
	 */
	public void mouseHoverOn(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	/**
	 * This method will perform mouse hover action over the offset
	 * @param driver
	 * @param x
	 * @param y
	 */
	public void mouseHoverOn(WebDriver driver, int x, int y)
	{
		Actions act = new Actions(driver);
		act.moveByOffset(x,y).perform();
	}
	
	/**
	 * This method will perform right click on the page
	 * @param driver
	 */
	public void rightClickOn(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.contextClick().perform();
	}
	
	/**
	 * This method will perform drag and drop action from src element to target element
	 * @param driver
	 * @param srcElement
	 * @param targetElement
	 */
	public void dragAndDropOn(WebDriver driver, WebElement srcElement, WebElement targetElement)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(srcElement, targetElement).perform();
	}
	
	/**
	 * switch to frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method will take screen shot and return the destination path
	 * @param driver
	 * @param screenshotName
	 * @return
	 * @throws IOException
	 */
	public String takeScreenshot(WebDriver driver, String screenshotName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = ".\\Screenshots\\"+screenshotName+".png";
		File dst = new File(path);
		Files.copy(src, dst);
		
		return dst.getAbsolutePath(); //used for reporting in listeners
	}
}
