package com.pi.accountmanagement.services;

import com.pi.accountmanagement.domains.Account;
import com.pi.accountmanagement.domains.Transaction;
import com.pi.accountmanagement.dtos.TransactionDTO;
import com.pi.accountmanagement.enums.TransactionType;
import com.pi.accountmanagement.exceptions.BadRequestException;
import com.pi.accountmanagement.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private AccountService accountService;

    /**
     * Metodo responsável por criar uma transacao do tipo deposito em uma conta
     *
     * @param transactionDTO Objeto com os dados da transacao
     */
    public void createDepositTransaction(TransactionDTO transactionDTO) {
        Account account = this.accountService.getAccountById(transactionDTO.getAccountId());

        if (!account.isActive()) {
            throw new BadRequestException("This account is blocked");
        }

        double amount = account.getBalance();

        Transaction transaction = new Transaction(transactionDTO);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        account.setBalance(amount + transaction.getValue());

        transaction.setAccount(account);

        transactionRepository.save(transaction);

    }

    /**
     * Metodo responsável por criar uma transacao do tipo saque em uma conta
     *
     * @param transactionDTO Objeto com os dados da transacao
     */
    public void createWithdrawTransaction(TransactionDTO transactionDTO) {
        Account account = this.accountService.getAccountById(transactionDTO.getAccountId());

        double amount = account.getBalance();

        if (!account.isActive()) {
            throw new BadRequestException("This account is blocked");
        }

        if (transactionDTO.getValue() > account.getDailyWithdrawalLimit()) {
            throw new BadRequestException("The amount requested is greater than the your withdraw daily limit");
        }

        if ((amount == 0) || (amount - transactionDTO.getValue() < 0)) {
            throw new BadRequestException("Insufficient funds");
        }

        Transaction transaction = new Transaction(transactionDTO);
        transaction.setTransactionType(TransactionType.WITHDRAW);
        account.setBalance(amount - transaction.getValue());

        transaction.setAccount(account);

        transactionRepository.save(transaction);

    }

    /**
     * Metodo responsável por retornar todas as transacoes de uma conta
     * @param accountId id da conta
     * @return retorna a lista de transacoes da conta
     */
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        Account account = accountService.getAccountById(accountId);

        return account.getTransactions();
    }


    /**
     * Metodo responsável por buscar transacoes por periodo
     * @param accountId Id da conta
     * @param fromDate data de inicio das transacoes
     * @param toDate data final das transacoes
     * @return Uma lista com as trasacoes realizadas no intervalo das datas passadas pelos parametros
     */
    public List<Transaction> getTransactionsByDate(Long accountId, LocalDate fromDate, LocalDate toDate) {
        return transactionRepository.findTransactionsByDate(accountId, fromDate, toDate);
    }


}
