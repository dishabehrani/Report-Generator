package com.persistent.bfsi.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Report {

	public String table;
	public List<String> columns;
	public String query;

}
