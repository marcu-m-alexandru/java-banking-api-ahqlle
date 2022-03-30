package com.gohenry.javabankingapiahqlle.bankaccount.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountHistoryRepository extends PagingAndSortingRepository<BankAccountHistory, Long> {

    @Query("SELECT h FROM BankAccountHistory h " +
            "WHERE h.outBankAccountId = ?1 OR h.inBankAccountId = ?1 " +
            "ORDER BY h.createdDate DESC")
    Page<BankAccountHistory> findByBankAccountIdOrderByCreatedDateDesc(long bankAccountId, Pageable pageable);
}
