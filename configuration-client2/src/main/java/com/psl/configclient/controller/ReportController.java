package com.psl.configclient.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psl.configclient.config.entity.Report;
import com.psl.configclient.excel.ExcelGenerator;
import com.psl.configclient.exception.BadQueryRequest;
import com.psl.configclient.service.ReportService;

@RestController
@RequestMapping("/api")
public class ReportController {
	
	@Autowired 
	private ReportService service;
	

	@Autowired
	private ExcelGenerator excel;

	
	
	
	@RequestMapping("/test")
	public String  hello()
	{
		return "hello";
		
	}
	
	
	
	
	
//	@PostMapping("/report")
//	public List<Map<String,Object>> generate(@RequestBody Report theReport)
//	{
//		
//		return service.reportGenerator(theReport);
//	}
	
	@PostMapping("/download")
	public String downloadExcel(@RequestBody Report theReport)
	{
		List<Map<String,Object>> list=service.reportGenerator(theReport);
		if(list.isEmpty()) {
			throw new BadQueryRequest("No result found for the query input !!!");
		}
		
		
		List<String> columns=service.fetchtKey(list);
		try {
			excel.ToExcel(list,columns);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return "successs written at location -> E:/report.xlsx";
		
	}
	
	
}