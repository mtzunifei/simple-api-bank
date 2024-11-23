package com.example.simple.service;

import com.example.simple.exception.ResourceNotFoundException;
import com.example.simple.model.Client;
import com.example.simple.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllClients() {
        Client client1 = new Client();
        Client client2 = new Client();
        List<Client> clients = Arrays.asList(client1, client2);

        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientService.getAllClients();
        assertEquals(2, result.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void getClientById() {
        Client client = new Client();
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client result = clientService.getClientById(1L);
        assertNotNull(result);
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void getClientByIdNotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientService.getClientById(1L));
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void createClient() {
        Client client = new Client();
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client result = clientService.createClient(client);
        assertNotNull(result);
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void updateClient() {
        Client client = new Client();
        Client updatedClient = new Client();
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(updatedClient);

        Client result = clientService.updateClient(1L, updatedClient);
        assertNotNull(result);
        verify(clientRepository, times(1)).findById(1L);
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void updateClientNotFound() {
        Client updatedClient = new Client();
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientService.updateClient(1L, updatedClient));
        verify(clientRepository, times(1)).findById(1L);
        verify(clientRepository, never()).save(any(Client.class));
    }

    @Test
    void deleteClient() {
        Client client = new Client();
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        ResponseEntity<?> response = clientService.deleteClient(1L);
        assertNotNull(response);
        assertEquals(ResponseEntity.ok().build(), response);
        verify(clientRepository, times(1)).findById(1L);
        verify(clientRepository, times(1)).delete(client);
    }

    @Test
    void deleteClientNotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientService.deleteClient(1L));
        verify(clientRepository, times(1)).findById(1L);
        verify(clientRepository, never()).delete(any(Client.class));
    }
}
