package com.persistent.bfsi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
	private int status;
	private String message;
	private Long timeStamp;

}
