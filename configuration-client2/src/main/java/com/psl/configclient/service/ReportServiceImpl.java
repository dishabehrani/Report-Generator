package com.psl.configclient.service;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.configclient.config.entity.Report;
import com.psl.configclient.dao.ReportDao;

@Service
public class ReportServiceImpl implements ReportService {
	
	
	

	@Autowired
	private ReportDao dao;
	
	
	


	@Override
	public List<Map<String,Object>> reportGenerator(Report theReport) {
	
		return dao.reportGenerator(theReport);
		}





	@Override
	public List<String> fetchtKey(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		return dao.fetchtKey(list);
	}





	@Override
	public void test(List<Map<String, Object>> list) {
		dao.test(list);
		
	}

}
	