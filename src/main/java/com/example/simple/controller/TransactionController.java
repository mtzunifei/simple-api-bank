package com.example.simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.simple.model.Transaction;
import com.example.simple.service.ClientService;
import com.example.simple.service.TransactionService;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    ClientService clientService;

    @GetMapping("/transaction")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @PostMapping("/transaction")
    public Transaction createTransacao(@Valid @RequestBody Transaction transaction) {	
        return transactionService.createTransacao(transaction);
	
    }

    @GetMapping("/transaction/{id}")
    public Transaction getTransacaoById(@PathVariable(value = "id") Long transacaoId) {
        return transactionService.getTransacaoById(transacaoId);
    }

    @PutMapping("/transaction/{id}")
    public Transaction updateTransacao(@PathVariable(value = "id") Long transacaoId,
                                           @Valid @RequestBody Transaction transacaoDetails) {
        return transactionService.updateTransacao(transacaoId, transacaoDetails);
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<?> deleteTransacao(@PathVariable(value = "id") Long transacaoId) {
        return transactionService.deleteTransacao(transacaoId);
    }
}
