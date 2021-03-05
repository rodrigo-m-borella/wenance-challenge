package com.wenance.challenge.argbitcoin.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class ApiErrorMessageBody {

	private String errorMessage;
	private String code;

	public ApiErrorMessageBody(String errorMessage) {
		this.errorMessage = errorMessage;
	}



}
