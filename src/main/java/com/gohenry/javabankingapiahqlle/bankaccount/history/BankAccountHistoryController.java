package com.gohenry.javabankingapiahqlle.bankaccount.history;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BankAccountHistoryController {

    private final BankAccountHistoryService bankAccountHistoryService;

    @GetMapping("/bank-accounts/{bankAccountId}/history")
    public Page<BankAccountHistory> history(@PathVariable long bankAccountId, Pageable pageable) {
        return bankAccountHistoryService.findByBankAccountIdOrderByCreatedDateDesc(bankAccountId, pageable);
    }
}
