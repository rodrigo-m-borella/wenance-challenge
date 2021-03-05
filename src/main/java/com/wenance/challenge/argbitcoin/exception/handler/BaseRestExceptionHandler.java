package com.wenance.challenge.argbitcoin.exception.handler;

import com.wenance.challenge.argbitcoin.exception.ApiErrorMessageBody;
import com.wenance.challenge.argbitcoin.exception.BadRequestException;
import com.wenance.challenge.argbitcoin.exception.BaseCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;


@RestControllerAdvice
public class BaseRestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String defaultMessage = "Internal server error";

	protected ResponseEntity<Object> handleBaseRestException(BaseCustomException ex) {

		HttpStatus httpStatus = getHttpStatusCodeFromAnnotation(ex);
		ApiErrorMessageBody apiErrorMessageBody = createApiErrorBody(ex.getCustomMessage());

		return new ResponseEntity<>(apiErrorMessageBody, httpStatus);
	}


	private HttpStatus getHttpStatusCodeFromAnnotation(BaseCustomException ex) {

		ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);

		if (!Objects.isNull(responseStatus)) {
			return responseStatus.value();
		}

		return HttpStatus.INTERNAL_SERVER_ERROR;
	}


	private ApiErrorMessageBody createApiErrorBody(String errorMessage) {
		return new ApiErrorMessageBody(errorMessage);
	}

	@ExceptionHandler({BaseCustomException.class})
	public ResponseEntity<Object> handleIntegrationsBaseException(BaseCustomException ex) {
		return this.handleBaseRestException(ex);
	}


	@ExceptionHandler({BadRequestException.class})
	protected ResponseEntity<Object> handleBadRequestExceptions(BadRequestException ex) {

		ApiErrorMessageBody apiErrorMessageBody = createApiErrorBody(ex.getCustomMessage());

		return new ResponseEntity<>(apiErrorMessageBody, HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler({TimeoutException.class})
	public ResponseEntity<Object> handleTimeoutException(TimeoutException ex) {

		ApiErrorMessageBody apiErrorMessageBody = createApiErrorBody(ex.getLocalizedMessage());

		return new ResponseEntity<>(apiErrorMessageBody, HttpStatus.GATEWAY_TIMEOUT);
	}


	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleGenericException(Exception ex) {

		ApiErrorMessageBody apiErrorMessageBody = createApiErrorBody(defaultMessage);
		
		return new ResponseEntity<>(apiErrorMessageBody, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {

//		ApiErrorMessageBody apiErrorMessageBody = createApiErrorBody("Input validation Error");
		ApiErrorMessageBody apiErrorMessageBody = createApiErrorBody(ex.getLocalizedMessage());

		return new ResponseEntity<>(apiErrorMessageBody, HttpStatus.BAD_REQUEST);
	}

}