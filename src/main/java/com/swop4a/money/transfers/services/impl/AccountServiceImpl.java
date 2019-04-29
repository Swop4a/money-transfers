package com.swop4a.money.transfers.services.impl;

import com.swop4a.money.transfers.exceptions.AccountNotFoundException;
import com.swop4a.money.transfers.exceptions.NoEnoughBalanceException;
import com.swop4a.money.transfers.exceptions.UnsupportedOperationException;
import com.swop4a.money.transfers.model.Account;
import com.swop4a.money.transfers.repositories.AccountRepository;
import com.swop4a.money.transfers.services.AccountService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.BiFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author swop4a
 * @since 28/04/2019 12:57
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository repository;

	@Autowired
	public AccountServiceImpl(AccountRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public Account transferMoney(BigInteger from, BigInteger to, BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new UnsupportedOperationException(
				String.format("AMOUNT FOR TRANSFER SHOULD BE POSITIVE: %s. SWAP 'FROM' <=> 'TO'", amount));
		}

		log.info("Transferring {} from account: {} , to account {}", amount, from, to);

		Account fromAccount = retrieveAccount(from);
		BigDecimal balance = fromAccount.getBalance();
		if (balance.compareTo(amount) < 0) {
			throw new NoEnoughBalanceException(String.format("ACCOUNT BALANCE %s < %s AMOUNT", balance, amount));
		}

		Account toAccount = retrieveAccount(to);
		transferMoney(fromAccount, toAccount, amount);

		log.info("MONEY TRANSFERRED SUCCESSFULLY");

		return fromAccount;
	}

	private Account retrieveAccount(BigInteger id) {
		Account account = repository.findById(id)
			.orElseThrow(() -> new AccountNotFoundException(String.format("ACCOUNT %s NOT FOUND", id)));
		log.info("RETRIEVED ACCOUNT {}", account);

		return account;
	}

	private void transferMoney(Account accountFrom, Account accountTo, BigDecimal amount) {
		updateBalanceForAccount(accountFrom, amount, BigDecimal::subtract);
		updateBalanceForAccount(accountTo, amount, BigDecimal::add);
	}

	private void updateBalanceForAccount(Account account, BigDecimal amount,
		BiFunction<BigDecimal, BigDecimal, BigDecimal> function) {
		BigDecimal balance = account.getBalance();
		account.setBalance(function.apply(balance, amount));

		log.info("ACCOUNT {} UPDATED SUCCESSFULLY", repository.save(account));
	}
}
