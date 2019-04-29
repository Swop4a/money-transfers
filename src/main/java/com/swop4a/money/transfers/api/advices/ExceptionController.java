package com.swop4a.money.transfers.api.advices;

import com.swop4a.money.transfers.exceptions.AccountNotFoundException;
import com.swop4a.money.transfers.exceptions.NoEnoughBalanceException;
import com.swop4a.money.transfers.exceptions.UnsupportedOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author swop4a
 * @since 28/04/2019 13:04
 */
@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> exception(AccountNotFoundException ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoEnoughBalanceException.class)
	public ResponseEntity<ErrorResponse> exception(NoEnoughBalanceException ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnsupportedOperationException.class)
	public ResponseEntity<ErrorResponse> exception(UnsupportedOperationException ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorResponse> exception(Throwable ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<ErrorResponse> buildErrorResponse(String message, HttpStatus status) {
		ErrorResponse response = new ErrorResponse(message);
		return new ResponseEntity<>(response, status);
	}

	class ErrorResponse {

		private String message;

		ErrorResponse(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}
}
