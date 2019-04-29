package com.swop4a.money.transfers.exceptions;

/**
 * @author swop4a
 * @since 28/04/2019 17:39
 */
public class NoEnoughBalanceException extends RuntimeException {

	public NoEnoughBalanceException(String message) {
		super(message);
	}
}
