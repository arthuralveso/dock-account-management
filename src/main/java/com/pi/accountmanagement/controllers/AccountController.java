package com.pi.accountmanagement.controllers;

import com.pi.accountmanagement.domains.Account;
import com.pi.accountmanagement.domains.Transaction;
import com.pi.accountmanagement.dtos.AccountDTO;
import com.pi.accountmanagement.exceptions.BadRequestException;
import com.pi.accountmanagement.exceptions.NotFoundException;
import com.pi.accountmanagement.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * REST controller para gerenciar contas
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    final AccountService accountService;


    /**
     * {@code POST  /account} : cria uma nova conta.
     * @param accountDTO dados recebidos para criacao da conta
     * @return status {@code 201 (Created)}
     * @throws BadRequestException {@code 400 (Bad Request)} se o usuário tiver uma conta de mesmo tipo
     */
    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody @Valid AccountDTO accountDTO) {
        accountService.create(accountDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    /**
     * {@code GET  /account/:accountId/balance} : Busca o saldo da conta
     * @param accountId ID da conta a ser buscada
     * @return retorna o saldo da conta
     * @throws NotFoundException {@code 404 (Not Found)} se não houve uma conta para o ID passado nos parametros
     */
    @GetMapping("/{accountId}/balance")
    public ResponseEntity<Double> getAccountBalance(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.getBalance(accountId));
    }


    /**
     * {@code GET  /account/:accountId} : Busca conta pelo ID.
     * @param accountId ID da conta a ser buscada
     * @return retorna uma conta
     * @throws NotFoundException {@code 404 (Not Found)} se não houve uma conta para o ID passado nos parametros
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }

    /**
     * {@code POST  /account/block} : Bloqueia uma conta.
     * @param accountId ID da conta a ser bloqueada
     * @return status {@code 200 (OK)}
     * @throws BadRequestException {@code 400 (Bad Request)} se a conta ja estiver bloqueada
     */
    @PatchMapping("/{accountId}/block")
    public ResponseEntity<Void> blockAccount(@PathVariable Long accountId) {
        accountService.blockAccountById(accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
