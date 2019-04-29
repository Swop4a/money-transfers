package com.swop4a.money.transfers.exceptions;

/**
 * @author swop4a
 * @since 28/04/2019 17:33
 */
public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException(String message) {
		super(message);
	}
}