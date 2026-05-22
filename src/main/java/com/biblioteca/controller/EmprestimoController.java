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
import com.biblioteca.repository.EmprestimoRepository;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @GetMapping
    public Iterable<Emprestimo> listarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(@PathVariable Long id) {

        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);

        if (emprestimo.isPresent()) {
            return ResponseEntity.ok(emprestimo.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Emprestimo cadastrarEmprestimo(@RequestBody Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }
}