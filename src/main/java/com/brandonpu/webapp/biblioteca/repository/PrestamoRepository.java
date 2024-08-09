package com.brandonpu.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brandonpu.webapp.biblioteca.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{

}
