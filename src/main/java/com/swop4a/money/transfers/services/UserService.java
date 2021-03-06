package com.swop4a.money.transfers.services;

import com.swop4a.money.transfers.model.User;
import java.util.List;

/**
 * @author swop4a
 * @since 28/04/2019 16:47
 */
public interface UserService {

	/**
	 * Retrieve all existing accounts
	 *
	 * @return list of all accounts
	 */
	List<User> retrieveAll();
}
