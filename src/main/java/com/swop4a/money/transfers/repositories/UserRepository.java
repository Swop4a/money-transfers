package com.swop4a.money.transfers.repositories;

import com.swop4a.money.transfers.model.User;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author swop4a
 * @since 28/04/2019 16:49
 */
public interface UserRepository extends JpaRepository<User, BigInteger> {

}
