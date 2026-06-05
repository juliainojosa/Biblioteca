package com.biblioteca.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmprestimoEntityTest {

    @Test
    @DisplayName("Deve criar empréstimo e acessar getters/setters")
    void deveCriarEmprestimoComSucesso() {

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);

        Livro livro = new Livro();
        livro.setIdLivro(1L);

        Emprestimo emprestimo = new Emprestimo();

        emprestimo.setIdEmprestimo(1L);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(7));
        emprestimo.setDevolvido(false);
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);

        assertEquals(1L, emprestimo.getIdEmprestimo());
        assertEquals(usuario, emprestimo.getUsuario());
        assertEquals(livro, emprestimo.getLivro());
        assertFalse(emprestimo.isDevolvido());
    }
}