package com.biblioteca.dto;

public record LivroResponseDTO(

        Long idLivro,
        String titulo,
        String isbn,
        String autor,
        String categoria

) {
}