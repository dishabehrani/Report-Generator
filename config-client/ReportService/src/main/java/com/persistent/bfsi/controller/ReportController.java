package com.persistent.bfsi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.bfsi.model.Report;
import com.persistent.bfsi.service.ReportService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class ReportController {

	@Autowired
	private ReportService reportService;

	@PostMapping("/v1/report/generate")
	public String generate(@Valid @RequestBody Report report) {

		log.info("ReportController calling Reportservice to generate report");
		if (reportService.generate(report)) {
			log.info("ReportController receives success status from ReportService");
			return "Successfully written at location -> E:/report.xlsx";
		}

		log.error("ReportController receives failure status from ReportService");
		return "unable to write at loaction  -> E:/report.xlsx";
	}
}