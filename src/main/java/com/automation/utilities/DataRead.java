package com.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class DataRead{
	
	public static  String filepath=System.getProperty("user.dir")+"/TestData/TestSheet.xls";
	public static Properties prop;
	public static String env;
	public static FileInputStream fs;
	public static Workbook wb;
	public static Sheet sh;
	public static int executeIter;
    public static File file;
    public static int totalRowsCount;
    public static Label label;
    public static WritableWorkbook writableWorkbook;
    public static WritableSheet writableSheet;

	
	public static String getData(String ColumnVal){
	
		String readColumnIndexValue = sh.getCell(sh.findCell(ColumnVal).getColumn(), executeIter).getContents();
		return readColumnIndexValue;
	}
	
	public static String getData(String SheetName, String ColumnVal,int RowNumber) throws BiffException, IOException{
		
		fs = new FileInputStream(filepath);
		wb = Workbook.getWorkbook(fs);
		sh = wb.getSheet(SheetName);
		System.out.println("Sheet "+SheetName);
		
		String readColumnIndexValue = sh.getCell(sh.findCell(ColumnVal).getColumn(), RowNumber).getContents();
		System.out.println("readColumnIndexValue  "+readColumnIndexValue);
		sh.getCell(sh.findCell(ColumnVal).getColumn(), RowNumber).getContents();
		
		return readColumnIndexValue;
	}
	
	public static void setCellData(String SheetName, String ColumnVal,int RowNumber,String sData) throws BiffException, IOException, RowsExceededException, WriteException {	
		file = new File(filepath);
		wb = Workbook.getWorkbook(file);
		writableWorkbook = Workbook.createWorkbook(new File(filepath),wb);
		writableSheet = writableWorkbook.getSheet(SheetName);
		System.out.println("Sheet "+SheetName);	
		int readColumnNumnber = sh.findCell(ColumnVal).getColumn();
		label= new Label(readColumnNumnber, RowNumber, sData);
		writableSheet.addCell(label);
		writableWorkbook.write();
        writableWorkbook.close(); 
	}
	
	public static int getTotalNumberOfRows(String SheetName) throws BiffException, IOException{
        fs = new FileInputStream(filepath);
        wb = Workbook.getWorkbook(fs);
        sh = wb.getSheet(SheetName);
        System.out.println("Sheet "+SheetName);
        totalRowsCount = sh.getRows();
        return totalRowsCount;    
    }
	
	public static int getTotalNumberOfRows(String SheetName, String filePath) throws BiffException, IOException{
		try {
			file = new File(filePath);
			fs = new FileInputStream(file);
	        wb = Workbook.getWorkbook(fs);
	        sh = wb.getSheet(SheetName);
	        System.out.println("Sheet name is: "+SheetName);
	        totalRowsCount = sh.getRows();
	        wb.close();    
		} catch (Exception e) {

		} 
        return totalRowsCount;
    }
	
	public static String getData(String filePath,String SheetName, String ColumnVal,int RowNumber) throws BiffException, IOException
	{
		try {
			fs = new FileInputStream(filePath);
			wb = Workbook.getWorkbook(fs);
			sh = wb.getSheet(SheetName);
			System.out.println("Sheet name is:  "+SheetName);		
			String cellValue = sh.getCell(sh.findCell(ColumnVal).getColumn(), RowNumber).getContents();
			System.out.println("The cell value is :  "+cellValue);
			wb.close();
			return cellValue;
		} catch (Exception e) {
			
		}
		return ColumnVal; 			
	}
	
	public static void setData(String filePath, String SheetName, String ColumnVal,int RowNumber,String sData) throws BiffException, IOException, RowsExceededException, WriteException
	{	
		try {
			file = new File(filePath);
			wb = Workbook.getWorkbook(file);
			writableWorkbook = Workbook.createWorkbook(new File(filePath),wb);
			writableSheet = writableWorkbook.getSheet(SheetName);
			System.out.println("Sheet "+SheetName);	
			int readColumnNumnber = writableSheet.findCell(ColumnVal).getColumn();
			label= new Label(readColumnNumnber, RowNumber, sData);
			writableSheet.addCell(label);
			writableWorkbook.write();
	        writableWorkbook.close(); 
		} catch (Exception e) {
			System.out.println("Data value is not set for row number :"+RowNumber);
			e.printStackTrace();
		} 
		
	}

}

