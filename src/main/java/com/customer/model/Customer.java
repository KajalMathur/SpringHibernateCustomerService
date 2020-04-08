package com.customer.model;

import com.customer.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;
import java.util.Date;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "name.required")
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @CreationTimestamp
    private Date joiningDate;

    private Date expiryDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;

    public Date setExpiryDate(int expdateCount) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, expdateCount);
        Date newDate = c.getTime();
        expiryDate = newDate;
        return expiryDate;
    }

    public String setPassword(String password) {
        this.password = password;
        return password;
    }
}
