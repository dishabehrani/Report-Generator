package com.persistent.bfsi.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ExcelGenerator {

	boolean flag = false;

	public boolean JsonToExcel(List<Map<String, Object>> list, List<String> columns) {

		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Report");

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.BLUE.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		Row headerRow = sheet.createRow(0);

		int columnIndex = 0;
		for (String columnList : columns) {
			Cell cell = headerRow.createCell(columnIndex++);
			cell.setCellValue(columnList);
			cell.setCellStyle(headerCellStyle);

		}

		int rowIndex = 1;
		for (Map<String, Object> map : list) {
			columnIndex = 0;
			Row row = sheet.createRow(rowIndex++);
			for (Map.Entry<String, Object> entry : map.entrySet()) {

				if (entry.getValue() instanceof String) {
					row.createCell(columnIndex).setCellValue((String) entry.getValue());

				} else if (entry.getValue() instanceof Integer) {
					row.createCell(columnIndex).setCellValue((Integer) entry.getValue());

				}

				columnIndex++;

			}
		}

		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File("E:/report.xlsx"));
			flag = true;
		} catch (FileNotFoundException e) {
			log.error("File Not Exception occured", e);
			e.printStackTrace();
			flag = false;
		}
		try {
			log.info("ExeclGenerator successfully writes into Excel");
			workbook.write(out);
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
			flag = false;
		}

		finally {
			try {
				if (out != null) {
					log.info("Stream successfully closed");
					out.close();
					flag = true;
				}
			} catch (IOException e) {
				log.error("Error in closing the Stream", e);
				flag = false;
			}
		}

		return flag;
	}

}
