package com.swop4a.money.transfers.repositories;

import com.swop4a.money.transfers.model.Account;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author swop4a
 * @since 28/04/2019 13:00
 */
public interface AccountRepository extends JpaRepository<Account, BigInteger> {

}
