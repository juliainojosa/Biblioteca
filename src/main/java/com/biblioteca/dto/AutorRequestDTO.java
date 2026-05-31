package com.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;

public record AutorRequestDTO(

        @NotBlank(message = "Nome do autor é obrigatório")
        String nomeAutor,

        String nacionalidade

) {
}