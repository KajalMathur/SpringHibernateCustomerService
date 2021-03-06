package com.customer.CustomerResponse;

import com.customer.address.Address;
import lombok.Builder;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
@Builder
public class CustomerResponse {

    Status status;
    private int id;
    private String firstName;
    private String lastName;
    private Address address;
    private Date joiningDate;
    private Date expiryDate;

    public Status setStatus(Date joiningDate, Date expiryDate) {

        Calendar c = Calendar.getInstance();
        Date currentDate = c.getTime();

        c.setTime(joiningDate);
        c.add(Calendar.YEAR, 2);
        Date experienceDate = c.getTime();

        if ((currentDate.after(expiryDate)))
            status = Status.EXPIRED;
        else if (currentDate.after(experienceDate))
            status = Status.EXPERIENCED;
        else if (currentDate.before(experienceDate))
            status = Status.NEW;
        else
            status = Status.INVALID;
        return status;
    }

    public enum Status {
        NEW, EXPERIENCED, EXPIRED, INVALID;
    }
}
