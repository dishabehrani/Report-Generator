package com.persistent.bfsi.model;

import java.util.List;
import javax.validation.constraints.NotNull;
import com.persistent.bfsi.validation.Query;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Report {

	@NotNull(message = "please provide a valid table name")
	public String table;

	@NotNull(message = "please provide a valid column list")
	public List<String> columns;

	@NotNull(message = "please provide a valid query")
	@Query
	public String query;

}
