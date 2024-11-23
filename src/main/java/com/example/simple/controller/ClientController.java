package com.example.simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.simple.model.Client;
import com.example.simple.service.ClientService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/client")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping("/client")
    public Client createTransacao(@Valid @RequestBody Client client) {
        return clientService.createClient(client);
    }

    @GetMapping("/client/{id}")
    public Client getClientById(@PathVariable(value = "id") Long clientId) {
        return clientService.getClientById(clientId);
    }

    @PutMapping("/client/{id}")
    public Client updateClient(@PathVariable(value = "id") Long clientId,
                                           @Valid @RequestBody Client clientBody) {
        return clientService.updateClient(clientId, clientBody);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long clientId) {
        return clientService.deleteClient(clientId);
    }
}
