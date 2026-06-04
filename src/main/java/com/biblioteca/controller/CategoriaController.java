package com.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biblioteca.entity.Categoria;
import com.biblioteca.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public Iterable<Categoria> listarCategorias() {

        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(
            @PathVariable Long id) {

        Categoria categoria =
                categoriaService.buscarPorId(id);

        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrarCategoria(
            @RequestBody Categoria categoria) {

        Categoria categoriaSalva =
                categoriaService.cadastrarCategoria(categoria);

        return ResponseEntity.status(201)
                .body(categoriaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(
            @PathVariable Long id,
            @RequestBody Categoria categoriaAtualizada) {

        Categoria categoria =
                categoriaService.atualizarCategoria(
                        id,
                        categoriaAtualizada);

        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(
            @PathVariable Long id) {

        categoriaService.deletarCategoria(id);

        return ResponseEntity.noContent().build();
    }
}