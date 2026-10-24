package com.learning.accounts.mapper;

import com.learning.accounts.dto.CustomerDto;
import com.learning.accounts.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapCustomerDto(Customer customer, CustomerDto customerDto) {
        // Implementation for mapping Customer to CustomerDto
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapCustomer(CustomerDto customerDto, Customer customer) {
        // Implementation for mapping CustomerDto to Customer
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
