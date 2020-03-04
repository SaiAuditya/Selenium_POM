package com.utilities;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {
	public  XSSFWorkbook ExcelWorkBook;
	public  XSSFSheet ExcelWorkSheet;

	public void SetExcelSheet(String SheetPath, String SheetName)
			throws IOException {
		ExcelWorkBook = new XSSFWorkbook(SheetPath);
		ExcelWorkSheet = ExcelWorkBook.getSheet(SheetName);
	}

	public int get_used_rows() {
		int lstrow = ExcelWorkSheet.getLastRowNum();
		return lstrow;
	}

	public String getExcelData(String columnName, int row_iterator) {

		DataFormatter formatter = new DataFormatter();

		int totalcells = ExcelWorkSheet.getRow(0).getLastCellNum();

		boolean flag = false;
		String data = null;
		for (int i = 0; i < totalcells; i++) {

			if (ExcelWorkSheet.getRow(0).getCell(i).getStringCellValue()
					.contains(columnName)) {
				data = formatter.formatCellValue(ExcelWorkSheet.getRow(
						row_iterator).getCell(i));
				flag = true;
				break;

			}
		}
		if (flag) {
			return data;
		} else {
			return null;
		}

	}

	public void setExcelData(String columnName, String data, int row_iterator) {
		int totalcells = ExcelWorkSheet.getRow(0).getLastCellNum();

		try {

			for (int i = 0; i < totalcells; i++) {
				if (ExcelWorkSheet.getRow(0).getCell(i).getStringCellValue()
						.contains(columnName)) {
					ExcelWorkSheet.getRow(row_iterator).getCell(i)
							.setCellValue(data);
					System.out.println("Data in the column :" + columnName
							+ " is set to " + data);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
