package com.gohenry.javabankingapiahqlle.bankaccount;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @GetMapping("/bank-accounts/{id}")
    public BankAccount findById(@PathVariable long id) {
        return bankAccountService.findByIdOrElseThrow(id);
    }

    @PostMapping("/customers/{customerId}/bank-accounts")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new bank account")
    @ApiResponse(responseCode = "201", description = "Bank Account created.")
    public BankAccount newBankAccount(@PathVariable long customerId, @RequestParam long amount) {
        return bankAccountService.newBankAccount(customerId, amount);
    }

    @PostMapping("/bank-accounts/{id}/transfer")
    public BankAccount transfer(@PathVariable long id, @RequestParam long inBankAccountId, @RequestParam long amount) {
        return bankAccountService.transfer(id, inBankAccountId, amount);
    }

    @PostMapping("/bank-accounts/{id}/add-money")
    public BankAccount addMoney(@PathVariable long id, @RequestParam long amount) {
        return bankAccountService.addMoney(id, amount);
    }
}
