package com.psl.configclient.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.psl.configclient.config.entity.Report;
import com.psl.configclient.excel.ExcelGenerator;
@Repository
public class ReportDaoImpl  implements ReportDao{
	
	
	@Autowired
	private JdbcTemplate jdbc;
	

	


	@Override
	public List<String> fetchtKey(List<Map<String,Object>> list){
		
		List<String> columns=new ArrayList<>();
		for(Map<String,Object> map: list){
			for(Map.Entry<String,Object> entry :map.entrySet()){
				
				
				columns.add(entry.getKey());
			}
			break;
		}
		
		
		return columns;
	}
	
	
	@Override
	public 	List<Map<String,Object>> whereClaue(String query,Object value) {
	
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		list=jdbc.queryForList(query,new Object[] {value});
	
		return list;
		
	}
	@Override
	public 	List<Map<String,Object>> reportGenerator(Report theReport) {
		 
		List<Map<String,Object>> list=new ArrayList<>();
		
		String query=theReport.getQuery();
		String table=theReport.getTable();
		List<String> columns=theReport.getColumns();
		
		
	
		
		if(!query.isEmpty()){
			
			list=jdbc.queryForList(query);
			columns=fetchtKey(list);
			
		}
			
		if(!query.isEmpty()){
			
			
			if(!query.contains("where"))
			{
				list=jdbc.queryForList(query);
			
			}
			else
			{
				
				//String sql1=query.substring(0,query.lastIndexOf("=")+1).concat("?").trim();
				String sql1[]=query.split("=");
				String sql2=sql1[0].trim().concat("= ? ");
				//System.out.println(sql1);
				
				//String where_clause=query.substring(query.lastIndexOf("where")+5).trim();
				
				String whereArr[]=query.split("where");
				String whereClause=whereArr[1].trim();
				
				String tokens[]=whereClause.split("=");
				Object value=tokens[1].trim().replace("'", "");
				System.out.println(value);
				list=whereClaue(sql2,value);
				
			}
			columns=fetchtKey(list);
			
		}
	
		else if(table !=null & columns!=null)
		{
			String col_list=String.join(",", columns);
			String query1="select " +col_list + " from "+ table;
			list=jdbc.queryForList(query1);
			
			
			
		}
	
	
				return list;
	}

	@Override
	public void test(List<Map<String,Object>> list) {
		
		
	}
}