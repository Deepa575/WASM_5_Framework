package vtiger.GenericUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This class provides implementation for the IRetryAnalyser interface of TestNG
 * @author Deepa
 *
 */
public class RetryAnalyserImplementation implements IRetryAnalyzer{

	int count = 0;
	int retryCount = 3;
	public boolean retry(ITestResult result) {
	
		while(count<retryCount)
		{
			count++;
			return true;
		}
		
		return false;
	}
}
