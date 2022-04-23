package com.pi.accountmanagement.controllers;

import com.pi.accountmanagement.domains.Transaction;
import com.pi.accountmanagement.dtos.TransactionDTO;
import com.pi.accountmanagement.exceptions.NotFoundException;
import com.pi.accountmanagement.services.TransactionService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * REST controller para gerenciamento de contas
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private TransactionService transactionService;


    /**
     * {@code POST  /deposit} : cria transacao do tipo deposito.
     *
     * @param transactionDTO dados recebidos para criacao da transacao
     * @return status {@code 201 (Created)}
     * @throws com.pi.accountmanagement.exceptions.NotFoundException {@code 404 (Not Found)} se não houver conta para o id passado nos parametros
     */
    @PostMapping("/deposit")
    public ResponseEntity<Void> createDeposit(@RequestBody @Valid TransactionDTO transactionDTO) {
        transactionService.createDepositTransaction(transactionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * {@code POST  /deposit} : cria transacao do tipo saque.
     *
     * @param transactionDTO dados recebidos para criacao da transacao
     * @return status {@code 201 (Created)}
     * @throws com.pi.accountmanagement.exceptions.NotFoundException   {@code 404 (Not Found)} se não houver conta para o id passado nos parametros
     * @throws com.pi.accountmanagement.exceptions.BadRequestException {@code 400 (Bad Request)} se não houver saldo suficiente para realizar o saque
     * @throws com.pi.accountmanagement.exceptions.BadRequestException {@code 400 (Bad Request)} se a conta estiver bloqueada
     */
    @PostMapping("/withdraw")
    public ResponseEntity<Void> createWithdraw(@RequestBody @Valid TransactionDTO transactionDTO) {
        transactionService.createWithdrawTransaction(transactionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    /**
     * {@code GET  /account/:accountId/transactions} : Busca todas as transacoes de uma conta
     * @param accountId ID da conta
     * @return retorna uma lista com todas as transacoes de uma conta
     * @throws NotFoundException {@code 404 (Not Found)} se não houve uma conta para o ID passado nos parametros
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getTransactionsByAccountId(accountId));
    }

    /**
     * Retorna as transacoes por periodo
     * @param accountId Id da conta
     * @param fromDate data de inicio das transacoes
     * @param toDate data final das transacoes
     * @return Uma lista com as trasacoes realizadas no intervalo das datas passadas pelos parametros
     */

    @GetMapping(params = {"fromDate", "toDate"}, path = "/{accountId}/time-course")
    public ResponseEntity<List<Transaction>> getTransactionsByDate(
            @PathVariable Long accountId,
            @RequestParam(value = "fromDate") String fromDate,
            @RequestParam(value = "toDate") String toDate) {


        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate from = LocalDate.parse(fromDate, parser);
        LocalDate to = LocalDate.parse(toDate, parser);


        return ResponseEntity.ok(transactionService.getTransactionsByDate(accountId, from, to));
    }


}
