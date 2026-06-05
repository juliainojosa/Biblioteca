package com.biblioteca.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UsuarioTest {

    @Test
    @DisplayName("Deve criar usuário corretamente")
    void deveCriarUsuarioCorretamente() {

        Usuario usuario = new Usuario();

        usuario.setIdUsuario(1L);
        usuario.setNomeUsuario("Maria");
        usuario.setEmail("maria@email.com");
        usuario.setTelefone("81999999999");

        assertEquals(1L, usuario.getIdUsuario());
        assertEquals("Maria", usuario.getNomeUsuario());
        assertEquals("maria@email.com", usuario.getEmail());
        assertEquals("81999999999", usuario.getTelefone());
    }

    @Test
    @DisplayName("Deve alterar dados do usuário")
    void deveAlterarDadosUsuario() {

        Usuario usuario = new Usuario();

        usuario.setNomeUsuario("João");
        usuario.setEmail("joao@email.com");

        usuario.setNomeUsuario("Maria");
        usuario.setEmail("maria@email.com");

        assertEquals("Maria", usuario.getNomeUsuario());
        assertEquals("maria@email.com", usuario.getEmail());
    }
}