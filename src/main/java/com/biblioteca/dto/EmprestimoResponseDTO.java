package com.biblioteca.dto;

import java.time.LocalDate;

public record EmprestimoResponseDTO(

        Long idEmprestimo,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao,
        boolean devolvido,
        String usuario,
        String livro

) {
}