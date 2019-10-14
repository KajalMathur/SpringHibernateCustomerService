package com.customer.CustomerResponse;

import java.util.Calendar;
import java.util.Date;

import com.customer.address.Address;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerResponse {

	private int id;
	private String firstName;
	private String lastName;
	private Address address;
	private Date joiningDate;
	private Date expiryDate;
	Status status;

	private enum Status {
		New, Experienced, Expired, Invalid;
	}

	public Status setStatus(Date joiningDate, Date expiryDate) {

		Calendar c = Calendar.getInstance();
		Date currentDate = c.getTime();

		c.setTime(joiningDate);
		c.add(Calendar.YEAR, 2);
		Date experienceDate = c.getTime();

		if ((currentDate.after(expiryDate)))
			status = Status.Expired;
		else if (currentDate.after(experienceDate))
			status = Status.Experienced;
		else if (currentDate.before(experienceDate))
			status = Status.New;
		else
			status = Status.Invalid;
		return status;
	}

	public Status getStatus() {
		return status;
	}

}
