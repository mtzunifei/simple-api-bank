package com.example.simple.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.simple.exception.ResourceNotFoundException;
import com.example.simple.model.Client;
import com.example.simple.model.Transaction;
import com.example.simple.repository.ClientRepository;
import com.example.simple.repository.TransactionRepository;

public class TransactionService {
	
	@Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/transaction")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction createTransacao(@Valid @RequestBody Transaction transaction) {
    	
        //verifica se o cliente já esta cadastrado, é necessário cadastrar antes de fazer a transação
    	Client client = clientRepository.findById(transaction.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", transaction.getClientId()));

		if ((transaction.getTipoTransacao().name().equals("PIX")) || (transaction.getTipoTransacao().name().equals("DEPOSITO"))) 
		{
			transaction.setAgencia(client.getAgencia());
			transaction.setContaCorrente(client.getContaCorrente());
			transaction.setChavePix(client.getChavePix());
			transaction.setTipoChave(client.getTipoChave());
			
    		client.setSaldo(client.getSaldo().add(transaction.getValor()));
    		clientRepository.save(client);
    		
    		return transactionRepository.save(transaction);
		}
		if ((transaction.getTipoTransacao().name().equals("RECARGA_CELULAR")) || (transaction.getTipoTransacao().name().equals("SAQUE"))) 
		{	
			//verifica se a conta possui saldo para recarga ou saque
			int compararSaldo = client.getSaldo().compareTo(transaction.getValor());
			if (compararSaldo > 0)
			{
				transaction.setAgencia(client.getAgencia());
				transaction.setContaCorrente(client.getContaCorrente());
				
	    		client.setSaldo(client.getSaldo().subtract(transaction.getValor()));
	    		clientRepository.save(client);
	    		
	    		return transactionRepository.save(transaction);
			}
		}
		throw new ResourceNotFoundException("Transaction", "id", transaction.getClientId());
	
    }

    public Transaction getTransacaoById(@PathVariable(value = "id") Long transacaoId) {
        return transactionRepository.findById(transacaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Transacao", "id", transacaoId));
    }

    public Transaction updateTransacao(@PathVariable(value = "id") Long transacaoId,
                                           @Valid @RequestBody Transaction transacaoDetails) {

        Transaction transacao = transactionRepository.findById(transacaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Transacao", "id", transacaoId));

        transacao.setTipoTransacao(transacaoDetails.getTipoTransacao());
        transacao.setValor(transacaoDetails.getValor());

        Transaction updateTransacao = transactionRepository.save(transacao);
        return updateTransacao;
    }

    public ResponseEntity<?> deleteTransacao(@PathVariable(value = "id") Long transacaoId) {
        Transaction transacao = transactionRepository.findById(transacaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Transacao", "id", transacaoId));

        transactionRepository.delete(transacao);

        return ResponseEntity.ok().build();
    }
}
