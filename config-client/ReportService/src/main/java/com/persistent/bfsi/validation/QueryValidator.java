package com.persistent.bfsi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class QueryValidator implements ConstraintValidator<Query, String> {

	@Override
	public boolean isValid(String query, ConstraintValidatorContext context) {

		String queryToLowerCase = query.toLowerCase();
		if (!queryToLowerCase.isEmpty())
			if (queryToLowerCase.contains("select") && queryToLowerCase.contains("from"))
			{
				log.info("Valid Query input recieved from ReportController");
				return true;
			}
			else
			{	log.info("Invalid Query input recieved from ReportController");
				return false;
			}
		else
			return true;

	}

}
