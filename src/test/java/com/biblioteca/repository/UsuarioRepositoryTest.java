package com.biblioteca.repository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.biblioteca.entity.Usuario;

@DataJpaTest
@DisplayName("Testes de integração - UsuarioRepository")
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Salvar usuário")
    void salvarUsuario() {

        Usuario usuario = new Usuario();

        usuario.setNomeUsuario("Maria");
        usuario.setEmail("maria@email.com");
        usuario.setTelefone("81999999999");

        Usuario salvo =
                usuarioRepository.save(usuario);

        assertNotNull(salvo.getIdUsuario());
    }

    @Test
    @DisplayName("Buscar usuário por nome")
    void buscarPorNome() {

        Usuario usuario = new Usuario();

        usuario.setNomeUsuario("Maria");
        usuario.setEmail("maria@email.com");
        usuario.setTelefone("81999999999");

        usuarioRepository.save(usuario);

        List<Usuario> resultado =
                usuarioRepository.findByNomeUsuario("Maria");

        assertFalse(resultado.isEmpty());
    }

    @Test
    @DisplayName("Buscar usuário inexistente")
    void buscarUsuarioInexistente() {

        List<Usuario> resultado =
                usuarioRepository.findByNomeUsuario("Inexistente");

        assertTrue(resultado.isEmpty());
    }
}