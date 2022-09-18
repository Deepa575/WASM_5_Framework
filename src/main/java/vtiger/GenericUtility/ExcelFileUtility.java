package vtiger.GenericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class contains all the generic methods to read data and write data to Excel sheet
 * @author Deepa
 *
 */
public class ExcelFileUtility {

	/**
	 * This method will read the data from excel sheet and return the value
	 * @param sheet
	 * @param row
	 * @param cell
	 * @return
	 * @throws Throwable
	 */
	public String readDataFromExcel(String sheet, int row, int cell) throws Throwable {
		//Step 1: load the file into file input stream
		FileInputStream fis = new FileInputStream(IConstantsUtility.ExcelFilePath);
		
		//Step 2: Create workbook using workbook factory
		Workbook wb = WorkbookFactory.create(fis);
		
		//Step 3: navigate to sheet
		Sheet sh = wb.getSheet(sheet);
		
		//Step 4: Navigate to row
		Row r = sh.getRow(row);
		
		//Step 5: navigate to cell
		Cell c = r.getCell(cell);
		
		//Step 6: read the response cell value
		String value = c.getStringCellValue();
		
		//closing the workbook
		wb.close();
		return value;
	}
	
	/**
	 * This method will return the last row number in particular sheet
	 * @param sheet
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getRowCount(String sheet) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream(IConstantsUtility.ExcelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheet);
		int lastRow = sh.getLastRowNum();
		return lastRow;
	}
	
	/**
	 * This method will write data into the excel sheet
	 * @param sheet
	 * @param row
	 * @param cell
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeDataIntoExcel(String sheet,int row, int cell, String value) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream(IConstantsUtility.ExcelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheet);
		Row r = sh.getRow(row);
		Cell c = r.createCell(cell);
		c.setCellValue(value);
		
		FileOutputStream fos = new FileOutputStream(IConstantsUtility.ExcelFilePath);
		wb.write(fos);
		wb.close();	
	}
}
