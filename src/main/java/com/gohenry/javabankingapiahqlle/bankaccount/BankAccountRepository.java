package com.gohenry.javabankingapiahqlle.bankaccount;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends PagingAndSortingRepository<BankAccount, Long> {
}
