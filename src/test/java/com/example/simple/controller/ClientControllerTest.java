package com.example.simple.controller;

import com.example.simple.model.Client;
import com.example.simple.service.ClientService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllClients() {
        Client client1 = new Client();
        Client client2 = new Client();
        List<Client> clients = Arrays.asList(client1, client2);

        when(clientService.getAllClients()).thenReturn(clients);

        List<Client> result = clientController.getAllClients();
        assertEquals(2, result.size());
        verify(clientService, times(1)).getAllClients();
    }

    @Test
    void createClient() {
        Client client = new Client();
        when(clientService.createClient(any(Client.class))).thenReturn(client);

        Client result = clientController.createTransacao(client);
        assertNotNull(result);
        verify(clientService, times(1)).createClient(any(Client.class));
    }

    @Test
    void getClientById() {
        Client client = new Client();
        when(clientService.getClientById(1L)).thenReturn(client);

        Client result = clientController.getClientById(1L);
        assertNotNull(result);
        verify(clientService, times(1)).getClientById(1L);
    }

    @Test
    void updateClient() {
        Client client = new Client();
        when(clientService.updateClient(eq(1L), any(Client.class))).thenReturn(client);

        Client result = clientController.updateClient(1L, client);
        assertNotNull(result);
        verify(clientService, times(1)).updateClient(eq(1L), any(Client.class));
    }

}
