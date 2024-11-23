package com.example.simple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.simple.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
