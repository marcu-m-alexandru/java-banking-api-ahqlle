package com.gohenry.javabankingapiahqlle.bankaccount;

import com.gohenry.javabankingapiahqlle.bankaccount.history.BankAccountHistoryService;
import com.gohenry.javabankingapiahqlle.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountHistoryService bankAccountHistoryService;

    // todo handle throw
    public BankAccount findByIdOrElseThrow(long id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new NotFoundException("Could not find account with id = " + id));
    }

    public BankAccount newBankAccount(long customerId, long amount) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setCustomerId(customerId);
        bankAccount.setAmount(amount);
        return bankAccountRepository.save(bankAccount);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public BankAccount transfer(long outBankAccountId, long inBankAccountId, long amount) {
        if (outBankAccountId == inBankAccountId) {
            throw new IllegalArgumentException("Can not make transfer to the same account.");
        }
        BankAccount outBankAccount = findByIdOrElseThrow(outBankAccountId);
        BankAccount inBankAccount = findByIdOrElseThrow(inBankAccountId);
        return transfer(outBankAccount, inBankAccount, amount);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    // todo
    // Note: transfer method kind of uses the same logic.
    public BankAccount addMoney(long id, long amount) {
        BankAccount bankAccount = findByIdOrElseThrow(id);
        // todo check negative amount
        long initialAmount = bankAccount.getAmount();
        bankAccount.setAmount(initialAmount + amount);
        bankAccountHistoryService.add(bankAccount, bankAccount, amount); // todo will show up twice
        return bankAccountRepository.save(bankAccount);
    }

    // Note: addMoney method kind of uses the same logic.
    private BankAccount transfer(BankAccount outBankAccount, BankAccount inBankAccount, long amount) {
        // todo check negative amount
        long outBankAccountAmount = outBankAccount.getAmount();
        if (outBankAccountAmount < amount) {
            throw new IllegalArgumentException("Not enough money; required = " + amount + ", existing = " + outBankAccountAmount);
        }

        // todo check overflow
        inBankAccount.setAmount(inBankAccount.getAmount() + amount);
        outBankAccount.setAmount(outBankAccountAmount - amount);

        bankAccountHistoryService.add(outBankAccount, inBankAccount, amount);

        bankAccountRepository.save(inBankAccount);
        return bankAccountRepository.save(outBankAccount);
    }
}
