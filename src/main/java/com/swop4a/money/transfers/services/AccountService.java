package com.swop4a.money.transfers.services;

import com.swop4a.money.transfers.model.Account;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author swop4a
 * @since 28/04/2019 12:57
 */
public interface AccountService {

	Account transferMoney(BigInteger from, BigInteger to, BigDecimal amount);
}
