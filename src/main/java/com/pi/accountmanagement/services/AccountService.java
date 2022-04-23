package com.pi.accountmanagement.services;

import com.pi.accountmanagement.domains.Account;
import com.pi.accountmanagement.domains.Transaction;
import com.pi.accountmanagement.dtos.AccountDTO;
import com.pi.accountmanagement.exceptions.BadRequestException;
import com.pi.accountmanagement.exceptions.NotFoundException;
import com.pi.accountmanagement.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

	private AccountRepository accountRepository;
	private PeopleService peopleService;


	/**
	 * Metodo reponsável por criar uma nova conta caso o usuário não possua uma conta do mesmo tipo
	 * @param accountDTO Dados para criação da conta
	 *
	 */

	public void create(AccountDTO accountDTO) {

		Account existingAccount = accountRepository.findAccountByPeopleId(accountDTO.getPeopleId());


		if(!Objects.isNull(existingAccount) && existingAccount.getAccountType() == accountDTO.getAccountType()) {
			throw new BadRequestException("Client already have a account with this type");
		}

		Account account = new Account(accountDTO);
		account.setPeople(peopleService.getPeopleById(accountDTO.getPeopleId()));

		accountRepository.save(account);

	}


	/**
	 * Metodo que retorna uma conta pelo ID
	 * @param accountId id da conta
	 * @return retorna uma conta
	 */

	public Account getAccountById(Long accountId) {

		Optional<Account> account =  this.accountRepository.findById(accountId);

		if(!account.isPresent()) {
			throw new NotFoundException("Account not found");
		}

		return account.get();
	}

	/**
	 * Metodo que retorna o saldo de uma conta atraves do seu ID
	 * @param accountId id da conta
	 * @return retorna o saldo da conta
	 */
	public double getBalance(Long accountId) {

		Account account = this.getAccountById(accountId);


		return account.getBalance();

	}

	/**
	 * Metodo responsável por realizar o bloqueio de uma conta
	 * @param accountId id da conta
	 */

	public void blockAccountById(Long accountId) {
		Account account = this.getAccountById(accountId);


		if(!account.isActive()) {
			throw new BadRequestException("The account is already blocked");
		}

		account.setActive(false);

		accountRepository.save(account);
	}


}
