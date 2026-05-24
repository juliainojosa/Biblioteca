package com.biblioteca.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.entity.Categoria;
import com.biblioteca.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // LISTAR TODAS AS CATEGORIAS
    @GetMapping
    public Iterable<Categoria> listarCategorias() {

        return categoriaService.listarCategorias();
    }

    // BUSCAR CATEGORIA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {

        Optional<Categoria> categoria = categoriaService.buscarPorId(id);

        if (categoria.isPresent()) {

            return ResponseEntity.ok(categoria.get());
        }

        return ResponseEntity.notFound().build();
    }

    // CADASTRAR CATEGORIA
    @PostMapping
    public Categoria cadastrarCategoria(@RequestBody Categoria categoria) {

        return categoriaService.cadastrarCategoria(categoria);
    }

    // ATUALIZAR CATEGORIA
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(
            @PathVariable Long id,
            @RequestBody Categoria categoriaAtualizada) {

        Categoria categoria = categoriaService.atualizarCategoria(id, categoriaAtualizada);

        if (categoria != null) {

            return ResponseEntity.ok(categoria);
        }

        return ResponseEntity.notFound().build();
    }

    // DELETAR CATEGORIA
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {

        Optional<Categoria> categoria = categoriaService.buscarPorId(id);

        if (categoria.isPresent()) {

            categoriaService.deletarCategoria(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}