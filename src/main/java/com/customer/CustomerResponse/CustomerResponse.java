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
	private String status;

	public void setStatus(Date joiningDate, Date expiryDate) {
		Calendar c = Calendar.getInstance();
		Date currentDate = c.getTime();

		c.setTime(joiningDate);
		c.add(Calendar.YEAR, 2);
		Date experienceDate = c.getTime();

		if ((currentDate.after(expiryDate)))
			status = "Expired";
		else if (currentDate.after(experienceDate))
			status = "Experienced";
		else if (currentDate.before(experienceDate))
			status = "New";
		else
			status = "Invalid Status";
	}

	public String getStatus() {
		return status;
	}

}
