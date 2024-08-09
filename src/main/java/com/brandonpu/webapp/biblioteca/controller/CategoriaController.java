package com.brandonpu.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brandonpu.webapp.biblioteca.model.Categoria;
import com.brandonpu.webapp.biblioteca.service.CategoriaService;

@Controller
@RestController
@RequestMapping(value = "")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    // Listar Categorias como un retorno
    @GetMapping("/categorias")
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    // Buscar
    @GetMapping("/categoria")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Este es el agregar con mensajes
    @PostMapping("/categoria")
    public ResponseEntity<Map<String, String>> agregarCategoria(@RequestBody Categoria categoria) {
        Map<String, String> response = new HashMap<>();
        try {
            if (categoriaService.guardarCategoria(categoria)) {
                response.put("message", "La categoria se creo con exito");
                return ResponseEntity.ok(response);
            } else {
                response.put("err", "La categoria ya esta hecha");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("err", "Hubo un error al crear la categoria");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Editar
    @PutMapping("/categoria")
    public ResponseEntity<Map<String, String>> editarCategoria(@RequestParam Long id,
            @RequestBody Categoria categoriaNueva) {
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoria.setNombreCategoria(categoriaNueva.getNombreCategoria());
            if (categoriaService.verificarCategoriaDuplicado(categoria)) {
                response.put("message", "La categoria se ha editado con exito");
                return ResponseEntity.ok(response);
            }else{
                response.put("err", "La categoria no se puso editado");
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception e) {
            response.put("err", "La categoria no se puso editado");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Eliminar
    @DeleteMapping("/categoria")
    public ResponseEntity<Map<String, String>> eliminarCategoria(@RequestParam Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoriaService.eliminarCategoria(categoria);
            response.put("message", "Categoria Eliminada con Exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "La Categoria no se pudo eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
