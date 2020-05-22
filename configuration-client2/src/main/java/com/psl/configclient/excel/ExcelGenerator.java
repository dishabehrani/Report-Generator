package com.psl.configclient.excel;

import java.io.File;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psl.configclient.dao.ReportDaoImpl;


@Component
public class ExcelGenerator {
	
	
	
	public  void ToExcel(List<Map<String,Object>> list,List<String>columns) throws IOException {
	    
		
//		 for(String cols:columns) {
//			 System.out.println(cols);
//		 }
//		 
//		 for(Map<String,Object> map:list) {
//			 for(Map.Entry<String, Object>  entry :map.entrySet())
//			 {
//				 System.out.println(entry.getKey()+entry.getValue());
//				 
//			 }
//			 
//		 }

			
		
			        Workbook workbook = new XSSFWorkbook();
			      
			        Sheet sheet = workbook.createSheet("Report");
			   
			        Font headerFont = workbook.createFont();
			        headerFont.setBold(true);
			        headerFont.setColor(IndexedColors.BLUE.getIndex());
			   
			        CellStyle headerCellStyle = workbook.createCellStyle();
			        headerCellStyle.setFont(headerFont);
			   
			        // Row for Header
			        Row headerRow = sheet.createRow(0);
			      
			        int i=0;
			        for(String cols:columns){
			        	Cell cell=headerRow.createCell(i++);
			        	cell.setCellValue(cols);
			        	cell.setCellStyle(headerCellStyle);
			        	
			   	}
		
		
		int rowIndx=1;
		for(Map<String,Object> map:list ){
			i=0;
			Row row=sheet.createRow(rowIndx++);
			for(Map.Entry<String, Object>entry :map.entrySet()){
				

			
			 
			
					if(entry.getValue() instanceof String) {
						row.createCell(i).setCellValue((String)entry.getValue());
						
					
					}
					else if(entry.getValue()  instanceof Integer) {
						row.createCell(i).setCellValue((Integer)entry.getValue());
					
					}
				
				
				i++;
				
			}
		}
				
		FileOutputStream out=new FileOutputStream(new File("E:/report.xlsx"));
	      workbook.write(out);
	     out.close();
				
				
				
			
	}

}
