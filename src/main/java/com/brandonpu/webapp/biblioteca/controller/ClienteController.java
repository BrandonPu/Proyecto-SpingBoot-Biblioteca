package com.brandonpu.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{dpi}")
    public ResponseEntity<Cliente> buscarClientePorDpi(@PathVariable Long dpi) {
        try {
            return ResponseEntity.ok(clienteService.buscarClientePorDpi(dpi));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, String>> agregarCliente(@RequestBody Cliente cliente) {
        Map<String, String> response = new HashMap<>();
        try {
            clienteService.guardarCliente(cliente);
            response.put("message", "El cliente se creo con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "Hubo un error al crear el cliente");
            return ResponseEntity.badRequest().body(response);
        }

    }

    @PutMapping("/{dpi}")
    public ResponseEntity<Map<String, String>> editarCliente(@PathVariable Long dpi,
            @RequestBody Cliente clienteNuevo) {
        Map<String, String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarClientePorDpi(dpi);
            cliente.setDpi(clienteNuevo.getDpi());
            cliente.setNombre(clienteNuevo.getNombre());
            cliente.setApellido(clienteNuevo.getApellido());
            cliente.setTelefono(clienteNuevo.getTelefono());
            clienteService.guardarCliente(cliente);
            response.put("message", "El cliente se ha editado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "El cliente no se pudo editar");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{dpi}")
    public ResponseEntity<Map<String, String>> eliminarCliente(@PathVariable Long dpi) {
        Map<String, String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarClientePorDpi(dpi);
            clienteService.eliminarCliente(cliente);
            response.put("message", "Cliente eliminado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "El cliente no se pudo eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
