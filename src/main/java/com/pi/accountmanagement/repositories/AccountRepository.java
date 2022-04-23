package com.pi.accountmanagement.repositories;

import com.pi.accountmanagement.domains.Account;
import com.pi.accountmanagement.domains.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

    @Query("select a from Account as a where (a.people.peopleId = :clientId)")
    Account findAccountByPeopleId(@Param(value = "clientId") Long clientId);

}
