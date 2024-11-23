package com.example.simple.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.simple.model.Client;
import com.example.simple.repository.ClientRepository;

public class ClientRepositoryTest {

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveClient() {
        Client client = new Client();
        client.setId(1L);
        client.setTipoChave("CPF");
        client.setChavePix("12345678900");

        when(clientRepository.save(client)).thenReturn(client);

        Client savedClient = clientRepository.save(client);

        assertNotNull(savedClient);
        assertEquals(1L, savedClient.getId());
        assertEquals("CPF", savedClient.getTipoChave());
        assertEquals("12345678900", savedClient.getChavePix());
        verify(clientRepository, times(1)).save(client);
    }
    
    @Test
    void testFindById() {
        Client client = new Client();
        client.setId(1L);

        when(clientRepository.findById(1L)).thenReturn(java.util.Optional.of(client));

        java.util.Optional<Client> foundClient = clientRepository.findById(1L);

        assertTrue(foundClient.isPresent());
        assertEquals(1L, foundClient.get().getId());
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        Client client1 = new Client();
        Client client2 = new Client();
        List<Client> clients = Arrays.asList(client1, client2);

        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> foundClients = clientRepository.findAll();

        assertEquals(2, foundClients.size());
        verify(clientRepository, times(1)).findAll();
    }
    
    @Test
    void testDeleteById() {
        doNothing().when(clientRepository).deleteById(1L);

        clientRepository.deleteById(1L);

        verify(clientRepository, times(1)).deleteById(1L);
    }

}

