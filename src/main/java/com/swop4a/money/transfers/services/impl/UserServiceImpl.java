package com.swop4a.money.transfers.services.impl;

import com.swop4a.money.transfers.model.User;
import com.swop4a.money.transfers.repositories.UserRepository;
import com.swop4a.money.transfers.services.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author swop4a
 * @since 28/04/2019 16:48
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	@Autowired
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<User> retrieveAll() {
		log.info("RETRIEVING USERS");
		List<User> users = repository.findAll();
		log.info("SUCCESSFULLY RETRIEVED {} USERS", users.size());

		return users;
	}
}
