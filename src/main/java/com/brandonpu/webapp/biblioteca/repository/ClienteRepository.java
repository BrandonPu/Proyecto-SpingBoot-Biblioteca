package com.brandonpu.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brandonpu.webapp.biblioteca.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
