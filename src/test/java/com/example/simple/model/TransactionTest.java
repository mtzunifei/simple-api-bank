package com.example.simple.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.simple.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionTest {

    @Test
    void testGettersAndSetters() {
        Transaction transaction = new Transaction();
        Date now = new Date();

        transaction.setId(1L);
        transaction.setClientId(2L);
        transaction.setTipoTransacao(Transaction.TipoTransacao.PIX);
        transaction.setTipoChave("CPF");
        transaction.setChavePix("12345678900");
        transaction.setNumCelular("987654321");
        transaction.setTipoConta("Corrente");
        transaction.setAgencia("1234");
        transaction.setContaCorrente("56789-0");
        transaction.setValor(BigDecimal.valueOf(1000.00));
        transaction.setCreatedAt(now);
        transaction.setUpdatedAt(now);

        assertEquals(1L, transaction.getId());
        assertEquals(2L, transaction.getClientId());
        assertEquals(Transaction.TipoTransacao.PIX, transaction.getTipoTransacao());
        assertEquals("CPF", transaction.getTipoChave());
        assertEquals("12345678900", transaction.getChavePix());
        assertEquals("987654321", transaction.getNumCelular());
        assertEquals("Corrente", transaction.getTipoConta());
        assertEquals("1234", transaction.getAgencia());
        assertEquals("56789-0", transaction.getContaCorrente());
        assertEquals(BigDecimal.valueOf(1000.00), transaction.getValor());
        assertEquals(now, transaction.getCreatedAt());
        assertEquals(now, transaction.getUpdatedAt());
    }
    
    @Test
    void testTransactionCreation() {
        Transaction transaction = new Transaction();
        Date now = new Date();

        transaction.setId(1L);
        transaction.setClientId(2L);
        transaction.setTipoTransacao(Transaction.TipoTransacao.PIX);
        transaction.setTipoChave("CPF");
        transaction.setChavePix("12345678900");
        transaction.setNumCelular("987654321");
        transaction.setTipoConta("Corrente");
        transaction.setAgencia("1234");
        transaction.setContaCorrente("56789-0");
        transaction.setValor(BigDecimal.valueOf(1000.00));
        transaction.setCreatedAt(now);
        transaction.setUpdatedAt(now);

        assertNotNull(transaction);
        assertEquals(1L, transaction.getId());
        assertEquals(2L, transaction.getClientId());
        assertEquals(Transaction.TipoTransacao.PIX, transaction.getTipoTransacao());
        assertEquals("CPF", transaction.getTipoChave());
        assertEquals("12345678900", transaction.getChavePix());
        assertEquals("987654321", transaction.getNumCelular());
        assertEquals("Corrente", transaction.getTipoConta());
        assertEquals("1234", transaction.getAgencia());
        assertEquals("56789-0", transaction.getContaCorrente());
        assertEquals(BigDecimal.valueOf(1000.00), transaction.getValor());
        assertEquals(now, transaction.getCreatedAt());
        assertEquals(now, transaction.getUpdatedAt());
    }
    
    @Test
    void testTransactionUpdate() {
        Transaction transaction = new Transaction();
        Date now = new Date();

        transaction.setId(1L);
        transaction.setClientId(2L);
        transaction.setTipoTransacao(Transaction.TipoTransacao.PIX);
        transaction.setTipoChave("CPF");
        transaction.setChavePix("12345678900");
        transaction.setNumCelular("987654321");
        transaction.setTipoConta("Corrente");
        transaction.setAgencia("1234");
        transaction.setContaCorrente("56789-0");
        transaction.setValor(BigDecimal.valueOf(1000.00));
        transaction.setCreatedAt(now);
        transaction.setUpdatedAt(now);

        transaction.setTipoTransacao(Transaction.TipoTransacao.DEPOSITO);
        transaction.setValor(BigDecimal.valueOf(2000.00));

        assertEquals(Transaction.TipoTransacao.DEPOSITO, transaction.getTipoTransacao());
        assertEquals(BigDecimal.valueOf(2000.00), transaction.getValor());
    }
}