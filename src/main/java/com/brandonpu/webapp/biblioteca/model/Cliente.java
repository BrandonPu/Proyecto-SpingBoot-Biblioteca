package com.brandonpu.webapp.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "Clientes")
public class Cliente {

    @Id
    @NotNull(message = "El Dpi no puede estar vacio")
    private Long dpi;
    @NotNull(message = "El Nombre del cliente no puede ser nulo")
    private String nombre;
    @NotNull(message = "El Apellido del cliente no puede ser nulo")
    private String apellido;
    @NotNull(message = "El Telefono del cliente no puede ser nulo")
    private String telefono;

}
