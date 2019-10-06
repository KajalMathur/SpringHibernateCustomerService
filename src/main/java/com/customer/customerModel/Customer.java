package com.customer.customerModel;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.customer.address.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@Table(name = "Customer")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "joiningDate", "expiryDate" }, allowGetters = true)
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date joiningDate;

	@Setter(AccessLevel.NONE)
	private Date expiryDate;


	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JoinColumn(name = "chinapanna_id")
	private Address address;

	public void setExpiryDate(int expdateCount) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, expdateCount);
		Date newDate = c.getTime();
		expiryDate = newDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}
}
