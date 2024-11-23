package com.example.simple.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.simple.exception.ResourceNotFoundException;
import com.example.simple.model.Client;
import com.example.simple.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
    ClientRepository clientRepository;
	
	public List<Client> getAllClients() {
	     return clientRepository.findAll();
	}
	
	public Client getClientById(@PathVariable(value = "id") Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
    }
	
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }
    
    public Client updateClient(@PathVariable(value = "id") Long clientId,
            @Valid @RequestBody Client clientBody) {

		Client client = clientRepository.findById(clientId)
		.orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
		
		client.setTipoChave(clientBody.getTipoChave());
		client.setChavePix(clientBody.getChavePix());
		client.setAgencia(clientBody.getAgencia());
		client.setContaCorrente(clientBody.getContaCorrente());
		
		Client updateClient = clientRepository.save(client);
		return updateClient;
	}
	    
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));

        clientRepository.delete(client);

        return ResponseEntity.ok().build();
    }
}
