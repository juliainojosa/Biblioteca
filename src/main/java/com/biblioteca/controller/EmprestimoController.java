package com.biblioteca.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biblioteca.entity.Emprestimo;
import com.biblioteca.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    // LISTAR TODOS OS EMPRÉSTIMOS
    @GetMapping
    public Iterable<Emprestimo> listarEmprestimos() {

        return emprestimoService.listarEmprestimos();
    }

    // BUSCAR EMPRÉSTIMO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(
            @PathVariable Long id) {

        Optional<Emprestimo> emprestimo =
                emprestimoService.buscarPorId(id);

        return ResponseEntity.of(emprestimo);
    }

    // CADASTRAR EMPRÉSTIMO
    @PostMapping
    public ResponseEntity<Emprestimo> cadastrarEmprestimo(
            @RequestBody Emprestimo emprestimo) {

        Emprestimo emprestimoSalvo =
                emprestimoService.cadastrarEmprestimo(emprestimo);

        return ResponseEntity.status(201)
                .body(emprestimoSalvo);
    }

    // ATUALIZAR EMPRÉSTIMO
    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> atualizarEmprestimo(
            @PathVariable Long id,
            @RequestBody Emprestimo emprestimoAtualizado) {

        Emprestimo emprestimo =
                emprestimoService.atualizarEmprestimo(
                        id,
                        emprestimoAtualizado);

        return ResponseEntity.ok(emprestimo);
    }

    // DELETAR EMPRÉSTIMO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo(
            @PathVariable Long id) {

        emprestimoService.deletarEmprestimo(id);

        return ResponseEntity.noContent().build();
    }
}