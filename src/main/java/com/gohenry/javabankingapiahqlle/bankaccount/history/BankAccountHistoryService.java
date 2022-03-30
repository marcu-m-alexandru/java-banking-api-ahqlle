package com.gohenry.javabankingapiahqlle.bankaccount.history;

import com.gohenry.javabankingapiahqlle.bankaccount.BankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class BankAccountHistoryService {

    private final BankAccountHistoryRepository bankAccountHistoryRepository;

    public Page<BankAccountHistory> findByBankAccountIdOrderByCreatedDateDesc(
            @PathVariable long bankAccountId, Pageable pageable) {
        // todo check if bankAccountId exists
        return bankAccountHistoryRepository.findByBankAccountIdOrderByCreatedDateDesc(bankAccountId, pageable);
    }

    public BankAccountHistory add(BankAccount outBankAccount, BankAccount inBankAccount, long amount) {
        BankAccountHistory bankAccountHistory = new BankAccountHistory();
        bankAccountHistory.setOutBankAccountId(outBankAccount.getId());
        bankAccountHistory.setInBankAccountId(inBankAccount.getId());
        bankAccountHistory.setAmount(amount);

        return bankAccountHistoryRepository.save(bankAccountHistory);
    }
}
