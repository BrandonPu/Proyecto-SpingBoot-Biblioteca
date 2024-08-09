package com.brandonpu.webapp.biblioteca.service;

import java.util.List;

import com.brandonpu.webapp.biblioteca.model.Prestamo;

public interface IPrestamosService {

  public List<Prestamo> listarPrestamos();

  public Prestamo buscarPrestamoPorId(Long id);

  public Prestamo guardarPrestamo(Prestamo prestamo);

  public void eliminarPrestamo(Prestamo prestamo);
}
