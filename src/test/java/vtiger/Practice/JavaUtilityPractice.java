package vtiger.Practice;

import vtiger.GenericUtility.ExcelFileUtility;
import vtiger.GenericUtility.JavaUtility;
import vtiger.GenericUtility.PropertyFileUtility;

	public class JavaUtilityPractice {
		public static void main(String[] args) throws Throwable {
			JavaUtility jUtil = new JavaUtility();
			int ran = jUtil.getRandomNumber();
			System.out.println(ran);
			
			String date = jUtil.getSystemDate();
			System.out.println(date);
			
			String d=jUtil.getSystemDateInFormat();
			System.out.println(d);
			
			PropertyFileUtility pUtil = new PropertyFileUtility();
			String b = pUtil.readDataFromPropertyFile("browser");
			System.out.println(b);
			
			ExcelFileUtility eUtil = new ExcelFileUtility();
			eUtil.writeDataIntoExcel("sheet1", 6, 12, "Lucky");
			System.out.println("data added");
		}
	}
	