package com.example.simple.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.simple.model.Transaction;
import com.example.simple.repository.TransactionRepository;

public class TransactionRepositoryTest {

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTransaction() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setTipoTransacao(Transaction.TipoTransacao.PIX);
        transaction.setValor(BigDecimal.valueOf(1000.00));

        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction savedTransaction = transactionRepository.save(transaction);

        assertNotNull(savedTransaction);
        assertEquals(1L, savedTransaction.getId());
        assertEquals(Transaction.TipoTransacao.PIX, savedTransaction.getTipoTransacao());
        assertEquals(BigDecimal.valueOf(1000.00), savedTransaction.getValor());
        verify(transactionRepository, times(1)).save(transaction);
    }
    
    @Test
    void testFindById() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);

        when(transactionRepository.findById(1L)).thenReturn(java.util.Optional.of(transaction));

        java.util.Optional<Transaction> foundTransaction = transactionRepository.findById(1L);

        assertTrue(foundTransaction.isPresent());
        assertEquals(1L, foundTransaction.get().getId());
        verify(transactionRepository, times(1)).findById(1L);
    }
    
    @Test
    void testFindAll() {
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> foundTransactions = transactionRepository.findAll();

        assertEquals(2, foundTransactions.size());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById() {
        doNothing().when(transactionRepository).deleteById(1L);

        transactionRepository.deleteById(1L);

        verify(transactionRepository, times(1)).deleteById(1L);
    }

}
