package com.actitime.genericlibraries;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLibrary {

	private InputStream fis = null;
	private FileOutputStream fos = null;
	private Workbook workbook = null;
	private Sheet sheet = null;;
	private Row row = null;
	private Cell cell = null;
	private String filePath = null;

	public ExcelLibrary() throws InvalidFormatException, IOException {
		filePath = "actiTimeTestdata.xlsx";
	}

	private void openFile() {
		fis = this.getClass().getClassLoader().getResourceAsStream(filePath);
	}

	public String getExcelData(int rowNumber, int columnNumber,
			String sheetNumber) throws InvalidFormatException, IOException {
		openFile();

		workbook = WorkbookFactory.create(fis);
		sheet = workbook.getSheet(sheetNumber);
		row = sheet.getRow(rowNumber);
		fis.close();
		return row.getCell(columnNumber).getStringCellValue();

	}

	public int getRowCount(String sheetNumber) throws InvalidFormatException,
			IOException {
		openFile();
		
		workbook = WorkbookFactory.create(fis);
		sheet = workbook.getSheet(sheetNumber);
		fis.close();
		return sheet.getLastRowNum();
	}

	public String getCellData(String sheetNumber, int rowNumber,
			int columnNumber) throws InvalidFormatException, IOException {
		openFile();
		
		workbook = WorkbookFactory.create(fis);
		sheet = workbook.getSheet(sheetNumber);

		Cell cell = sheet.getRow(rowNumber).getCell(columnNumber);
		String retValue = null;
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
			retValue = String.valueOf(Math.round(cell.getNumericCellValue()));
		else
			retValue = cell.getStringCellValue();
		fis.close();
		return retValue;
	}

	public void setExcelValue(int rowNumber, int columnNumber,
			String sheetNumber, String data) throws IOException,
			InvalidFormatException {
		openFile();
		
		workbook = WorkbookFactory.create(fis);
		sheet = workbook.getSheet(sheetNumber);
		row = sheet.getRow(rowNumber);
		cell = row.createCell(columnNumber);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue(data);
		fis.close();
		fos = new FileOutputStream(filePath);
		workbook.write(fos);
		fos.flush();
		fos.close();
	}

}