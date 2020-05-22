package com.psl.configclient.service;

import java.util.List;
import java.util.Map;

import com.psl.configclient.config.entity.Report;

public interface ReportService {
	
	

	public List<Map<String,Object>> reportGenerator(Report theReport);
	public List<String> fetchtKey(List<Map<String,Object>> list);
	public void test(List<Map<String,Object>> list);
}
