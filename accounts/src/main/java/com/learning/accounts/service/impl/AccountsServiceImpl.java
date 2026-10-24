package com.learning.accounts.service.impl;

import com.learning.accounts.contstants.AccountsConstants;
import com.learning.accounts.dto.AccountsDto;
import com.learning.accounts.dto.CustomerDto;
import com.learning.accounts.entity.Accounts;
import com.learning.accounts.entity.Customer;
import com.learning.accounts.exception.CustomerAlreadyExistException;
import com.learning.accounts.exception.ResourceNotFoundException;
import com.learning.accounts.mapper.AccountsMapper;
import com.learning.accounts.mapper.CustomerMapper;
import com.learning.accounts.repository.AccountsRepository;
import com.learning.accounts.repository.CustomerRepositoty;
import com.learning.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountService {
    private AccountsRepository accountsRepostory;
    private CustomerRepositoty customerRepositoty;

    @Override
    public void createAccount(CustomerDto customerDto) {
        // Implementation for creating an account

        Customer customer = CustomerMapper.mapCustomer(customerDto, new Customer());
        Optional<Customer> existingCustomer = customerRepositoty.findByMobileNumber(customer.getMobileNumber());
        if (existingCustomer.isPresent()) {
            throw new CustomerAlreadyExistException("Customer already exists for the given mobile number" + customer.getMobileNumber());
        }
        Customer saveCustomer = customerRepositoty.save(customer);
        accountsRepostory.save(createNewAccount(saveCustomer));
    }


    private Accounts createNewAccount(Customer customer) {

        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccount = 10000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccount);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {
        Customer customer = customerRepositoty.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepostory.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapAccountsDto(accounts, new AccountsDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            // Implementation for updating account details
            Accounts accounts=accountsRepostory.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "accountNumber", accountsDto.getAccountNumber().toString())
            );
            Accounts updatedAccounts = AccountsMapper.mapAccounts(accountsDto, accounts);
            accountsRepostory.save(updatedAccounts);
            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepositoty.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "customerId", customerId.toString())
            );
            CustomerMapper.mapCustomer(customerDto, customer);
            customerRepositoty.save(customer);
            isUpdated = true;
            }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepositoty.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepostory.deleteByCustomerId(customer.getCustomerId());
        customerRepositoty.delete(customer);
        return true;
    }
}
