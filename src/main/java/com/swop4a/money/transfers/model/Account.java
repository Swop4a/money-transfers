package com.swop4a.money.transfers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author swop4a
 * @since 28/04/2019 12:57
 */
@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "owner")
@ToString(exclude = "owner")
public class Account {

	@Id
	private BigInteger id;
	private String title;
	private BigDecimal balance;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "owner", nullable = false)
	private User owner;
}
