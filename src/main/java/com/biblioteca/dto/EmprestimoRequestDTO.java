package com.biblioteca.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record EmprestimoRequestDTO(

        @NotNull(message = "Data do empréstimo é obrigatória")
        LocalDate dataEmprestimo,

        @NotNull(message = "Data de devolução é obrigatória")
        LocalDate dataDevolucao,

        boolean devolvido,

        @NotNull(message = "Usuário é obrigatório")
        Long usuarioId,

        @NotNull(message = "Livro é obrigatório")
        Long livroId

) {
}