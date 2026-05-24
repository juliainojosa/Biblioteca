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

import com.biblioteca.entity.Emprestimo;
import com.biblioteca.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    // LISTAR TODOS
    @GetMapping
    public Iterable<Emprestimo> listarEmprestimos() {

        return emprestimoService.listarEmprestimos();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(@PathVariable Long id) {

        Optional<Emprestimo> emprestimo = emprestimoService.buscarPorId(id);

        if (emprestimo.isPresent()) {

            return ResponseEntity.ok(emprestimo.get());
        }

        return ResponseEntity.notFound().build();
    }

    // CADASTRAR
    @PostMapping
    public Emprestimo cadastrarEmprestimo(@RequestBody Emprestimo emprestimo) {

        return emprestimoService.cadastrarEmprestimo(emprestimo);
    }
}