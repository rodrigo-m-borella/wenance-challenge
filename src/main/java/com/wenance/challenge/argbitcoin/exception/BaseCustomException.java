package com.wenance.challenge.argbitcoin.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
@Setter
public class BaseCustomException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final String defaultMsg = "Internal server error";

	private String customMessage;

	public BaseCustomException(String msg) {
		super(StringUtils.hasLength(msg)?msg:defaultMsg);
	}

}
