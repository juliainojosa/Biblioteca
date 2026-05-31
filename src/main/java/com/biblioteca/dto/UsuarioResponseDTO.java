package com.biblioteca.dto;

public record UsuarioResponseDTO(

        Long idUsuario,
        String nomeUsuario,
        String email,
        String telefone

) {
}