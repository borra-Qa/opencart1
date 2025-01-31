package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	String path;
	
	public ExcelUtilities(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String sheetname) throws IOException
	{
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetname);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowcount;
		
	}
	
	public int getCellCount(String sheetname,int rownum) throws IOException
	{
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellcount;
		
	}
	
	public String getCellData(String sheetname,int rownum,int cellnum) throws IOException
	{
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		cell=row.getCell(cellnum);
		
		String data;
		//data=cell.toString();
		DataFormatter formatter=new DataFormatter();
		try {
		data=formatter.formatCellValue(cell); //Returns the formatted value of String regardless of format
		}catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fis.close();
		return data;
	}
	
	public void setCellData(String sheetname,int rownum,int cellnum,String data) throws IOException
	{
		File xfile=new File(path);
		if(!xfile.exists())//If file not exists then create new file
		{
			workbook=new XSSFWorkbook();
			fos=new FileOutputStream(path);
			workbook.write(fos);
		}
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		if(workbook.getSheetIndex(sheetname)==-1)//If sheet not exists then create new file
			workbook.createSheet(sheetname);
		
		sheet=workbook.getSheet(sheetname);
		
		if(sheet.getRow(rownum)==null) //If row not exists then create new file
			sheet.createRow(rownum);
		row=sheet.getRow(rownum);
		cell=row.createCell(cellnum);
		cell.setCellValue(data);
		fos=new FileOutputStream(path);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}
	
	public void fillGreenColour(String sheetname,int rownum,int cellnum) throws IOException
	{
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		cell=row.getCell(cellnum);
		style=workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fos=new FileOutputStream(path);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}
	public void fillRedColour(String sheetname,int rownum,int cellnum) throws IOException
	{
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		cell=row.getCell(cellnum);
		
		style=workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fos=new FileOutputStream(path);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}
}
