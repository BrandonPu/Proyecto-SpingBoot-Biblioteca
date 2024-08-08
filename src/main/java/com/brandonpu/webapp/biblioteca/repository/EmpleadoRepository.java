package com.brandonpu.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brandonpu.webapp.biblioteca.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
  
}
