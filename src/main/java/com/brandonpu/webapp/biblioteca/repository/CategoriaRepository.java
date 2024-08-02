package com.brandonpu.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brandonpu.webapp.biblioteca.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
