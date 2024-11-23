package com.example.simple.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.simple.model.Client;

import java.math.BigDecimal;
import java.util.Date;

public class ClientTest {

    @Test
    void testGettersAndSetters() {
        Client client = new Client();
        Date now = new Date();

        client.setId(1L);
        client.setTipoChave("CPF");
        client.setChavePix("12345678900");
        client.setAgencia("1234");
        client.setContaCorrente("56789-0");
        client.setSaldo(BigDecimal.valueOf(1000.00));
        client.setCreatedAt(now);
        client.setUpdatedAt(now);

        assertEquals(1L, client.getId());
        assertEquals("CPF", client.getTipoChave());
        assertEquals("12345678900", client.getChavePix());
        assertEquals("1234", client.getAgencia());
        assertEquals("56789-0", client.getContaCorrente());
        assertEquals(BigDecimal.valueOf(1000.00), client.getSaldo());
        assertEquals(now, client.getCreatedAt());
        assertEquals(now, client.getUpdatedAt());
    }
    
    @Test
    void testClientCreation() {
        Client client = new Client();
        Date now = new Date();

        client.setId(1L);
        client.setTipoChave("CPF");
        client.setChavePix("12345678900");
        client.setAgencia("1234");
        client.setContaCorrente("56789-0");
        client.setSaldo(BigDecimal.valueOf(1000.00));
        client.setCreatedAt(now);
        client.setUpdatedAt(now);

        assertNotNull(client);
        assertEquals(1L, client.getId());
        assertEquals("CPF", client.getTipoChave());
        assertEquals("12345678900", client.getChavePix());
        assertEquals("1234", client.getAgencia());
        assertEquals("56789-0", client.getContaCorrente());
        assertEquals(BigDecimal.valueOf(1000.00), client.getSaldo());
        assertEquals(now, client.getCreatedAt());
        assertEquals(now, client.getUpdatedAt());
    }
    
}
