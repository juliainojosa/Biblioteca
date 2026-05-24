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

        if (emprestimo.isPresent()) {

            return ResponseEntity.ok(emprestimo.get());
        }

        return ResponseEntity.notFound().build();
    }

    // CADASTRAR EMPRÉSTIMO
    @PostMapping
    public Emprestimo cadastrarEmprestimo(
            @RequestBody Emprestimo emprestimo) {

        return emprestimoService
                .cadastrarEmprestimo(emprestimo);
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

        if (emprestimo != null) {

            return ResponseEntity.ok(emprestimo);
        }

        return ResponseEntity.notFound().build();
    }

    // DELETAR EMPRÉSTIMO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo(
            @PathVariable Long id) {

        Optional<Emprestimo> emprestimo =
                emprestimoService.buscarPorId(id);

        if (emprestimo.isPresent()) {

            emprestimoService.deletarEmprestimo(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

//sei la oq sei la oq