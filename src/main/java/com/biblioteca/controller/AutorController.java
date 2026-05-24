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
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id) {

        Optional<Autor> autor = autorService.buscarPorId(id);

        if (autor.isPresent()) {

            return ResponseEntity.ok(autor.get());
        }

        return ResponseEntity.notFound().build();
    }

    // CADASTRAR AUTOR
    @PostMapping
    public Autor cadastrarAutor(@RequestBody Autor autor) {

        return autorService.cadastrarAutor(autor);
    }

    // ATUALIZAR AUTOR
    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizarAutor(
            @PathVariable Long id,
            @RequestBody Autor autorAtualizado) {

        Autor autor = autorService.atualizarAutor(id, autorAtualizado);

        if (autor != null) {

            return ResponseEntity.ok(autor);
        }

        return ResponseEntity.notFound().build();
    }

    // DELETAR AUTOR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id) {

        Optional<Autor> autor = autorService.buscarPorId(id);

        if (autor.isPresent()) {

            autorService.deletarAutor(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}