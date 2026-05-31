package com.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRequestDTO(

        @NotBlank(message = "Título é obrigatório")
        String titulo,

        @NotBlank(message = "ISBN é obrigatório")
        String isbn,

        @NotNull(message = "Autor é obrigatório")
        Long autorId,

        @NotNull(message = "Categoria é obrigatória")
        Long categoriaId

) {
}