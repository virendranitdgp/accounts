package com.learning.accounts.service;

import com.learning.accounts.dto.CustomerDto;

public interface IAccountService {


    void  createAccount(CustomerDto customerDto);

    CustomerDto fetchAccountDetails(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
