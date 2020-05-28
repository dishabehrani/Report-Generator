package com.persistent.bfsi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistent.bfsi.dao.ReportDAO;
import com.persistent.bfsi.excel.ExcelGenerator;
import com.persistent.bfsi.exception.BadQueryRequest;
import com.persistent.bfsi.model.Report;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportDAO reportDAO;

	@Autowired
	private ExcelGenerator excelGenerator;

	@Override
	public boolean generate(Report report) {

		boolean flag = false;

		log.info("ReportService fetching query output from ReportDAO");
		List<Map<String, Object>> list = reportDAO.generate(report);
		if (list.isEmpty()) {
			log.error("Exception occurred at ReportService ,no result returned for the query input");
			throw new BadQueryRequest("No result found for the query input !!!");
		}

		log.info("ReportService fetching columns from ReportDAO");
		List<String> columns = reportDAO.fetchColumns(list);

		log.info("ReportService calls ExelGenerator method to convert JSON to Excel");
		if (excelGenerator.JsonToExcel(list, columns))
			flag = true;

		return flag;
	}

}
