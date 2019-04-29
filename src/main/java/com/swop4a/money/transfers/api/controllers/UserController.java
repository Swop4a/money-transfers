package com.swop4a.money.transfers.api.controllers;

import com.swop4a.money.transfers.model.User;
import com.swop4a.money.transfers.services.UserService;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author swop4a
 * @since 28/04/2019 16:52
 */
@RestController
@Api(value = "User controller", description = "Controller for user operations")
public class UserController {

	private final UserService service;

	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping("/users")
	public List<User> users() {
		return service.retrieveAll();
	}
}
