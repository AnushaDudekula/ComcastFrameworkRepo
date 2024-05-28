package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class excelUtility {
	public String getDataFromExcel(String Sheetname,int rowNum,int celNum) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./TestData/TestScriptData.xlsx");
		Workbook book=WorkbookFactory.create(fis);
		String value=book.getSheet(Sheetname).getRow(rowNum).getCell(celNum).getStringCellValue();
		return value;
	}
public int getRowCount(String Sheetname) throws IOException {
	FileInputStream fis=new FileInputStream("./TestData/TestScriptData.xlsx");
	Workbook book=WorkbookFactory.create(fis);
	 int rowCount=book.getSheet(Sheetname).getLastRowNum();
	return rowCount;
}
public void setDataIntoExcel(String Sheetname,int rowNum,int celNum,String value) throws IOException {
	FileInputStream fis=new FileInputStream("./TestData/TestScriptData.xlsx");
	Workbook book=WorkbookFactory.create(fis);
	book.getSheet(Sheetname).getRow(rowNum).createCell(celNum).setCellValue(value);
	FileOutputStream fos=new FileOutputStream("./TestData/TestScriptData.xlsx");
	book.write(fos);
	book.close();
}
}
