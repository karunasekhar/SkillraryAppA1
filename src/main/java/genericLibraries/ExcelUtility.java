package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import java.util.Map;

import org.apache.poi.EncryptedDocumentException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtility {
	
	private Workbook wb;
	/**
	 * this method is used to initialization excel
	 * @param excelPath
	 */
	public void excelInitialization(String excelPath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(excelPath);
			
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
		try {
			wb = WorkbookFactory.create(fis);
			
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * this method is used to read data from excel
	 * @param sheetname
	 * @param expectedtest 
	 * @param expectedtest
	 * @return
	 */
	public Map<String, String> readFromExcel(String sheetname,String expectedtest){
		Map<String, String> map = new HashMap<String, String>();
		DataFormatter df = new DataFormatter();
		
		Sheet sh= wb.getSheet(sheetname);
		
		for(int i=0;i<=sh.getLastRowNum();i++) {
			if(df.formatCellValue(sh.getRow(i).getCell(1)).equals(expectedtest)){
				for(int j=1;j<=sh.getLastRowNum();j++) {
					map.put(df.formatCellValue(sh.getRow(j).getCell(2)), 
							df.formatCellValue(sh.getRow(j).getCell(3)));
					if(df.formatCellValue(sh.getRow(j).getCell(2)).equals("####"))
						break;
					
				}
				break;
			}
			
			}
			return map;
		}
	
	public void writeToExcel(String sheetname, String status, String expectedtest,String excelpath) {
		Sheet sh = wb.getSheet(sheetname);
		for(int i=0;i<=sh.getLastRowNum();i++) {
			if(sh.getRow(i).getCell(1).getStringCellValue().equals(expectedtest)) {
				sh.getRow(i).createCell(4).setCellValue(status);
				break;
				
			}
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(excelpath);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}
		try {
			wb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * this method is used to close the excel
	 */
	public void closeExcel() {
		try {
			wb.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

				
			

	


		