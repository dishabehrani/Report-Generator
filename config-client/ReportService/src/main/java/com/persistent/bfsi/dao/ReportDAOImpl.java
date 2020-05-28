package com.persistent.bfsi.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.persistent.bfsi.model.Report;

@Repository
public class ReportDAOImpl implements ReportDAO {

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<String> fetchColumns(List<Map<String, Object>> list) {

		List<String> columns = new ArrayList<>();
		for (Map<String, Object> map : list) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {

				columns.add(entry.getKey());
			}
			break;
		}

		return columns;
	}

	@Override
	public List<Map<String, Object>> queryWithWhereClause(String query, Object value) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		list = jdbc.queryForList(query, value);

		return list;

	}

	@Override
	public List<Map<String, Object>> generate(Report report) {

		List<Map<String, Object>> list = new ArrayList<>();

		String query = report.getQuery();
		String table = report.getTable();
		List<String> columns = report.getColumns();

		if (!query.isEmpty()) {

			if (!query.contains("where")) {
				list = jdbc.queryForList(query);

			} else {

				String[] sql = query.split("=");
				String whereClauseQuery = sql[0].trim().concat("= ? ");

				String[] splitQuery = query.split("where");
				String whereClause = splitQuery[1].trim();

				String[] splitWhereClause = whereClause.split("=");
				Object value = splitWhereClause[1].trim().replace("'", "");

				list = queryWithWhereClause(whereClauseQuery, value);

			}
			columns = fetchColumns(list);

		} else if (table != null & columns != null) {
			String columnList = String.join(",", columns);
			String tableColumnQuery = "select " + columnList + " from " + table;
			list = jdbc.queryForList(tableColumnQuery);

		}

		return list;
	}

}