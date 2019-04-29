package com.swop4a.money.transfers.services;

import com.swop4a.money.transfers.model.Account;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author swop4a
 * @since 28/04/2019 12:57
 */
public interface AccountService {

	/**
	 * Method provide transfer money operation between two internal accounts.
	 * If account's balance is not enough then will raised exception
	 *
	 * @param from source point
	 * @param to destination point
	 * @param amount is amount what need to transfer
	 * @return account from transfer
	 */
	Account transferMoney(BigInteger from, BigInteger to, BigDecimal amount);
}
