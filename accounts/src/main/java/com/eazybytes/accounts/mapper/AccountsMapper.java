package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto mapAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        // Implementation for mapping Accounts to AccountsDto
         accountsDto.setAccountNumber(accounts.getAccountNumber());
         accountsDto.setAccountType(accounts.getAccountType());
         accountsDto.setBranchAddress(accounts.getBranchAddress());
         return accountsDto;
    }

    public static Accounts mapAccounts(AccountsDto accountsDto, Accounts  accounts  ) {
        // Implementation for mapping AccountsDto to Accounts
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
