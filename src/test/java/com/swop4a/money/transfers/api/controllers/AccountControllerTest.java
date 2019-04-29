package com.swop4a.money.transfers.api.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.swop4a.money.transfers.exceptions.AccountNotFoundException;
import com.swop4a.money.transfers.exceptions.NoEnoughBalanceException;
import com.swop4a.money.transfers.exceptions.UnsupportedOperationException;
import com.swop4a.money.transfers.model.Account;
import com.swop4a.money.transfers.services.impl.AccountServiceImpl;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

	private static final BigInteger ID_FROM = new BigInteger("1");
	private static final BigInteger ID_TO = new BigInteger("2");
	private static final BigInteger ID_NOT_EXISTS = new BigInteger("3");

	@Autowired
	private MockMvc mvc;

	@MockBean
	private AccountServiceImpl service;

	@Before
	public void setUp() {
		when(service.transferMoney(ID_FROM, ID_TO, new BigDecimal(100))).thenReturn(new Account());
		when(service.transferMoney(ID_FROM, ID_TO, new BigDecimal(200))).thenThrow(new NoEnoughBalanceException("msg"));
		when(service.transferMoney(ID_FROM, ID_NOT_EXISTS, new BigDecimal(200))).thenThrow(new AccountNotFoundException("msg"));
		when(service.transferMoney(ID_FROM, ID_TO, new BigDecimal(-200))).thenThrow(new UnsupportedOperationException("msg"));
	}

	@Test
	public void transferOk() throws Exception {
		mvc.perform(get(String.format("/transfer/%s/%s/%s", ID_FROM, ID_TO, 100))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().is(200));
	}

	@Test
	public void transferBalanceNotEnough() throws Exception {
		mvc.perform(get(String.format("/transfer/%s/%s/%s", ID_FROM, ID_TO, 200))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().is(400));
	}

	@Test
	public void transferAccountNotFound() throws Exception {
		mvc.perform(get(String.format("/transfer/%s/%s/%s", ID_FROM, ID_NOT_EXISTS, 200))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().is(404));
	}

	@Test
	public void transferUnsupportedOperation() throws Exception {
		mvc.perform(get(String.format("/transfer/%s/%s/%s", ID_FROM, ID_TO, -200))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().is(405));
	}
}