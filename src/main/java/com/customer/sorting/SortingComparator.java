package com.customer.sorting;

import java.util.Comparator;

import com.customer.CustomerResponse.CustomerResponse;

public class SortingComparator implements Comparator<CustomerResponse> {
	@Override
	public int compare(CustomerResponse o1, CustomerResponse o2) {
		// TODO Auto-generated method stub
		if (o1.getAddress().getCountry().equalsIgnoreCase("india")
				&& o2.getAddress().getCountry().equalsIgnoreCase("india"))
			return 0;
		else if (!o1.getAddress().getCountry().equalsIgnoreCase("india")
				&& o2.getAddress().getCountry().equalsIgnoreCase("india"))
			return -1;
		else if (o1.getAddress().getCountry().equalsIgnoreCase("india")
				&& !o2.getAddress().getCountry().equalsIgnoreCase("india"))
			return 1;
		else if (!o1.getAddress().getCountry().equalsIgnoreCase("india")
				&& !o2.getAddress().getCountry().equalsIgnoreCase("india"))
			return 0;
		else
			return 0;
}

}
