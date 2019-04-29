package com.swop4a.money.transfers.api.controllers;

import com.swop4a.money.transfers.model.Account;
import com.swop4a.money.transfers.services.AccountService;
import io.swagger.annotations.Api;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author swop4a
 * @since 28/04/2019 12:58
 */
@RestController
@Api(value = "Account controller", description = "Controller for account operations")
public class AccountController {

	private final AccountService service;

	@Autowired
	public AccountController(AccountService service) {
		this.service = service;
	}

	@GetMapping("/transfer/{from}/{to}/{amount}")
	public Account transfer(@PathVariable BigInteger from, @PathVariable BigInteger to, @PathVariable BigDecimal amount) {
		return service.transferMoney(from, to, amount);
	}
}
