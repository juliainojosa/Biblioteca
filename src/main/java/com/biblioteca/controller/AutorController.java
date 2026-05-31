package com.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.dto.AutorRequestDTO;
import com.biblioteca.dto.AutorResponseDTO;
import com.biblioteca.service.AutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    // LISTAR TODOS OS AUTORES
    @GetMapping
    public List<AutorResponseDTO> listarAutores() {

        return autorService.listarAutores();
    }

    // BUSCAR AUTOR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(autorService.buscarPorId(id));
    }

    // CADASTRAR AUTOR
    @PostMapping
    public ResponseEntity<AutorResponseDTO> cadastrarAutor(
            @Valid @RequestBody AutorRequestDTO dto) {

        AutorResponseDTO autor = autorService.cadastrarAutor(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(autor);
    }

    // ATUALIZAR AUTOR
    @PutMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> atualizarAutor(
            @PathVariable Long id,
            @Valid @RequestBody AutorRequestDTO dto) {

        AutorResponseDTO autor = autorService.atualizarAutor(id, dto);

        return ResponseEntity.ok(autor);
    }

    // DELETAR AUTOR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id) {

        autorService.deletarAutor(id);

        return ResponseEntity.noContent().build();
    }
}