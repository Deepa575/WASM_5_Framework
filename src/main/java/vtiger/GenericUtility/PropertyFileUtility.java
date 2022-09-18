package vtiger.GenericUtility;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * This class contains generic methods to read data from property file
 * @author Deepa
 *
 */
public class PropertyFileUtility {

	/**
	 * This method will read the specific key value from the property file 
	 * @param key
	 * @return
	 * @throws Throwable
	 */
	public String readDataFromPropertyFile(String key) throws Throwable
	{
		//Step 1: load the file to file input stream
		FileInputStream fis = new FileInputStream(IConstantsUtility.PropertyFilePath);
		
		//Step 2: create object of properties from java
		Properties pObj = new Properties();
		
		//Step 3: load the file to properties object
		pObj.load(fis);
		
		//step 4: read data thru the key
		String value = pObj.getProperty(key);
		return value;
	}
}