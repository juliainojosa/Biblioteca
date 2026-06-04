package com.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biblioteca.entity.Autor;
import com.biblioteca.service.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    // LISTAR TODOS OS AUTORES
    @GetMapping
    public Iterable<Autor> listarAutores() {

        return autorService.listarAutores();
    }

    // BUSCAR AUTOR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarPorId(
            @PathVariable Long id) {

        Autor autor =
                autorService.buscarPorId(id);

        return ResponseEntity.ok(autor);
    }

    // CADASTRAR AUTOR
    @PostMapping
    public ResponseEntity<Autor> cadastrarAutor(
            @RequestBody Autor autor) {

        Autor autorSalvo =
                autorService.cadastrarAutor(autor);

        return ResponseEntity.status(201)
                .body(autorSalvo);
    }

    // ATUALIZAR AUTOR
    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizarAutor(
            @PathVariable Long id,
            @RequestBody Autor autorAtualizado) {

        Autor autor =
                autorService.atualizarAutor(
                        id,
                        autorAtualizado);

        return ResponseEntity.ok(autor);
    }

    // DELETAR AUTOR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(
            @PathVariable Long id) {

        autorService.deletarAutor(id);

        return ResponseEntity.noContent().build();
    }
}