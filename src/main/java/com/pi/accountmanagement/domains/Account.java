package com.pi.accountmanagement.domains;

import com.pi.accountmanagement.dtos.AccountDTO;
import com.pi.accountmanagement.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "accounts")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    
    @Column(nullable = false)
    private double balance;

    @Column(nullable = false)
    private double dailyWithdrawalLimit;

    @Column(nullable = false)
    private boolean isActive;
    
    @Column(nullable = false)
    private AccountType accountType;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    @ManyToOne()
    @JoinColumn(name = "people_id", nullable = false)
    private People people;
    
    public Account(AccountDTO dto) {
        this.balance = dto.getBalance();
        this.dailyWithdrawalLimit = dto.getDailyWithdrawalLimit();
        this.accountType = dto.getAccountType();
        this.isActive = dto.isActive();
        this.createdAt = LocalDateTime.now();
    }

}
