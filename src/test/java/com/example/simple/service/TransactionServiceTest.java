package com.example.simple.service;

import com.example.simple.exception.ResourceNotFoundException;
import com.example.simple.model.Client;
import com.example.simple.model.Transaction;
import com.example.simple.repository.ClientRepository;
import com.example.simple.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTransactions() {
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> result = transactionService.getAllTransactions();
        assertEquals(2, result.size());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void createTransacaoPixOrDeposito() {
        Client client = new Client();
        client.setId(1L);
        client.setSaldo(BigDecimal.ZERO);

        Transaction transaction = new Transaction();
        transaction.setClientId(1L);
        transaction.setTipoTransacao(Transaction.TipoTransacao.PIX);
        transaction.setValor(BigDecimal.TEN);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.createTransacao(transaction);
        assertNotNull(result);
        assertEquals(BigDecimal.TEN, client.getSaldo());
        verify(clientRepository, times(1)).save(any(Client.class));
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void createTransacaoRecargaCelularOrSaque() {
        Client client = new Client();
        client.setId(1L);
        client.setSaldo(BigDecimal.TEN);

        Transaction transaction = new Transaction();
        transaction.setClientId(1L);
        transaction.setTipoTransacao(Transaction.TipoTransacao.RECARGA_CELULAR);
        transaction.setValor(BigDecimal.ONE);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.createTransacao(transaction);
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(9), client.getSaldo());
        verify(clientRepository, times(1)).save(any(Client.class));
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void createTransacaoInsufficientFunds() {
        Client client = new Client();
        client.setId(1L);
        client.setSaldo(BigDecimal.ONE);

        Transaction transaction = new Transaction();
        transaction.setClientId(1L);
        transaction.setTipoTransacao(Transaction.TipoTransacao.SAQUE);
        transaction.setValor(BigDecimal.TEN);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        assertThrows(ResourceNotFoundException.class, () -> transactionService.createTransacao(transaction));
        verify(clientRepository, times(1)).findById(1L);
        verify(transactionRepository, never()).save(any(Transaction.class));
    }

    @Test
    void getTransacaoById() {
        Transaction transaction = new Transaction();
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        Transaction result = transactionService.getTransacaoById(1L);
        assertNotNull(result);
        verify(transactionRepository, times(1)).findById(1L);
    }

    @Test
    void getTransacaoByIdNotFound() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> transactionService.getTransacaoById(1L));
        verify(transactionRepository, times(1)).findById(1L);
    }

    @Test
    void updateTransacao() {
        Transaction transaction = new Transaction();
        Transaction updatedTransaction = new Transaction();
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(updatedTransaction);

        Transaction result = transactionService.updateTransacao(1L, updatedTransaction);
        assertNotNull(result);
        verify(transactionRepository, times(1)).findById(1L);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void updateTransacaoNotFound() {
        Transaction updatedTransaction = new Transaction();
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> transactionService.updateTransacao(1L, updatedTransaction));
        verify(transactionRepository, times(1)).findById(1L);
        verify(transactionRepository, never()).save(any(Transaction.class));
    }

    @Test
    void deleteTransacao() {
        Transaction transaction = new Transaction();
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        ResponseEntity<?> response = transactionService.deleteTransacao(1L);
        assertNotNull(response);
        assertEquals(ResponseEntity.ok().build(), response);
        verify(transactionRepository, times(1)).findById(1L);
        verify(transactionRepository, times(1)).delete(transaction);
    }

    @Test
    void deleteTransacaoNotFound() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> transactionService.deleteTransacao(1L));
        verify(transactionRepository, times(1)).findById(1L);
        verify(transactionRepository, never()).delete(any(Transaction.class));
    }
}
