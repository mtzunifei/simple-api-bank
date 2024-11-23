package com.example.simple.controller;

import com.example.simple.model.Transaction;
import com.example.simple.service.ClientService;
import com.example.simple.service.TransactionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTransactions() {
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionService.getAllTransactions()).thenReturn(transactions);

        List<Transaction> result = transactionController.getAllTransactions();
        assertEquals(2, result.size());
        verify(transactionService, times(1)).getAllTransactions();
    }

    @Test
    void createTransacao() {
        Transaction transaction = new Transaction();
        when(transactionService.createTransacao(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionController.createTransacao(transaction);
        assertNotNull(result);
        verify(transactionService, times(1)).createTransacao(any(Transaction.class));
    }

    @Test
    void getTransacaoById() {
        Transaction transaction = new Transaction();
        when(transactionService.getTransacaoById(1L)).thenReturn(transaction);

        Transaction result = transactionController.getTransacaoById(1L);
        assertNotNull(result);
        verify(transactionService, times(1)).getTransacaoById(1L);
    }

    @Test
    void updateTransacao() {
        Transaction transaction = new Transaction();
        when(transactionService.updateTransacao(eq(1L), any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionController.updateTransacao(1L, transaction);
        assertNotNull(result);
        verify(transactionService, times(1)).updateTransacao(eq(1L), any(Transaction.class));
    }

}
