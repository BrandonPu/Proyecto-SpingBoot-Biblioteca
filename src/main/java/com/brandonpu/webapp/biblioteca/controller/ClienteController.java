package com.brandonpu.webapp.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brandonpu.webapp.biblioteca.model.Cliente;
import com.brandonpu.webapp.biblioteca.service.ClienteService;

@Controller
@RestController
@RequestMapping(value = "cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/")
    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }
}
