package com.persistent.bfsi.dao;

import java.util.List;
import java.util.Map;

import com.persistent.bfsi.model.Report;

public interface ReportDAO {
	List<Map<String, Object>> generate(Report report);

	List<String> fetchColumns(List<Map<String, Object>> list);

	List<Map<String, Object>> queryWithWhereClause(String query, Object value);
}
