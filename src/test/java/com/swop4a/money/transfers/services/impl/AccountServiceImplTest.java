package com.swop4a.money.transfers.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.swop4a.money.transfers.exceptions.AccountNotFoundException;
import com.swop4a.money.transfers.exceptions.NoEnoughBalanceException;
import com.swop4a.money.transfers.exceptions.UnsupportedOperationException;
import com.swop4a.money.transfers.model.Account;
import com.swop4a.money.transfers.repositories.AccountRepository;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

	private static final BigInteger ID_FROM = new BigInteger("1");
	private static final BigInteger ID_TO = new BigInteger("2");

	@InjectMocks
	private AccountServiceImpl service;

	@Mock
	private AccountRepository repository;

	@Before
	public void setUp() {
		Account account = new Account();
		account.setId(ID_FROM);
		account.setBalance(new BigDecimal("30000"));

		when(repository.findById(ID_FROM)).thenReturn(Optional.of(account));

		account = new Account();
		account.setId(ID_TO);
		account.setBalance(new BigDecimal("35000"));

		when(repository.findById(ID_TO)).thenReturn(Optional.of(account));
	}

	@Test
	public void transferMoneyOk() {
		Account account = service.transferMoney(ID_FROM, ID_TO, new BigDecimal("10000"));
		assertNotNull(account);

		assertEquals(ID_FROM, account.getId());
		assertEquals(new BigDecimal("20000"), account.getBalance());
	}

	@Test(expected = AccountNotFoundException.class)
	public void transferMoneyAccountNotFound() {
		service.transferMoney(BigInteger.ZERO, ID_TO, new BigDecimal("10000"));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void transferMoneyNotSupportedOperation() {
		service.transferMoney(ID_FROM, ID_TO, new BigDecimal("-10000"));
	}

	@Test(expected = NoEnoughBalanceException.class)
	public void transferMoneyNoEnoughMoney() {
		service.transferMoney(ID_FROM, ID_TO, new BigDecimal("100000"));
	}
}