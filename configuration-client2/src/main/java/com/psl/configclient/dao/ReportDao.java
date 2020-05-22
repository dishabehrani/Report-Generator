package com.psl.configclient.dao;

import java.util.List;
import java.util.Map;

import com.psl.configclient.config.entity.Report;

public interface ReportDao {
	public List<Map<String,Object>> reportGenerator(Report theReport );
	public List<String> fetchtKey(List<Map<String,Object>> list);
	public void test(List<Map<String,Object>> list);
	public 	List<Map<String,Object>> whereClaue(String query,Object value);		
}
