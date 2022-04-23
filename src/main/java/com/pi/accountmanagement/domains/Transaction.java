package com.pi.accountmanagement.domains;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pi.accountmanagement.dtos.TransactionDTO;
import com.pi.accountmanagement.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "transactions")
public class Transaction implements Serializable{
	
	 private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long transactionId;

	    @Column(nullable = false)
	    private double value;

	    @Column(nullable = false)
	    private LocalDate transactionDate;

		@Column(nullable = false)
		private TransactionType transactionType;

		@ManyToOne
		@JoinColumn(name = "account_id", nullable = false)
		@JsonBackReference
		private Account account;
	

		public Transaction(TransactionDTO dto) {
			this.value = dto.getValue();
			this.transactionDate = LocalDate.now();
		}
}
