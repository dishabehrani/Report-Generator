package com.psl.configclient.config.entity;

import java.util.List;




public class Report {

	public String table;
	public List<String> columns;
	public String query;
	
	public Report(){}
	
	
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public  List<String> getColumns() {
		return columns;
	}
	public void setColumns( List<String> columns) {
		this.columns = columns;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	
	
}
