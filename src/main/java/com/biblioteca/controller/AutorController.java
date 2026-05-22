package com.biblioteca.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.entity.Autor;
import com.biblioteca.repository.AutorRepository;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public Iterable<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id) {

        Optional<Autor> autor = autorRepository.findById(id);

        if (autor.isPresent()) {
            return ResponseEntity.ok(autor.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Autor cadastrarAutor(@RequestBody Autor autor) {
        return autorRepository.save(autor);
    }
}