package com.learning.accounts.controller;

import com.learning.accounts.dto.CustomerDto;
import com.learning.accounts.dto.ResponseDto;
import com.learning.accounts.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountService iAccountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody @Valid CustomerDto customerDto) {
        iAccountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("Account created successfully", HttpStatus.valueOf(201).toString()));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Pattern(regexp = "^[0-9]{10}$") String mobileNumber) {
        CustomerDto customerDto = iAccountService.fetchAccountDetails(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody @Valid CustomerDto customerDto) {
       boolean isUpdate= iAccountService.updateAccount(customerDto);
       if (isUpdate) {
           return ResponseEntity
                   .status(HttpStatus.OK)
                   .body(new ResponseDto("Account updated successfully", HttpStatus.valueOf(200).toString()));
       }else {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR   )
                .body(new ResponseDto("Failed to update account", HttpStatus.valueOf(500).toString()));
       }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam  @Pattern(regexp = "^[0-9]{10}$") String mobileNumber) {
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("Account deleted successfully", HttpStatus.valueOf(200).toString()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto("Failed to delete account", HttpStatus.valueOf(500).toString()));
        }
    }

}
