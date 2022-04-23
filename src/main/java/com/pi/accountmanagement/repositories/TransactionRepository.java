package com.pi.accountmanagement.repositories;

import com.pi.accountmanagement.domains.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    @Query("select t from Transaction as t where ((t.account.accountId = :accountId) and (t.transactionDate between :fromDate and :toDate))")
    List<Transaction> findTransactionsByDate(
            @Param(value = "accountId") Long accountId,
            @Param(value = "fromDate") LocalDate fromDate,
            @Param(value = "toDate") LocalDate toDate
    );

}
