package com.swop4a.money.transfers.model;

import java.math.BigInteger;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author swop4a
 * @since 28/04/2019 12:57
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	private BigInteger id;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;

	@OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
	private Set<Account> accounts;
}
